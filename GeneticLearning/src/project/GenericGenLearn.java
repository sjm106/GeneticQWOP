package project;

import java.util.ArrayList;
import java.util.Arrays;

public class GenericGenLearn {
	
	private ArrayList<GeneticItem> items = new ArrayList<GeneticItem>();
	private final int GENERATIONS = 1000;
	//private final int NUM_PARENTS = 2;
	
	//private float maxEval = 0.0f;
	
	public GenericGenLearn(ArrayList<GeneticItem> Items){
		items = Items;
	}
	
	public void run(){
		for(int i = 0;i<items.size();i++){
			items.set(i, items.get(i).init());
		}
		for(int i = 0;i < GENERATIONS;i++){
			
			GeneticItem[] sortedItems = new GeneticItem[items.size()];
			Arrays.sort(sortedItems);
			
		}
	}
}