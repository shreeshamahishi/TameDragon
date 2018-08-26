package org.tamedragon.visualization.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.Border;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;

import org.tamedragon.common.llvmir.instructions.CFG;
import org.tamedragon.common.llvmir.irdata.ValueData;
import org.tamedragon.common.llvmir.semanticanalysis.LLVMSemantic;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.llvmir.types.Function;
import org.tamedragon.common.llvmir.types.Module;
import org.tamedragon.common.llvmir.utils.LLVMIREmitter;
import org.tamedragon.common.llvmir.utils.llvmGrammarLexer;
import org.tamedragon.common.llvmir.utils.llvmGrammarParser;
import org.tamedragon.common.optimization.ADCE;
import org.tamedragon.common.optimization.BranchOptimization;
import org.tamedragon.common.optimization.GVN;
import org.tamedragon.common.optimization.GlobalCSE;
import org.tamedragon.common.optimization.LICM;
import org.tamedragon.common.optimization.ListOfOptimization;
import org.tamedragon.common.optimization.ListOfOptimization.OptimizationTypeID;
import org.tamedragon.common.optimization.LocalCSE;
import org.tamedragon.common.optimization.LoopStrengthReduce;
import org.tamedragon.common.optimization.LoopUnSwitching;
import org.tamedragon.common.optimization.MemToRegPromoter;
import org.tamedragon.common.optimization.SparseConditionalConstantPropagation;
import org.tamedragon.common.optimization.TailMerging;
import org.tamedragon.common.optimization.TailRecursiveCallElimination;
import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;

public class Visualize extends JDialog {

	private static final long serialVersionUID = 3256444702936019250L;
	private JPanel jPanelfirst,jPanelSecond,jPanelTop; 
	private JSplitPane jPanelMain; 
	private JComboBox<OptimizationTypeID> optList;
	private CFG currentCFG = null;
	private boolean isOptimized;
	private List<BasicBlock> visitedNodeList = new ArrayList<BasicBlock>() ;
	private JScrollPane area;

	public Visualize(Frame parent, boolean isModel) {

		super(parent, isModel);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		setLayout(new BorderLayout());	

		jPanelTop = new JPanel(new FlowLayout());
		this.add(jPanelTop, BorderLayout.PAGE_START);
		jPanelTop.setBackground(Color.cyan);


		jPanelMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jPanelMain.setLeftComponent(jPanelfirst);
		jPanelMain.setRightComponent(jPanelSecond);
		add(jPanelMain,BorderLayout.CENTER);
		jPanelMain.setBackground(Color.cyan);

		jPanelfirst = new JPanel(new BorderLayout());
		jPanelfirst.setIgnoreRepaint(true);
		jPanelMain.add(jPanelfirst);

		optList = new JComboBox<OptimizationTypeID>();
		optList.addItem(ListOfOptimization.OptimizationTypeID.MemToReg);
		optList.addItem(ListOfOptimization.OptimizationTypeID.ADCE);
		optList.addItem(ListOfOptimization.OptimizationTypeID.LICM);
		optList.addItem(ListOfOptimization.OptimizationTypeID.SCCP);
		optList.addItem(ListOfOptimization.OptimizationTypeID.GlobalCSE);
		optList.addItem(ListOfOptimization.OptimizationTypeID.LocalCSE);
		optList.addItem(ListOfOptimization.OptimizationTypeID.GVN);
		optList.addItem(ListOfOptimization.OptimizationTypeID.BranchOptimization);
		optList.addItem(ListOfOptimization.OptimizationTypeID.TailRecursiveCallElimination);
		optList.addItem(ListOfOptimization.OptimizationTypeID.TailMerging);
		optList.addItem(ListOfOptimization.OptimizationTypeID.LoopStrengthReduce);
		optList.addItem(ListOfOptimization.OptimizationTypeID.LoopUnSwitching);
		optList.addItem(ListOfOptimization.OptimizationTypeID.SSADeadCodeEliminator);

		optList.setBounds(510, 100, 100, 30);
		jPanelTop.add(optList);

		JButton nextButton = new JButton("Optimize");
		nextButton.addActionListener(optiButtonClick );
		jPanelTop.add(nextButton, BorderLayout.LINE_END);

		JButton optimize = new JButton("Next Optimization");
		optimize.addActionListener(optiButtonClick );
		jPanelTop.add(optimize);
		jPanelSecond = new JPanel(new BorderLayout());
		jPanelSecond.setBounds(610 ,5,
				510,900);
		jPanelSecond.setBackground(Color.GRAY);
	}

