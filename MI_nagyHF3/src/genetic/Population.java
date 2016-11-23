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
	
	int currentGeneration;
	
	public Population(List<Item> items, int backpackW, int backpackH, double mutateCh, int entityNumInGen, int maxGen) {
		this.items = items;
		backpackWidth = backpackW;
		backpackHeight = backpackH;
		
		mutateChance = mutateCh;
		entityNumInGeneration = entityNumInGen;
		maxGenerations = maxGen;
		
		population = new ArrayList<Entity>();
		// create the first (random) population
		for (int i = 0; i < entityNumInGeneration; i++) {
			List<Integer> gen = new ArrayList<>();
			// create random permutation
			for (int j = 0; j < items.size(); j++)
				gen.add(j);
			java.util.Collections.shuffle(gen);
			population.add(new Entity(backpackWidth, backpackHeight, items, gen));
		}
		sortPop();
		
		currentGeneration = 0;
	}
	
	public void evolve() {
		List<Entity> parents = new ArrayList<>();
		
		sortPop();
		for (int generation = 0; generation < maxGenerations; generation++) {
			Entity parent1;
			Entity parent2;
			
			for (int childNum = 0; childNum < parents.size() * 2; childNum++) {
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
		//Collections.sort(population, 
				//(a, b) -> a.fittness < b.fittness ? -1 : 1);
			//	(a, b) -> a.fittness < b.fittness ? -1 : a.fittness == b.fittness ? 0 : 1); // ez is rossz lehet
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
		for (index = 0; randNum < scale; index++)
			scale += scale-1;
		
		return population.get(index);
	}
}
