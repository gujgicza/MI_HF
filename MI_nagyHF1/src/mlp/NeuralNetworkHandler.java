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
	
	ArrayList<Double> squareErrors;

	int trainingS;
	int validationS;

	double relationS;
	double mu;
	double epochNum;

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
	
	public void setTrainingParams(ArrayList<Double> trainingParams) {
		epochNum = trainingParams.remove(0);
		mu = trainingParams.remove(0);
		relationS = trainingParams.remove(0);
		trainingS = (int) Math.floor(inputs.size()*relationS);
		validationS = inputs.size() - trainingS;
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
	
	public void epoch() {
		for (int i = 0; i < trainingS; i++) {
			ArrayList<Double> realOutput = nn.getOutput(inputs.get(i));
			ArrayList<Double> currentErrors = calculateError(expectedOutput.get(i), realOutput);
			ArrayList<ArrayList<ArrayList<Double>>> partialDerivates = getPartialDerivates(currentErrors, inputs.get(i));
			nn.modifyWeightsAndBiases(partialDerivates, mu);
		}
	}

	private ArrayList<Double> calculateError(ArrayList<Double> expectedOutput, ArrayList<Double> realOutput) {
		ArrayList<Double> error = new ArrayList<>();
		for (int i = 0; i < expectedOutput.size(); i++)
			error.add(expectedOutput.get(i) - realOutput.get(i));
		return error;
	}

	public void training() {
		squareErrors = new ArrayList<>();
		for (int i = 0; i < epochNum; i++) {
			epoch();
			validate();
		}
	}

	public void validate() {
		double squareError = 0.0;
		ArrayList<Double> realOutput;
		ArrayList<Double> currentErrors;
		for (int i = 0; i < validationS; i++) {
			realOutput = nn.getOutput(inputs.get(i + trainingS));
			currentErrors = calculateError(expectedOutput.get(i + trainingS), realOutput);
			for (Double err : currentErrors) {
				squareError += err * err;
			}
		}
		squareError /= (validationS * outputSize);
		squareErrors.add(squareError);
	}

	
	public ArrayList<Double> getSquareErrors() {
		return squareErrors;
	}

}
