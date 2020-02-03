package org.tamedragon.common.controlflowanalysis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.experimental.dag.DirectedAcyclicGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.junit.Before;
import org.junit.Test;
import org.tamedragon.common.llvmir.instructions.GetElementPtrInst;
import org.tamedragon.common.llvmir.instructions.LoadInst;
import org.tamedragon.common.llvmir.instructions.PhiNode;
import org.tamedragon.common.llvmir.instructions.StoreInst;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.utils.LLVMIRUtils;

public class LoopIdiomRecognizeTests {

	private static final String ROOT_PATH = "ControlFlowAnalysis/LoopIdiomRecognize";

	private LLVMIRUtils llvmirUtils;

	@Before
	public void setUp(){
		llvmirUtils = new LLVMIRUtils();
	}

	@Test
	public void simplePointerIncrementRaw() throws Exception {
		String cSrcfilename =  "SimplePointerIncrementRaw.ll";

		Function function = getFunction(cSrcfilename, "foo");
		assertNotNull(function);

		LoopIdiomRecognize loopIdiomRecognize = new LoopIdiomRecognize(function);
		loopIdiomRecognize.identifyingIncrementingPointers();

		// Initializing store
		StoreInst initStore = (StoreInst) function.getBasicBlockAt(0).getInstructionAt(10);
		IncrementingPointerTuple tuple1 = loopIdiomRecognize.getIncrementingPointerTupleForInstruction(initStore);
		assertNotNull(tuple1);

		// Other instructions that participate in the increment
		LoadInst preLoad = (LoadInst) function.getBasicBlockAt(2).getInstructionAt(5);
		IncrementingPointerTuple tuple2 = loopIdiomRecognize.getIncrementingPointerTupleForInstruction(preLoad);
		assertNotNull(tuple2);
		GetElementPtrInst gep = (GetElementPtrInst) function.getBasicBlockAt(2).getInstructionAt(6);
		IncrementingPointerTuple tuple3 = loopIdiomRecognize.getIncrementingPointerTupleForInstruction(gep);
		assertNotNull(tuple3);
		StoreInst postStore = (StoreInst) function.getBasicBlockAt(2).getInstructionAt(7);
		IncrementingPointerTuple tuple4 = loopIdiomRecognize.getIncrementingPointerTupleForInstruction(postStore);
		assertNotNull(tuple4);

		// Check tuple
		assertTrue(tuple1 == tuple2 && tuple1 == tuple3 && tuple1 == tuple4);
	}

	@Test
	public void simplePointerIncrement() throws Exception {
		String cSrcfilename =  "SimplePointerIncrement.ll";

		Function function = getFunction(cSrcfilename, "foo");
		assertNotNull(function);

		LoopIdiomRecognize loopIdiomRecognize = new LoopIdiomRecognize(function);
		loopIdiomRecognize.identifyingIncrementingPointers();

		// Initializing phi node
		PhiNode phiNode = (PhiNode) function.getBasicBlockAt(1).getInstructionAt(2);
		IncrementingPointerTuple tuple1 = loopIdiomRecognize.getIncrementingPointerTupleForInstruction(phiNode);
		assertNotNull(tuple1);

		// Other instructions that participate in the increment
		GetElementPtrInst gep = (GetElementPtrInst) function.getBasicBlockAt(2).getInstructionAt(2);
		IncrementingPointerTuple tuple2 = loopIdiomRecognize.getIncrementingPointerTupleForInstruction(gep);
		assertNotNull(tuple2);

		// Check tuple
		assertTrue(tuple1 == tuple2);
	}

	private Function getFunction(String srcFileName, String functionNameToOptimize){
		llvmirUtils.getInstructionsList(ROOT_PATH, srcFileName);
		Module module = llvmirUtils.getModule();
		List<Function> functions = module.getFunctions();
		Function function = null;
		for(Function currentFunction : functions){
			String function_name = currentFunction.getName();

			if(function_name.equals(functionNameToOptimize))
			{
				function = currentFunction;
				break;

			}
		}
		return function;
	}

