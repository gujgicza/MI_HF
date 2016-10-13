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

			for (int i = 0; i < layerSizes.get(0); i++) {
				ArrayList<Double> currNeuronWeights = new ArrayList<>();
				for (int j = 0; j < inputSize; j++) {
					currNeuronWeights.add(new Random().nextGaussian() * 0.1);
				}
				weightParams.add(currNeuronWeights);
				biasParams.add(0.0);
			}

			for (int i = 1; i < layerSizes.size(); i++) {
				for (int k = 0; k < layerSizes.get(i); k++) {
					ArrayList<Double> currNeuronWeights = new ArrayList<>();
					for (int j = 0; j < layerSizes.get(i - 1); j++) {
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
	
	public ArrayList<ArrayList<ArrayList<Double>>> getWeights() {
		return nn.getWeights();
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
	
	public ArrayList<ArrayList<Double>> getPartialDerivates(ArrayList<Double> Errors) {		
		return nn.backPropagation(Errors);		
	}
	
	public void training() {
		for (int i = 0; i < trainingS; i++) {
			ArrayList<Double> realOutput = nn.getOutput(inputs.get(i));
			ArrayList<Double> currentErrors = nn.calculateError(expectedOutput.get(i), realOutput);
			ArrayList<ArrayList<Double>> partialDerivates = getPartialDerivates(currentErrors);
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