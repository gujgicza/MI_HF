package genetic;

import java.util.List;

public class BackPack {
	
	int[][] matrix;
	int height;
	int width;

	public BackPack(int capacityX, int capacityY, List<Item> items, List<Integer> genotype) {
		height = capacityY;
		width = capacityX;
		matrix = new int[height][width];
		
		packing(items, genotype);
	}
	
	private void packing(List<Item> items, List<Integer> genotype) {
		// TODO lehetne szebb....
		// in order of the permutation (genotype) try to fit the items to the backpack
		for (Integer number : genotype) {
			Item currItem = items.get(number);
			searchPlace:							// lehet rossz
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j ++)
					if (matrix[i][j] == 0) {
						// item fits in
						if (height-i >= currItem.height && width-j >= currItem.width) {
							for (int itemI = i; itemI < i + currItem.height; itemI++)
								for (int itemJ = j; itemJ < j + currItem.width; itemJ++)
									matrix[itemI][itemJ] = number + 1; // because the index starts with 0
							break searchPlace;
						}
					}
		}
	}

	public int getZeros() {
		int zeroNum = 0;
		
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				if (matrix[i][j] == 0)
					zeroNum += 1;
		
		return zeroNum;
	}

}
