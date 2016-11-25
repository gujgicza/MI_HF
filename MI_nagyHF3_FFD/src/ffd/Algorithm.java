package ffd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Algorithm {
	
	List<Item> items;
	int backpackWidth;
	int backpackHeight;
	
	public Algorithm(List<Item> itemList, int w, int h) {
		items = itemList;
		backpackWidth = w;
		backpackHeight = h;
		
	}
	
	public List<Integer> firstFitDecreasing() {
		List<Integer> order = new ArrayList<>();
		for (int j = 0; j < items.size(); j++)
			order.add(j);
		
		Collections.sort(order, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(items.get(o2).height * items.get(o2).width, items.get(o1).height * items.get(o1).width);
			}		
		});
		
		return order;
	}

	public List<Integer> firstFitDecreasingHeight() {
		List<Integer> order = new ArrayList<>();
		for (int j = 0; j < items.size(); j++)
			order.add(j);
		
		Collections.sort(order, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(items.get(o2).height, items.get(o1).height);
			}		
		});
		
		return order;
	}
	
	public List<Integer> firstFitDecreasingWidth() {
		List<Integer> order = new ArrayList<>();
		for (int j = 0; j < items.size(); j++)
			order.add(j);
		
		Collections.sort(order, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(items.get(o2).width, items.get(o1).width);
			}		
		});
		
		return order;
	}
}
