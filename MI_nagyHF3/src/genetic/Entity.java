package genetic;

import java.util.List;

public class Entity {
	List<Item> items;

	List<Integer> genotype;	// order of the items
	BackPack fenotype;		// placing of the items in the backpack
	Integer fittness;
	
	public Entity(int x, int y, List<Item> items, List<Integer> gen) {
		this.items = items;
		
		this.genotype = gen;
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
	
	public void mutate() {
		// TODO
	}
	
	public Entity crossover(Entity otherParent){
		// TODO
		return null;
	}
	
}
