package implementations;

import java.util.Random;

import project_Inheritance.GeneticItem;

public class StringGeneticItem extends GeneticItem<String>{

	public final String COMPARE_STRING = "whargarbl";
	private final int MAX_LENGTH = 256;
	private final float MUTATION_CHANCE = 0.01f;
	
	private Random rand = new Random();
	
	public StringGeneticItem(String Item) {
		super(Item);
		//item = Item;
	}
	
	public StringGeneticItem() {
		super("");
	}

	@Override
	public String Initialize() {
		String newString = "";
		int len = rand.nextInt(MAX_LENGTH);
		for(int i = 0;i<len;i++){
			newString += Character.toString((char) (rand.nextInt(96) + 32));
		}
		return newString;
	}

	@Override
	public float Evaluate() {
		float fitness = 1.0f;
		int num_incorrect = 0;
		for(int i = 0;i<COMPARE_STRING.length() && i < item.length();i++){
			if(item.charAt(i) != COMPARE_STRING.charAt(i)) num_incorrect++;
		}
		fitness -= (COMPARE_STRING.length() < item.length() ? num_incorrect / COMPARE_STRING.length() : num_incorrect / item.length());
		return fitness;
	}

	@Override
	public GeneticItem<String> Crossover(String parent) {
		String newString = "";
		int len = (item.length() + parent.length()) / 2;
		int cut = rand.nextInt(len);
		newString += item.substring(0, cut);
		newString += parent.substring(cut, parent.length() - 1);
		item = newString;
		this.resetFitness();
		return this;
	}

	@Override
	public String Mutation() {
		String newString = item;
		
		for(int i = 0;i<newString.length();i++){
			int roll = rand.nextInt(100);
			int p = (int)(MUTATION_CHANCE * 100);
			if(roll <= p){
				System.out.print("Mutation occured in String: " + newString + " at position " + i + ". " + newString.charAt(i) + " changed to ");
				newString = newString.substring(0, i) + Character.toString((char) (rand.nextInt(96) + 32)) + newString.substring(i+1);
				System.out.println(newString.charAt(i));
			}
		}
		return newString;
	}
}
