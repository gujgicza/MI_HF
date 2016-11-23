package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Entity {
	List<Item> items;

	List<Integer> genotype;	// order of the items
	BackPack fenotype;		// placing of the items in the backpack
	Integer fittness;
	
	public Entity(int x, int y, List<Item> items, List<Integer> gen) {
		this.items = items;
		
		this.genotype = gen;
		mutate();
		fenotype = new BackPack(x, y, items, genotype);
		calculateFittness();
	}
	
	public void calculateFittness() {
		fittness = fenotype.getZeros();
	}
	
	// insert mutation
	public void mutate() {
		int index1 = new Random().nextInt(genotype.size()-1);
		int index2 = new Random().nextInt(genotype.size()-1);
		int element = genotype.remove(index1);
		genotype.add(index2, element);
	}
	
	// order crossover
	public Entity crossover(Entity otherParent){
		int index1 = new Random().nextInt(genotype.size());
		int index2 = new Random().nextInt(genotype.size()-index1) + index1;
		
		ArrayList<Integer> childGen = new ArrayList<>();

		// matching section
		for (int i = index1; i < index2; i ++)
			childGen.add(genotype.get(i));
		
		ArrayList<Integer>remained = new ArrayList<>(otherParent.genotype);
		rotateLeft(remained, index1);
		remained.removeIf(
				element -> {return childGen.contains(element); });	// itt lehet hiba
		
		for (int i = 0; i < remained.size(); i++)
			childGen.add(remained.get(i));
		rotateRight(childGen, index1);
		
		Entity child = new Entity(fenotype.width, fenotype.height, items, childGen);
		return child;
	}
	
	private static <T> ArrayList<T> rotateRight(ArrayList<T> aL, int shift)
	{
	    if (aL.size() == 0)
	        return aL;

	    T element = null;
	    for(int i = 0; i < shift; i++)
	    {
	        // remove last element, add it to front of the ArrayList
	        element = aL.remove( aL.size() - 1 );
	        aL.add(0, element);
	    }

	    return aL;
	}
	
	private static <T> ArrayList<T> rotateLeft(ArrayList<T> aL, int shift)
	{
	    if (aL.size() == 0)
	        return aL;

	    T element = null;
	    for(int i = 0; i < shift; i++)
	    {
	        // remove first element, add it to the end of the ArrayList
	        element = aL.remove(0);
	        aL.add(element);
	    }

	    return aL;
	}
}


