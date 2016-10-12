package nagyHF1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NNSolutionOne {

	public static void main(String[] args) throws IOException {
		NeuralNetwork nn = new NeuralNetwork();
		
		// Reading the inputs
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
		
		String[] params = s.split(",");

		int inputSize = Integer.parseInt(params[0]);
		int outputLayerSize = Integer.parseInt(params[params.length-1]);
		
		ArrayList<Integer> hiddenLayerSizes = new ArrayList<>();
		for (int i = 1; i < params.length -1; i++)
			hiddenLayerSizes.add(Integer.parseInt(params[i]));
		
		
		// Creating and initializing the network
		ArrayList<Double> inputLayer = new ArrayList<>();
		for (int i = 0; i < inputSize; i++) {
			// itt lesz majd a rendes bemeneti értékek hozzáadása
			inputLayer.add(1.0);
		}
		
		// Creating the hidden layers
		for (int i = 0; i < hiddenLayerSizes.size(); i++) {
			int size;
			if (nn.layers.isEmpty())
				size = inputSize;
			else
				size = nn.layers.get(nn.layers.size()-1).neurons.size();
			Layer tmp = new Layer(hiddenLayerSizes.get(i), size, false);
			
			nn.addLayer(tmp);
		}
		
		// Creating the output layer
		int size;
		if (nn.layers.isEmpty())
			size = inputSize;
		else
			size = nn.layers.get(nn.layers.size()-1).neurons.size();
		
		nn.addLayer(new Layer(outputLayerSize, size, true));
		
		
		// Writing the output		
		String weights = nn.getWeights();
		
		System.out.println(s);
		System.out.println(weights);
	}

}
