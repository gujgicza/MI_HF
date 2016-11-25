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
		
		// try 3 different algorithm
		Algorithm alg = new Algorithm(items, backpackWidth, backpackHeight);
		
		List<Integer> orderD = alg.firstFitDecreasing();		
		BackPack solutionD = new BackPack(backpackWidth, backpackHeight, items, orderD);
		
		List<Integer> orderH = alg.firstFitDecreasingHeight();		
		BackPack solutionH = new BackPack(backpackWidth, backpackHeight, items, orderH);
		
		List<Integer> orderW = alg.firstFitDecreasingWidth();		
		BackPack solutionW = new BackPack(backpackWidth, backpackHeight, items, orderW);
		
		// choose the minimum
		BackPack solution = solutionD;
		if (solution.getZeros() > solutionH.getZeros())
			solution = solutionH;
		if (solution.getZeros() > solutionW.getZeros())
			solution = solutionW;
		
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
