package project_Inheritance;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneticRun<T> {

	private final int GENERATIONS;
	private final int NUM_PARENTS;
	//private final boolean DATA_SET;

	private boolean DEBUG = false;
	private GeneticItem<T>[] ITEMS;

	public GeneticRun(int generations, int numParents){
		GENERATIONS = generations;
		if(numParents < 2){
			NUM_PARENTS = 2;
		}else{
			NUM_PARENTS = numParents;
		}
		//DATA_SET = false;
	}

	public GeneticRun(int generations, int numParents, GeneticItem<T>[] Items){
		GENERATIONS = generations;
		if(numParents < 2){
			NUM_PARENTS = 2;
		}else{
			NUM_PARENTS = numParents;
		}
		ITEMS = Items;
		//DATA_SET = true;
	}

	
	/**
	 * Set the library to output debugging messages.
	 * @param b true || false.
	 */
	public void setDebug(boolean b){
		DEBUG = b;
	}
	
	public void setData(GeneticItem<T>[] items){
		ITEMS = items;
	}

	public T runGenetic(){
		//Initialize data.
		if(ITEMS[0] == null){
			for(GeneticItem<T> g : ITEMS){
				g.item = g.Initialize();
				PrintDebug(g.item.toString() + " initialized \n");
			}
		}
		GeneticItem<T>[] items = ITEMS;
		for(int i = 0;i<GENERATIONS;i++){
			//Evaluate
			for(GeneticItem<T> g : items){
				g.setFitness(g.Evaluate());
			}
			//Crossover
			Arrays.sort(items);
			
			ArrayList<GeneticItem<T>> parents = new ArrayList<GeneticItem<T>>();
			
			for(int k = items.length - NUM_PARENTS;k<items.length;k++){
				parents.add(items[k]);
			}
			ArrayList<GeneticItem<T>> children = new ArrayList<GeneticItem<T>>();
			for(int a = 0;a<parents.size();a++){
				for(int b = 0;b<parents.size();b++){
					if(a == b) continue;
					GeneticItem<T> child = parents.get(a).Crossover(parents.get(b).item); 
					children.add(child);
					PrintDebug(parents.get(a).toString() + " and " + parents.get(b).toString() + " created child " + child.item.toString() + "\n");
				}
			}
			//Mutate
			ArrayList<GeneticItem<T>> newItems = new ArrayList<GeneticItem<T>>();
			for(GeneticItem<T> gen : children){
				GeneticItem<T> newGen = gen;
				newGen.item = gen.Mutation();
				PrintDebug(gen.toString() + " mutated to " + newGen.toString() + "\n");
				newItems.add(newGen);
			}
			
			items = newItems.toArray(items);
		}
		
		for(GeneticItem<T> g : items){
			g.setFitness(g.Evaluate());
		}
		
		Arrays.sort(items);
		
		return items[items.length - 1].item;
	}

	private void PrintDebug(String debug){
		if(DEBUG){
			System.out.print(debug);
		}
	}
}
