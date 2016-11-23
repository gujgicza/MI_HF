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
		// TODO Auto-generated method stub
		
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
