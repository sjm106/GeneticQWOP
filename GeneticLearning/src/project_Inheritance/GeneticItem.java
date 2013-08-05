package project_Inheritance;

public abstract class GeneticItem<T> implements Comparable<GeneticItem<T>>{

	protected T item;
	
	private float fitness;
	private boolean FITNESS_SET = false;
	
	public GeneticItem(T Item){
		item = Item;
	}
	
	public T getItem(){
		return item;
	}
	
	public float getFitness(){
		return fitness;
	}
	
	public void setFitness(float val){
		if(!FITNESS_SET){
			fitness = val;
			FITNESS_SET = true;
		}else{
			throw new SecurityException("Fitness can only be set once");
		}
	}
	
	public void resetFitness(){
		fitness = 0.0f;
		FITNESS_SET = false;
	}
	
	/**
	 * Initializes object to starting data.
	 * @return <T> now has usable data.
	 */
	public abstract T Initialize();
	
	/**
	 * Evaluates the item. Returns fitness level.
	 * (0 < fitness < 1.0)
	 * @return Fitness level as float.
	 */
	public abstract float Evaluate();
	
	/**
	 * Creates a new GeneticItem which is the offspring of this and another GeneticItem.
	 * @param parent: The other parent.
	 * @return GeneticItem that is the child of this GeneticItem and another.
	 */
	public abstract GeneticItem<T> Crossover(T parent);
	
	/**
	 * Change the <T> object randomly.
	 * @return A slightly altered <T> object.
	 */
	public abstract T Mutation();
	
	@Override
	public int compareTo(GeneticItem<T> arg0){
		return (fitness < arg0.fitness) ? -1 : ((fitness == arg0.fitness) ? 0 : 1); 
	}
	
}
