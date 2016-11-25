package ffd;

import java.util.List;

public class BackPack {
	
	int[][] matrix;
	int height;
	int width;

	public BackPack(int capacityX, int capacityY, List<Item> items, List<Integer> order) {
		height = capacityY;
		width = capacityX;
		matrix = new int[height][width];
		
		packing(items, order);
	}
	
	private void packing(List<Item> items, List<Integer> order) {
		// in order of the permutation try to fit the items to the backpack
		for (Integer number : order) {
			Item currItem = items.get(number);
			searchPlace:
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j ++)
					if (matrix[i][j] == 0) {
						// item fits in
						if (height-i >= currItem.height && width-j >= currItem.width) {
							int checkSum = 0; // cheks if there is 0 everywhere
							for (int itemI = i; itemI < i + currItem.height; itemI++)
								for (int itemJ = j; itemJ < j + currItem.width; itemJ++)
									checkSum += matrix[itemI][itemJ];
							if (checkSum == 0) {
								for (int itemI = i; itemI < i + currItem.height; itemI++)
									for (int itemJ = j; itemJ < j + currItem.width; itemJ++)
										matrix[itemI][itemJ] = number + 1; // because the index strats with 0
								break searchPlace;
							}
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
