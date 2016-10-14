package mlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NNSolutionThree {

	public static void main(String[] args) throws IOException {
		NeuralNetworkHandler handler = new NeuralNetworkHandler();
		IOHandler iohandler = new IOHandler();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Reading the inputs : architecture
		ArrayList<Integer> architechture = iohandler.getArchitect(br);
		ArrayList<Integer> layerSizes = new ArrayList<>(architechture);
		int inputSize = layerSizes.remove(0);
		int outputSize = layerSizes.get(layerSizes.size() - 1);

		// Reading the inputs : weights
		ArrayList<ArrayList<Double>> allWeights = iohandler.getWeights(br, layerSizes);
		ArrayList<Double> allBiases = iohandler.getBiases(allWeights);

		// Reading the inputs : input number
		int inputNum = iohandler.getInputSetNumber(br);

		// Reading the inputs : input vectors
		ArrayList<ArrayList<Double>> allInputs = iohandler.getInputs(br, inputNum);

		// Initializing and creating the Neural Network
		handler.setArchitecture(inputSize, layerSizes, outputSize);
		handler.setWeightsAndBiases(allWeights, allBiases);
		handler.setInputs(allInputs);
		handler.createNeuralNetwork();
		
		// Creating the errors
		ArrayList<Double> errors = new ArrayList<>();
		for (int i = 0; i < inputSize; i++)
			errors.add(1.0);

		// Calculate the solution
		handler.getAllOutputs();	// to calculate the outputs, used when calculating partialDerivates
		// S=1
		ArrayList<ArrayList<ArrayList<Double>>> allPartialDerivates = handler.getPartialDerivates(errors, allInputs.get(0));

		// Writing out the solution
		iohandler.writeArchitecture(architechture);
		iohandler.writePartialDerivates(allPartialDerivates);
		br.close();
	}

}
