package mlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NNSolutionFive {

	public static void main(String[] args) throws IOException {
		NeuralNetworkHandler handler = new NeuralNetworkHandler();
		IOHandler iohandler = new IOHandler();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Reading the inputs : Training parameters
		ArrayList<Double> trainingParams = iohandler.getTrainingParams(br);

		// Reading the inputs : architecture
		ArrayList<Integer> architechture = iohandler.getArchitect(br);
		ArrayList<Integer> layerSizes = new ArrayList<>(architechture);
		int inputSize = layerSizes.remove(0);
		int outputSize = layerSizes.get(layerSizes.size() - 1);

		// Reading the inputs : weights
		ArrayList<ArrayList<Double>> allWeights = null;
		ArrayList<Double> allBiases = null;

		// Reading the inputs : input number
		int inputNum = iohandler.getInputSetNumber(br);

		// Reading the inputs : input vectors
		ArrayList<ArrayList<Double>> allInputs = iohandler.getInputs(br, inputNum);
		ArrayList<ArrayList<Double>> allExpectedOutput = iohandler.getExpectedOutputs(allInputs, inputSize, outputSize);

		// Initializing and creating the Neural Network
		handler.setArchitecture(inputSize, layerSizes, outputSize);
		handler.setWeightsAndBiases(allWeights, allBiases);
		handler.setInputs(allInputs);
		handler.setExpectedOutput(allExpectedOutput);
		handler.setTrainingParams(trainingParams);
		handler.createNeuralNetwork();
		
		// Training the network
		handler.training();
		
		// Calculate the solution
		ArrayList<Double> squareErrors = handler.getSquareErrors();
		ArrayList<ArrayList<ArrayList<Double>>> weightsAndBiases = handler.getWeightsAndBiases();
		
		// Writing out the solution
		iohandler.writeSquareErrors(squareErrors);
		iohandler.writeArchitecture(architechture);
		iohandler.writeWeightsAndBiases(weightsAndBiases);
	}

}