	public void show(Function function){
		CFG cfg = function.getCfg();

		currentCFG = cfg;

		// Create a visualization using JGraph, via an adapter
		AttributeMap vertexMap = createVertexAttributes();
		AttributeMap edgeMap = createEdgeAttributes(cfg);
		JGraphModelAdapter jgAdapter =  new JGraphModelAdapter(cfg,vertexMap,edgeMap);
		final JGraph jgraph = new JGraph(jgAdapter);
		HashMap<DefaultGraphCell, AttributeMap> cellsAndAttrs = new HashMap<DefaultGraphCell, AttributeMap>();
		jgAdapter.edit(cellsAndAttrs, null, null, null);

		area = new JScrollPane(jgraph, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		area.setAutoscrolls(true);

		if(isOptimized) {
			jPanelSecond.add(area);
			jPanelSecond.updateUI();
			this.validate();
		}
		else{
			jPanelfirst.add(area);
		}

		// Add data (graph manipulated via JGraphT)
		BasicBlock v1 = cfg.getStartNode();
		cfg.addVertex(v1);

		//addingVerticesAndEdgesBFS(cfg,v1, cfg);
		visitedNodeList = new ArrayList<BasicBlock>();
		jgraph.setBackground(Color.LIGHT_GRAY);

		final  JGraphHierarchicalLayout hir = new JGraphHierarchicalLayout();
		hir.setInterHierarchySpacing(20);
		hir.setInterRankCellSpacing(20);
		hir.setIntraCellSpacing(20);
		hir.setFixRoots(true);
		hir.setFineTuning(true);
		hir.setDeterministic(true);

		final JGraphFacade graphFacade = new JGraphFacade(jgraph);
		hir.run(graphFacade);

		final Map<?, ?> nestedMap = graphFacade.createNestedMap(true, true);
		jgraph.getGraphLayoutCache().edit(nestedMap);
		this.setVisible(true);
	}

	private ActionListener optiButtonClick = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				callOptimization();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	};;;

	private void addingVerticesAndEdgesBFS(ListenableGraph<BasicBlock, DefaultEdge> jgraphT,
			BasicBlock root, CFG cfg) {
		Queue<BasicBlock> queue = new LinkedList<BasicBlock>() ;

		if (root == null)
			return;

		queue.clear();
		queue.add(root);

		while(!queue.isEmpty()){
			BasicBlock node = queue.remove();

			List<BasicBlock> successors = cfg.getSuccessors(node);
			Iterator<BasicBlock> iterator = successors.iterator();
			while(iterator.hasNext())
			{
				BasicBlock node1 = (BasicBlock) iterator.next();
				if(isVisitedNode(node1)) {
					AttributeMap map = new AttributeMap();
					GraphConstants.setLineColor(map , Color.BLUE);
					jgraphT.addEdge(node, node1);
					continue;
				}

				jgraphT.addVertex(node1);
				jgraphT.addEdge(node, node1);
				queue.add(node1);
				visitedNodeList.add(node1);
			}
		}
	}

	protected void callOptimization() throws Exception {
		if(jPanelSecond.getParent() == null)
			jPanelMain.add(jPanelSecond);

		if(currentCFG == null) {
			// TODO Log warning here: no control flow graph to visualize
			return;
		}

		isOptimized = true;
		Function function = saveCFGinFile(currentCFG);
		MemToRegPromoter memToRegPromoter = new MemToRegPromoter();
		memToRegPromoter.execute(function);
		OptimizationTypeID optimizationTypeID = (OptimizationTypeID) optList.getSelectedItem();

		switch (optimizationTypeID) {
		case MemToReg:
			MemToRegPromoter memToRegPromoter1 = new MemToRegPromoter();
			memToRegPromoter1.execute(function);
			break;
		case ADCE:
			ADCE adce = new ADCE();
			adce.execute(function);
			break;
		case LICM:
			LICM licm = new LICM();
			licm.execute(function);
			break;
		case SCCP:
			SparseConditionalConstantPropagation sccp = new SparseConditionalConstantPropagation();
			sccp.execute(function);
			break;
		case GlobalCSE:
			GlobalCSE globalCSE = new GlobalCSE();
			break;
		case LocalCSE:
			LocalCSE localCSE = new LocalCSE();
			break;
		case GVN:
			GVN gvn = new GVN();
			gvn.execute(function);
			break;
		case BranchOptimization:
			BranchOptimization branchOptimization = new BranchOptimization();
			branchOptimization.execute(function);
			break;
		case TailRecursiveCallElimination:
			TailRecursiveCallElimination tailRecursiveCallElimination = new TailRecursiveCallElimination();
			tailRecursiveCallElimination.execute(function);
			break;
		case TailMerging:
			TailMerging tailMerging = new TailMerging();
			tailMerging.execute(function);
			break;
		case LoopStrengthReduce:
			LoopStrengthReduce loopStrengthReduce = new LoopStrengthReduce();
			loopStrengthReduce.execute(function);
			break;
		case LoopUnSwitching:
			LoopUnSwitching loopUnSwitching = new LoopUnSwitching();
			loopUnSwitching.execute(function);
			break;
		case SSADeadCodeEliminator:
			//SSADeadCodeEliminator ssaDeadCodeEliminator = new SSADeadCodeEliminator();
			break;

		default:

			break;
		}

		show(function);

	}

