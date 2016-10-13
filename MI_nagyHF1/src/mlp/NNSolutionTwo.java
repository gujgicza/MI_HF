package mlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class NNSolutionTwo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		NeuralNetworkHandler handler = new NeuralNetworkHandler();
		Writer writer = new Writer();
		
		// Reading the inputs : architecture
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
		
		String[] architecture = s.split(",");

		int inputSize = Integer.parseInt(architecture[0]);
		int outputLayerSize = Integer.parseInt(architecture[architecture.length-1]);
		
		ArrayList<Integer> layerSizes = new ArrayList<>();
		for (int i = 1; i < architecture.length -1; i++)
			layerSizes.add(Integer.parseInt(architecture[i]));
		layerSizes.add(outputLayerSize);
		
		// Reading the inputs : weights
		ArrayList<ArrayList<Double>> allWeights = new ArrayList<>();
		ArrayList<ArrayList<Double>> neurons;
		ArrayList<Double> weights;
		ArrayList<Double> biases = new ArrayList<>();
		String w;
		String[] inputWeights;
 		// iterate over the layers
		for (int i = 0; i < layerSizes.size(); i++) {
			neurons = new ArrayList<>();

			// iterate over the neurons
			for (int j = 0; j < layerSizes.get(i); j++) {

				w = br.readLine();
				inputWeights = w.split(",");

				weights = new ArrayList<>();

				// iterate over the neuron's weights
				for (int k = 0; k < inputWeights.length - 1; k++)
					weights.add(Double.parseDouble(inputWeights[k]));
				// and add the bias
				neurons.add(weights);
				biases.add(Double.parseDouble(inputWeights[inputWeights.length - 1]));
			}
			allWeights.addAll(neurons);
		}
		
		// Reading the inputs : training example number
		String num = br.readLine();
		int trainingNum = Integer.parseInt(num);

		// Reading the inputs : training examples
		ArrayList<ArrayList<Double>> inputs = new ArrayList<>();
		ArrayList<Double> oneInput;
		String in;
		String[] exampleInputs;
		for (int i = 0; i < trainingNum; i++) {
			oneInput = new ArrayList<>();
			in = br.readLine();
			exampleInputs = in.split(",");
			for (int j = 0; j< exampleInputs.length - 1; j++) {
				oneInput.add(Double.parseDouble(exampleInputs[j]));
			}
			inputs.add(oneInput);
		}
		
		// Initializing and creating the Neural Network
		handler.setArchitecture(inputSize, layerSizes, outputLayerSize);
		handler.setWeightsAndBiases(allWeights, biases);
		handler.setInputs(inputs);
		handler.createNeuralNetwork();
		
		ArrayList<ArrayList<Double>> outs = handler.getAllOutputs();
		String outNNSolutionTwo = writer.outputsToString(outs);

		// Writing out the solution
		System.out.println(s);
		System.out.println(outNNSolutionTwo);
		
	}

}
