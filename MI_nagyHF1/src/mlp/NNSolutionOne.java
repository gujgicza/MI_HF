package mlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NNSolutionOne {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		NeuralNetworkHandler handler = new NeuralNetworkHandler();
		Writer writer = new Writer();
		
		// Reading the inputs
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
		
		String[] params = s.split(",");

		int inputSize = Integer.parseInt(params[0]);
		int outputLayerSize = Integer.parseInt(params[params.length-1]);
		
		ArrayList<Integer> layerSizes = new ArrayList<>();
		for (int i = 1; i < params.length -1; i++)
			layerSizes.add(Integer.parseInt(params[i]));
		layerSizes.add(outputLayerSize);
		
		// Initializing and creating the Neural Network
		handler.setArchitecture(inputSize, layerSizes, outputLayerSize);
		handler.setWeightsAndBiases(null, null);
		handler.createNeuralNetwork();
		ArrayList<ArrayList<ArrayList<Double>>> weights = handler.getWeights();
		
		String outNNSolutionOne = writer.weightsToString(weights);
		
		// Writing out the solution
		System.out.println(s);
		System.out.println(outNNSolutionOne);

	}

}
