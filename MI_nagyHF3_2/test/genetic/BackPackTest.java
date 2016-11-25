package genetic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BackPackTest {
	ArrayList<Item> items;
	BackPack bp;
	
	@Before
	public void setUp() throws Exception {
		items = new ArrayList<>();
		items.add(new Item(2, 1));
		items.add(new Item(1, 1));
		items.add(new Item(1, 1));
		
		ArrayList<Integer> genotype =  new ArrayList<>();
		genotype.add(1);
		genotype.add(2);
		genotype.add(0);
		
		bp = new BackPack(2, 2, items, genotype);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		boolean isFirstLine = true;
		for (int i = 0; i < bp.height; i++) {
			if (isFirstLine)
				isFirstLine = false;
			else
				System.out.print("\n");
			boolean isFirstNum = true;
			for (int j = 0; j < bp.width; j++) {
				if (isFirstNum)
					isFirstNum = false;
				else
					System.out.print("\t");
				System.out.print(bp.matrix[i][j]);
			}
		}
	}

}