	private Function saveCFGinFile(CFG cfg){

		File file = new File("textA.bc");

		LLVMIREmitter emitter = LLVMIREmitter.getInstance();
		emitter.reset();
		BasicBlock basicBlock = (BasicBlock) cfg.getStartNode();
		Module module = basicBlock.getParent().getParent();
		List<String>  instrs = emitter.emitInstructions(module);
		String s = "";
		int count  = 0;
		for(String instr : instrs){
			if(count == instrs.size()-1)
				s += instr;
			else
				s += instr + "\n";
			count++;
		}
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		FileWriter fw = null;
		BufferedWriter bw = null;
		try{
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(s);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		finally{
			if(bw != null){
				try{
					bw.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
			if(fw != null){
				try{
					fw.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}

		return getFunction(file);
	}

	private boolean isVisitedNode(BasicBlock v1) {
		Iterator<BasicBlock> iterator = visitedNodeList.iterator(); 
		while(iterator.hasNext())
		{
			BasicBlock node = iterator.next();
			if(node == v1)
				return true;
		}
		return false;
	}

	public static AttributeMap createVertexAttributes() {
		AttributeMap map = new AttributeMap();
		Color c = Color.WHITE;

		GraphConstants.setBounds(map, new Rectangle2D.Double(100, 50, 90, 30));
		GraphConstants.setBackground(map, c);
		GraphConstants.setForeground(map, Color.BLACK);
		GraphConstants.setFont(map, GraphConstants.DEFAULTFONT.deriveFont(Font.ITALIC,(float)11));
		GraphConstants.setOpaque(map, true);
		GraphConstants.setAutoSize(map, true);
		GraphConstants.setResize(map, true);
		GraphConstants.setBendable(map,true);
		Border insideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border outsideBorder = BorderFactory.createLineBorder(Color.BLACK,1);
		Border compoundBorder = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
		GraphConstants.setBorder(map,compoundBorder);
		return map;
	}

	public static <V, E> AttributeMap createEdgeAttributes(
			Graph<V, E> jGraphTGraph) {
		AttributeMap map = new AttributeMap();

		if (jGraphTGraph instanceof DirectedGraph<?, ?>) {
			GraphConstants.setLineEnd(map, GraphConstants.ARROW_TECHNICAL);
			GraphConstants.setEndFill(map, true);
			GraphConstants.setEndSize(map, 10);
		}

		GraphConstants.setForeground(map, Color.decode("#25507C"));
		GraphConstants.setFont( map,
				GraphConstants.DEFAULTFONT.deriveFont(Font.BOLD,20));
		GraphConstants.setLineColor(map, Color.BLACK);
		GraphConstants.setLineWidth(map, (float) 1);
		GraphConstants.setBendable(map,true);
		GraphConstants.setLineStyle(map, GraphConstants.STYLE_SPLINE);
		return map;
	}


	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

	public static Function getFunction(File file)  {
		FileReader fr = null;
		BufferedReader br = null;

		Function function = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			char[] chArr = new char[(int) file.length()];
			br.read(chArr);
			String str = new String(chArr);
			if (str != null) {
				return getFunction(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RecognitionException re) {
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return function;
	}

	public static Function getFunction(String fileContent) throws RecognitionException  {

		Function function = null;

		ANTLRStringStream strStream = new ANTLRStringStream(fileContent);
		llvmGrammarLexer lexer = new llvmGrammarLexer(strStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		llvmGrammarParser parser = new llvmGrammarParser(tokens);

		parser.llvm();

		System.out.println("Parsed sucessfully...");

		List<ValueData> irDataList = parser.getList();
		LLVMSemantic llvmSemantic = new LLVMSemantic("", irDataList );
		Module module = llvmSemantic.semantic();
		function = module.getFunctions().get(0);


		return function;
	}

}
