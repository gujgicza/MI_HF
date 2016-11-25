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
		fenotype = new BackPack(x, y, items, genotype);
		calculateFittness();
	}
	
	public void calculateFittness() {
		fittness = fenotype.getZeroRowsNum() * fenotype.getZeroColsNum();
	}
	
	// insert mutation
	public void mutate(double mutateCh) {
		Random rand = new Random();
		if (rand.nextDouble() < mutateCh) {
			int index1 = rand.nextInt(genotype.size()-1);
			int index2 = rand.nextInt(genotype.size()-1);
			int element = genotype.remove(index1);
			genotype.add(index2, element);
		}
	}
}


