package mlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class NNSolutionOne {

	public static void main(String[] args) throws IOException {

		NeuralNetworkHandler handler = new NeuralNetworkHandler();
		IOHandler iohandler = new IOHandler();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Reading the inputs
		ArrayList<Integer> architechture = iohandler.getArchitect(br);
		ArrayList<Integer> layerSizes = new ArrayList<>(architechture);
		int inputSize = layerSizes.remove(0);
		int outputSize = layerSizes.get(layerSizes.size() - 1);

		// Initializing and creating the Neural Network
		handler.setArchitecture(inputSize, layerSizes, outputSize);
		handler.setWeightsAndBiases(null, null);
		handler.createNeuralNetwork();
		
		// Calculate the solution
		 ArrayList<ArrayList<ArrayList<Double>>> weights = handler.getWeightsAndBiases();

		// Writing out the solution
		iohandler.writeArchitecture(architechture);
		br.close();
		iohandler.writeWeights(weights);
	}

}
