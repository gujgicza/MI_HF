package ffd;

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
		
		// try 6 different algorithm
		Algorithm alg = new Algorithm(items, backpackWidth, backpackHeight);
		List<BackPack> solutions = new ArrayList<>();
		
		List<Integer> orderD = alg.firstFitDecreasing();		
		solutions.add(new BackPack(backpackWidth, backpackHeight, items, orderD, true));
		solutions.add(new BackPack(backpackWidth, backpackHeight, items, orderD, false));

		List<Integer> orderH = alg.firstFitDecreasingHeight();		
		solutions.add(new BackPack(backpackWidth, backpackHeight, items, orderH, true));
		solutions.add(new BackPack(backpackWidth, backpackHeight, items, orderH, false));

		List<Integer> orderW = alg.firstFitDecreasingWidth();		
		solutions.add(new BackPack(backpackWidth, backpackHeight, items, orderW, true));
		solutions.add(new BackPack(backpackWidth, backpackHeight, items, orderW, false));

		
		// choose the minimum
		BackPack bestSolution = solutions.get(0);
		for (int i = 0; i < solutions.size(); i++)
			if (solutions.get(i).getZeros() < bestSolution.getZeros())
				bestSolution = solutions.get(i);
		
		// writing out
		boolean isFirstLine = true;
		for (int i = 0; i < bestSolution.height; i++) {
			if (isFirstLine)
				isFirstLine = false;
			else
				System.out.print("\n");
			boolean isFirstNum = true;
			for (int j = 0; j < bestSolution.width; j++) {
				if (isFirstNum)
					isFirstNum = false;
				else
					System.out.print("\t");
				System.out.print(bestSolution.matrix[i][j]);
			}
		}
	}

}
