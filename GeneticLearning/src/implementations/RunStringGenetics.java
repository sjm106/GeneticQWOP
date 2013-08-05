package implementations;

import project_Inheritance.*;

public class RunStringGenetics {

	public static int GENERATIONS;
	public static int NUM_PARENTS;
	
	public static GeneticRun<String> GR;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length < 2){
			System.out.println("Usage: RunStringGenetics generations number_of_parents");
			System.exit(0);
		}
		GENERATIONS = Integer.valueOf(args[0]);
		NUM_PARENTS = Integer.valueOf(args[1]);
		
		GR = new GeneticRun<String>(GENERATIONS, NUM_PARENTS);
		
		StringGeneticItem[] items = new StringGeneticItem[128];
		
		for(int i = 0;i<items.length;i++){
			items[i] = new StringGeneticItem();
		}
		
		GR.setData(items);
		
		GR.setDebug(true);
		
		GR.runGenetic();
	}

}
