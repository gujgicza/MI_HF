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
		String[] params = s.split("\t");		
		int backpackHeight = Integer.parseInt(params[0]);
		int backpackWidth = Integer.parseInt(params[1]);
				
		// 2. line number of items
		s = br.readLine();
		params = s.split("\t");	
		int itemNum = Integer.parseInt(params[0]);
		
		// item parameters
		List<Item> items = new ArrayList<>();
		for (int i = 0; i < itemNum; i++) {
			s = br.readLine();
			params = s.split("\t");
			items.add(new Item(Integer.parseInt(params[0]), Integer.parseInt(params[1])));
		}
		
		// parameters
		double mutateCh = 0.3;
		int entityNumInGen = 20;
		int maxGen = 500;
		
		// create the first population
		Population population = new Population(items, backpackWidth, backpackHeight, mutateCh, entityNumInGen, maxGen);
		
		// find the optimum
		population.evolve();
		
		// write out the matrix
		Entity best = population.population.get(0);
		BackPack solution = best.fenotype;
		
		boolean isFirstLine = true;
		for (int i = 0; i < solution.height; i++) {
			if (isFirstLine)
				isFirstLine = false;
			else
				System.out.print("\n");
			boolean isFirstNum = true;
			for (int j = 0; j < solution.width; j++) {
				if (isFirstNum)
					isFirstNum = false;
				else
					System.out.print("\t");
				System.out.print(solution.matrix[i][j]);
			}
		}
	}

}
