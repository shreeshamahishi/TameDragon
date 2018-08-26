package org.tamedragon.common.dependenceanalysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.tamedragon.common.llvmir.instructions.Instruction;
import org.tamedragon.common.llvmir.types.BasicBlock;
import org.tamedragon.common.machineabstractions.MachineArchitecture;
import org.tamedragon.common.machineabstractions.Resource;

public class DataDependenceAnalysis {

//	private BasicBlock basicBlock;
//	private MachineArchitecture target;
//	
//	public DiGraph<DependenceEdge, Node> analyze(BasicBlock basicBlock, MachineArchitecture target){
//		
//		this.basicBlock = basicBlock;
//		this.target = target;
//		
//		DiGraph<DependenceEdge, Node> diGraph = new DiGraph<DependenceEdge, Node>();
//		
//		return diGraph;
//	}
//	
//	private int getLatency(Instruction inst1, int cycle1, Instruction inst2, int cycle2){
//		List<Set<Resource>> resourceVectorInst1 = getResourceVector(inst1, cycle1);
//		List<Set<Resource>> resourceVectorInst2 = getResourceVector(inst2, cycle2);
//		
//		boolean cycle;
//		int n = target.getMaxCycles();
//		int latency = 0,j;
//		do{
//			cycle = false;
//			j = 1;
//			while(j <= n){
//				// TODO Replace clumsy way of finding the intersection of two sets
//				// with a better way
//				Set<Resource> intersection = new HashSet<Resource>(resourceVectorInst1.get(j)); // use the copy constructor
//				intersection.retainAll(resourceVectorInst2.get(j));
//				if( !intersection.isEmpty() ){
//					for(int k = 1; k < n -1; k++){
//						resourceVectorInst1.set(k, resourceVectorInst2.get(k+1));
//					}
//					
//					n--;
//					latency++;
//					cycle = true;
//				}
//				j++;
//			}
//		}
//		
//		while(!cycle);
//		
//		return latency;
//	}
//	
//	private List<Set<Resource>> getResourceVector(Instruction ins, int cycle){
//		
//		List<Set<Resource>> resourceVector = new ArrayList<Set<Resource>>();
//		
//		int maxCycles = target.getMaxCycles();
//		
//		for(int i = 1; i < maxCycles; i++){
//			if(cycle + i -1 < maxCycles){
//				Set<Resource> res = target.getResourcesUsed(ins, cycle+i -1);
//				resourceVector.add(res);
//			}
//			else{
//				resourceVector.add(null);
//			}
//		}
//		
//		return resourceVector;
//	}
//
//	public BasicBlock getBasicBlock() {
//		return basicBlock;
//	}
//
//	public MachineArchitecture getTarget() {
//		return target;
//	}
}
