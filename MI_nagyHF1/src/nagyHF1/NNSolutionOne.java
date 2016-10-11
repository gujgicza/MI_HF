package nagyHF1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class NNSolutionOne {

	public static void main(String[] args) throws IOException {
		NeuralNetwork nn = new NeuralNetwork();
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
		
		String[] params = s.split(",");
		
//		for (int i = 0; i < params.length; i++)
//			System.out.println(params[i]);
		
		int input = Integer.parseInt(params[0]);
		int output = Integer.parseInt(params[params.length-1]);
		
		int[] hidden = null;
		for (int i = 1; i < params.length -1; i++)
			hidden[i-1] = Integer.parseInt(params[i]);

		

	}

}
