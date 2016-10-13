package nagyHF1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NNSolutionThree {

	public static void main(String[] args) throws IOException {

		NeuralNetwork nn = new NeuralNetwork();

		// Reading the inputs
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// First row: the Architecture
		String firstRow = br.readLine();
		String s;

		String[] params = firstRow.split(",");

		int inputSize = Integer.parseInt(params[0]);
		int outputLayerSize = Integer.parseInt(params[params.length - 1]);

		ArrayList<Integer> hiddenLayerSizes = new ArrayList<>();
		for (int i = 1; i < params.length - 1; i++)
			hiddenLayerSizes.add(Integer.parseInt(params[i]));

		// Next rows: the Weights
		// Creating the hidden layers
		ArrayList<Perceptron> perceptrons;
		ArrayList<Double> weights;
		Double bias;
		// iterate over the layers
		for (int i = 0; i < hiddenLayerSizes.size(); i++) {
			perceptrons = new ArrayList<>();

			// iterate over the neurons
			for (int j = 0; j < hiddenLayerSizes.get(i); j++) {

				s = br.readLine();
				params = s.split(",");

				weights = new ArrayList<>();

				// iterate over the neuron's weights
				for (int k = 0; k < params.length - 1; k++)
					weights.add(Double.parseDouble(params[k]));
				// and add the bias
				bias = Double.parseDouble(params[params.length - 1]);

				perceptrons.add(new Perceptron(weights, bias, false));
			}

			nn.addLayer(new Layer(perceptrons));
		}

		// Creating the output layer
		// iterate over the neurons
		perceptrons = new ArrayList<>();
		for (int j = 0; j < outputLayerSize; j++) {

			s = br.readLine();
			params = s.split(",");

			weights = new ArrayList<>();

			// iterate over the neuron's weights
			for (int k = 0; k < params.length - 1; k++)
				weights.add(Double.parseDouble(params[k]));
			// and add the bias
			bias = Double.parseDouble(params[params.length - 1]);

			perceptrons.add(new Perceptron(weights, bias, true));
		}
		nn.addLayer(new Layer(perceptrons));

		// Next row: the number of the inputs
		s = br.readLine();
		int inputNum = Integer.parseInt(s);
		
		// Last rows: the Input vectors
		String outputVectors = "";
		ArrayList<Double> inputLayer;
		ArrayList<Double> deltas;
		// iterate over number of given input vectors 
		for (int j = 0; j < inputNum; j++) {
			s = br.readLine();
			params = s.split(",");
			inputLayer = new ArrayList<>();
			deltas = new ArrayList<>();

			// iterate over the input vectors
			for (int i = 0; i < inputSize; i++) {
				inputLayer.add(Double.parseDouble(params[i]));
			}
			deltas = new ArrayList<>();
			for (int i = 0; i< outputLayerSize; i++)
				deltas.add(1.0);	// csak ebben a feladatban 1.0, ki kéne vonni az elvárt kimenetetből a kiszámolt valósat
			outputVectors += nn.getParcDerivates(inputLayer, deltas);
		}
		
		// Writing the output
		System.out.println(firstRow);
//		for (ArrayList<Double> output : outputVectors) {
//			String line = "";
//			boolean first = true;
//			for (Double double1 : output) {
//				if (!first)
//					line += ",";
//				else
//					first = false;
//				line += double1.toString();
//			}
//			System.out.println(line);
//		}
		System.out.println(outputVectors);
		
		

		
	}

}
