package mlp;

import java.util.ArrayList;
import java.util.Random;

public class NeuralNetworkHandler {

	NeuralNetwork nn;
	int inputSize;
	ArrayList<Integer> layerSizes;
	int outputSize;

	ArrayList<ArrayList<Double>> weights;
	ArrayList<Double> biases;

	ArrayList<ArrayList<Double>> inputs;
	ArrayList<ArrayList<Double>> expectedOutput;

	int trainingS;
	int validationS;

	double relationS;
	double mu;
	int epochNum;

	public void setArchitecture(int in, ArrayList<Integer> hidden, int out) {
		inputSize = in;
		layerSizes = hidden;
		outputSize = out;
	}

	public void setWeightsAndBiases(ArrayList<ArrayList<Double>> w, ArrayList<Double> b) {
		if (w == null && b == null) {
			ArrayList<ArrayList<Double>> weightParams = new ArrayList<>();
			ArrayList<Double> biasParams = new ArrayList<>();
			
			int size;
			for (int i = 0; i < layerSizes.size(); i++) {
				if (i == 0)
					size = inputSize;
				else
					size = layerSizes.get(i-1);
				for (int k = 0; k < layerSizes.get(i); k++) {
					ArrayList<Double> currNeuronWeights = new ArrayList<>();
					for (int j = 0; j < size; j++) {
						currNeuronWeights.add(new Random().nextGaussian() * 0.1);
					}
					weightParams.add(currNeuronWeights);
					biasParams.add(0.0);
				}
			}
			weights = weightParams;
			biases = biasParams;
		} else {
			weights = w;
			biases = b;
		}
	}

	public void setInputs(ArrayList<ArrayList<Double>> in) {
		inputs = in;
	}
	
	public void setExpectedOutput(ArrayList<ArrayList<Double>> expectedOut) { 
		expectedOutput = expectedOut;
	}
	
	public void createNeuralNetwork() {
		nn = new NeuralNetwork(inputSize, layerSizes, weights, biases);
	}
	
	public ArrayList<ArrayList<ArrayList<Double>>> getWeightsAndBiases() {
		return nn.getWeightsAndBiases();
	}	
	
	public ArrayList<Double> getOutput(ArrayList<Double> input) {
		return nn.getOutput(input);
	}
	
	public ArrayList<ArrayList<Double>> getAllOutputs() {
		ArrayList<ArrayList<Double>> allOutputs = new ArrayList<>();
		for (int i = 0; i < inputs.size(); i++)
			allOutputs.add(getOutput(inputs.get(i)));
		return allOutputs;
	}
	
	public ArrayList<ArrayList<ArrayList<Double>>> getPartialDerivates(ArrayList<Double> errors, ArrayList<Double> input) {		
		nn.backPropagation(errors);
		return nn.getPartialDerivates(input);
	}
	
	public void training() {
		for (int i = 0; i < trainingS; i++) {
			ArrayList<Double> realOutput = nn.getOutput(inputs.get(i));
			ArrayList<Double> currentErrors = nn.calculateError(expectedOutput.get(i), realOutput);
			ArrayList<ArrayList<ArrayList<Double>>> partialDerivates = getPartialDerivates(currentErrors, inputs.get(i));
			nn.modifyWeights(partialDerivates);
		}
	}

	public void epoch() {
		for (int i = 0; i < epochNum; i++) {
			training();
			validate();
		}
	}

	public double validate() {
		double squareError = 0.0;
		ArrayList<ArrayList<Double>> errors = new ArrayList<>();
		for (int i = 0; i < validationS; i++) {
			ArrayList<Double> realOutput = nn.getOutput(inputs.get(i + trainingS));
			ArrayList<Double> currentErrors = nn.calculateError(expectedOutput.get(i + trainingS), realOutput);
			for (Double err : currentErrors) {
				squareError += err * err;
			}
		}
		squareError /= (validationS + errors.get(0).size());
		return squareError;
	}

}
