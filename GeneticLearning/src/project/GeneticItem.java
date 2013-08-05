package project;

/***
 * This interface defines an item that can be used with a Genetic Library.
 * @author Simon
 *
 */
public interface GeneticItem extends Comparable<Float>{
	
	/**
	 * Returns an initialized item. Only called once.
	 * Typical initialization is randomization.
	 * @return Initialized GeneticItem.
	 */
	public GeneticItem init();
	
	/**
	 * Evaluates a given GeneticItem.
	 * @param a: A GeneticItem to be evaluated.
	 * @return Floating point fitness level x. 0 < x < 1.
	 */
	public float evaluate(GeneticItem a);
	
	/**
	 * Generates a merged Genetic Item from two GeneticItem objects. 
	 * @param a: A GeneticItem to be merged
	 * @param b: A GeneticItem to be merged
	 * @return A new Genetic Item which is the combination of a and b.
	 */
	public GeneticItem crossover(GeneticItem a, GeneticItem b);
	
	/**
	 * Mutates the given GeneticItem. Changes parts of the new GeneticItem randomly.
	 * @param a: A GeneticItem to be mutated.
	 * @return A mutated GeneticItem.
	 */
	public GeneticItem mutation(GeneticItem a);
	
}
