package genetic;

import java.util.ArrayList;
import java.util.List;

public class Population {
	List<Item> items;
	BackPack backpack;
	
	List<Entity> population;
	
	// parameters of genetic algorithm
	double mutateChance;
	int entityNumInGeneration;
	int maxGenerations;
	
	int currentGeneration;
	
	public Population(List<Item> items, BackPack backpack, double mutateCh, int entityNumInGen, int maxGen) {
		this.items = items;
		this.backpack = backpack;
		
		mutateChance = mutateCh;
		entityNumInGeneration = entityNumInGen;
		maxGenerations = maxGen;
		
		population = new ArrayList<Entity>();
		// create the first (random) population
		for (int i = 0; i < entityNumInGeneration; i++) {
			// TODO: gen should be a random permutation of the items serial numbers
			List<Integer> gen = new ArrayList<>();
			population.add(new Entity(backpack.width, backpack.height, items, gen));
		}
		currentGeneration = 0;
	}
	
	public void evolve() {
		// TODO
	}
	
	public void getPop() {
		// TODO
	}

	public void sortPop() {
		// TODO
	}
}
