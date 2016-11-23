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
			// create random permutation
			for (int j = 0; j < items.size(); j++)
				gen.add(j);
			java.util.Collections.shuffle(gen);
			population.add(new Entity(backpackWidth, backpackHeight, items, gen));
		}
		// add one with permutation of decreasing item size
		List<Integer> gen = new ArrayList<>();
		for (int j = 0; j < items.size(); j++)
			gen.add(j);
		
		Collections.sort(gen, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(items.get(o2).height * items.get(o2).width, items.get(o1).height * items.get(o1).width);
			}		
		});
		population.add(new Entity(backpackWidth, backpackHeight, items, gen));
		
		sortPop();
		
	}
	
	public void evolve() {		
		sortPop();
		for (int generation = 0; generation < maxGenerations; generation++) {
			Entity parent1;
			Entity parent2;
			
			for (int childNum = 0; childNum < entityNumInGeneration/3; childNum++) {
				parent1 = chooseParent();
				parent2 = chooseParent();
				population.add(parent1.crossover(parent2));
				//population.add(parent2.crossover(parent1));
			}
			sortPop();
			population = population.subList(0, entityNumInGeneration);
		}
	}

	private void sortPop() {
		Collections.sort(population, new Comparator<Entity>() {
		    public int compare(Entity m1, Entity m2) {
		        return Integer.compare(m1.fittness, m2.fittness);
		    }
		});
	}
	
	// scaling
	private Entity chooseParent() {
		int randNum = new Random().nextInt((int)((entityNumInGeneration + 1.0) * (entityNumInGeneration / 2.0)));

		int index;
		int scale = entityNumInGeneration;
		for (index = 0; randNum > scale; index++)
			scale += scale-1;
		
		return population.get(index);
	}
}
