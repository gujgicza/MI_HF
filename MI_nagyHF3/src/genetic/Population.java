package genetic;

import java.util.ArrayList;
import java.util.Collections;
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
	
	public Population(List<Item> items, int backpackW, int backpackH, double mutateCh, int entityNumInGen, int maxGen, double parentRatio) {
		this.items = items;
		backpackWidth = backpackW;
		backpackHeight = backpackH;
		
		mutateChance = mutateCh;
		entityNumInGeneration = entityNumInGen;
		maxGenerations = maxGen;
		chooseRatio = parentRatio;
		
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
		Random rand = new Random();
		ArrayList<Entity> parents = new ArrayList<>();
		
		for (int generation = 0; generation < maxGenerations; generation++) {
			parents = chooseParents();
			Entity parent1;
			Entity parent2;
			
			for (int childNum = 0; childNum < parents.size(); childNum++) {
				parent1 = parents.get(rand.nextInt(parents.size()));
				parent2 = parents.get(rand.nextInt(parents.size()));
				population.add(parent1.crossover(parent2));
			}
			sortPop();
			population = population.subList(0, entityNumInGeneration);
		}
	}

	private void sortPop() {
		Collections.sort(population, 
				(a, b) -> a.fittness < b.fittness ? 1 : a.fittness == b.fittness ? 0 : -1); // ez is rossz lehet
	}
	
	private ArrayList<Entity> chooseParents() {
		sortPop();
		ArrayList<Entity> parents = (ArrayList<Entity>) 
				population.subList(0, (int) Math.floor(entityNumInGeneration*chooseRatio));
		return parents;
	}
}