	@Test
	public void testTest() {

		List<PTService> allPTServices = createAllPTServices();

		List<PTService> rootServices = new ArrayList<>();

		Graph<PTService, DefaultEdge> dag = new DirectedAcyclicGraph<>(DefaultEdge.class);

		for(PTService service : allPTServices) {
			List<String> stDependencyNames = service.getStartupDependencies();

			if(stDependencyNames == null || stDependencyNames.size() == 0) {
				rootServices.add(service);
				continue;
			}

			// Service has a startup dependencies
			for(String stDependencyName : stDependencyNames) {
				PTService stDependency = getDependencyFromName(stDependencyName, allPTServices);
				if(stDependency == null) { // No such dependency found in the configs?
					// Throw exception here
				}

				dag.addVertex(stDependency);
				dag.addVertex(service);

				DefaultEdge edge = new DefaultEdge();
				dag.addEdge(stDependency, service, edge);
			}
		}

		List<List<PTService>> result = new ArrayList<>();
		result.add(rootServices);

		BreadthFirstIterator<PTService, DefaultEdge> iterator =  new BreadthFirstIterator<>(dag);
		int currentDepth = 0;
		List<PTService> servicesAtCurrentLevel = new ArrayList<>();
		while(iterator.hasNext()) {
			PTService service = iterator.next();
			if(service.getStartupDependencies() == null || service.getStartupDependencies().size() == 0) {
				continue;
			}
		
			int depth = getDepth(service, allPTServices);
			if(currentDepth != depth) {  // start new level
				servicesAtCurrentLevel = new ArrayList<>();
				servicesAtCurrentLevel.add(service);
				result.add(servicesAtCurrentLevel);
				currentDepth = depth;
			}
			else {  // Same level
				servicesAtCurrentLevel.add(service);
			}
			
		}

		int level = 0;
		for(List<PTService> servicesAtLevel : result) {
			System.out.println("Services at level " + level + " with num services = " + servicesAtLevel.size() + ": ");
			for(PTService service : servicesAtLevel) {
				System.out.println("	" + service.getName());
			}
			
			level++;
		}
	}

	private int getDepth(PTService service, List<PTService> allPTServices ) {
		int depth = 0;

		PTService currentService = service;
		while(currentService.getStartupDependencies() != null && currentService.getStartupDependencies().size() > 0 ) {
			depth++;
			currentService = getDependencyFromName(currentService.getStartupDependencies().get(0), allPTServices);
		}

		return depth;
	}


	private PTService getDependencyFromName(String stDependencyName, List<PTService> allPTServices ) {
		for(PTService service : allPTServices) {
			if(service.getName().equals(stDependencyName)) {
				return service;
			}
		}
		return null;
	}
	
	private List<PTService> createAllPTServices() {

		List<PTService> services = new ArrayList<PTService>();
		String[] ptServiceNames = new String[] {"security_v2", "entities", "workflow", "documents",
				"locations", "wallet", "payments", "pointchain-scoring", "mrb_licensing", "dataroom"};

		Map<String, String[]> dependencyMap = new HashMap<>();
		dependencyMap.put("mrb_licensing", new String[] {"dataroom", "documents"});
		dependencyMap.put("dataroom", new String[] {"entities", "workflow"});
		dependencyMap.put("security_v2", new String[] {"locations"});
		dependencyMap.put("entities", new String[] {"payments"});

		for(String name : ptServiceNames) {
			PTService service = new PTService();
			service.setName(name);
			services.add(service);

		}

		for(PTService service : services) {
			String serviceName = service.getName();
			if("mrb_licensing".equals(serviceName)) {
				System.out.println("WAIT HERE");
			}
			String[] dependenciesNamesArray = dependencyMap.get(serviceName);
			if(dependenciesNamesArray == null) {
				continue;
			}

			List<String> dependenciesNames = Arrays.asList(dependenciesNamesArray);
			if(dependenciesNames != null) {
				service.setStartupDependencies(dependenciesNames);
			}

		}

		return services;
	}
}

class PTService {

	private String name;
	private int port;
	private String welcomePath;
	private String restartPath;
	private List<String> startupDependencies = new ArrayList<>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getWelcomePath() {
		return welcomePath;
	}
	public void setWelcomePath(String welcomePath) {
		this.welcomePath = welcomePath;
	}
	public String getRestartPath() {
		return restartPath;
	}
	public void setRestartPath(String restartPath) {
		this.restartPath = restartPath;
	}
	public List<String> getStartupDependencies() {
		return startupDependencies;
	}
	public void setStartupDependencies(List<String> startupDependencies) {
		this.startupDependencies = startupDependencies;
	}


	@Override
	public String toString() {
		return "name : " + name + ", port: " + port + ", Welcome endpoint: " + welcomePath + ", Restart path: " + restartPath
				+ ", Startup dependencies: " + startupDependencies;
	}
}
