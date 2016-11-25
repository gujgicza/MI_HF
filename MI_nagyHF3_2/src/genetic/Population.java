package genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Population {
	List<Item> items;
	int backpackWidth;
	int backpackHeight;
	
	List<Entity> population;
	
	// parameters of genetic algorithm
	double mutateChance;
	int entityNumInGeneration;
	int maxGenerations;
	double chooseRatio;
		
	public Population(List<Item> items, int backpackW, int backpackH, double mutateCh, int entityNumInGen, int maxGen) {
		this.items = items;
		backpackWidth = backpackW;
		backpackHeight = backpackH;
		
		mutateChance = mutateCh;
		entityNumInGeneration = entityNumInGen;
		maxGenerations = maxGen;
		
		population = new ArrayList<Entity>();
		// create the first (random) population
		for (int i = 0; i < entityNumInGeneration-1; i++) {			
			List<Integer> gen = new ArrayList<>();
			for (int j = 0; j < items.size(); j++)
				gen.add(j);
			
			Collections.sort(gen, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(items.get(o2).height * items.get(o2).width, items.get(o1).height * items.get(o1).width);
				}		
			});
			Entity en = new Entity(backpackWidth, backpackHeight, items, gen);
			en.mutate(mutateCh);
			population.add(en);
		}		
		sortPop();		
	}
	
	public void evolve() {		
		sortPop();
		for (int generation = 0; generation < maxGenerations; generation++) {
			Entity parent;
			Entity child;
			
			for (int childNum = 0; childNum < entityNumInGeneration; childNum++) {
				parent = chooseParent();
				child = new Entity(parent.fenotype.width, parent.fenotype.height, items, parent.genotype);
				child.mutate(mutateChance);
				population.add(child);
			}
			sortPop();
			population = population.subList(0, entityNumInGeneration);
		}
	}

	private void sortPop() {
		Collections.sort(population, new Comparator<Entity>() {
		    public int compare(Entity m1, Entity m2) {
		    	if (m1.fittness > m2.fittness)
		    		return 1;
		    	if (m2.fittness > m1.fittness)
		    		return -1;
		    	else
		    		return Integer.compare(m1.fenotype.getZeros(), m2.fenotype.getZeros());
		    }
		});
	}
	
	// scaling: exponential
	private Entity chooseParent() {
		double rate = 0.75;
		double scale1 = 0.0;
		for (int i = 0; i < entityNumInGeneration; i++)
			scale1 += entityNumInGeneration * Math.pow(rate, i);
		double randNum = new Random().nextDouble()*scale1;		

		int index;
		double scale2 = entityNumInGeneration;
		for (index = 0; randNum > scale2; index++)
			scale2 += scale2*rate;

		return population.get(index);
	}
}
