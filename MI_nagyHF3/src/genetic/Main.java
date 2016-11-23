package genetic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. line backpack
		String s = br.readLine();
		String[] params = s.split(" ");		
		int backpackHeight = Integer.parseInt(params[0]);
		int backpackWidth = Integer.parseInt(params[1]);
				
		// 2. line number of items
		s = br.readLine();
		params = s.split(" ");	
		int itemNum = Integer.parseInt(params[0]);
		
		// item parameters
		List<Item> items = new ArrayList<>();
		for (int i = 0; i < itemNum; i++) {
			s = br.readLine();
			params = s.split(" ");
			items.add(new Item(Integer.parseInt(params[0]), Integer.parseInt(params[1])));
		}
		
		// parameters
		double mutateCh = 0.3;
		int entityNumInGen = 10;
		int maxGen = 15;
		double parentRatio = 0.3;
		
		// create the first population
		Population population = new Population(items, backpackWidth, backpackHeight, mutateCh, entityNumInGen, maxGen, parentRatio);
		
		// find the optimum
		population.evolve();
		
		// write out the matrix
		//population.sortPop();
		Entity best = population.population.get(0);
		BackPack solution = best.fenotype;
		
		for (int i = 0; i < solution.height; i++) {
			for (int j = 0; j < solution.width; j++) {
				System.out.print(solution.matrix[i][j]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}

}
