package mlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class NNSolutionOne {

	public static void main(String[] args) throws IOException {

		// return;

		//NeuralNetworkHandler handler = new NeuralNetworkHandler();
		//IOHandler iohandler = new IOHandler();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Reading the inputs
		//ArrayList<Integer> architechture = iohandler.getArchitect(br);
		//ArrayList<Integer> layerSizes = new ArrayList<>(architechture);
		//int inputSize = layerSizes.remove(0);
		//int outputSize = layerSizes.get(layerSizes.size() - 1);
		String s = br.readLine();
		String[] params = s.split(",");
		int inputSize = Integer.parseInt(params[0]);
		ArrayList<Integer> layerSizes = new ArrayList<>();
		for (int i = 1; i < params.length; i++)
			layerSizes.add(Integer.parseInt(params[i]));

		// Initializing and creating the Neural Network
		//handler.setArchitecture(inputSize, layerSizes, outputSize);
		//handler.setWeightsAndBiases(null, null);
		// handler.createNeuralNetwork();
		
		//DEBUG:
//		if (layerSizes.size() > 20)
//			System.out.println("nagyháló");

		// Calculate the solution
		// ArrayList<ArrayList<ArrayList<Double>>> weights =
		// handler.getWeights();

		// Writing out the solution
		//iohandler.writeArchitecture(architechture);
		System.out.println(s);
		writeNNSolutionOne(inputSize, layerSizes);
		br.close();
		// iohandler.writeWeights(weights);
		// System.out.println("Beolvasta");
		// System.out.println(handler.getWeightsInStringForNNSolutionOne());
		// System.out.flush();
	}

	public static void writeNNSolutionOne(int inputSize, ArrayList<Integer> layerSizes) {
		StringBuilder stringB =  new StringBuilder();
		Random random = new Random();
		int size = inputSize;
		boolean isFirstNeuron = true;
		for (int i = 0; i < layerSizes.size(); i++) {
			for (int k = 0; k < layerSizes.get(i); k++) {
				if (!isFirstNeuron)
					stringB.append("\n");
				else
					isFirstNeuron = false;
				boolean isFirstW = true;
				for (int j = 0; j < size; j++) {
					if (!isFirstW)
						stringB.append(",");
					else
						isFirstW = false;
					stringB.append(random.nextGaussian() * 0.1);
				}
				stringB.append(",");
				stringB.append(0.0);
			}
			size = layerSizes.get(i);
		}
		System.out.println(stringB);
	}

}
