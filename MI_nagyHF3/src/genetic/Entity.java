package genetic;

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
	
	public Integer getFittness() {
		if (fittness == null)
			calculateFittness();
		return fittness;
	}
	
	// insert mutation
	public void mutate() {
		int index1 = new Random().nextInt(genotype.size()-1);
		int index2 = new Random().nextInt(genotype.size()-1);
		int element = genotype.remove(index1);
		genotype.add(index2, element);
	}
	
	public Entity crossover(Entity otherParent){
		// TODO
		return null;
	}
	
}
