package mlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class IOHandler {
	
	// Reader part:
	
	ArrayList<Integer> getArchitect(BufferedReader br) throws IOException {
		String s = br.readLine();
		String[] params = s.split(",");
		ArrayList<Integer> architecture = new ArrayList<>();
		for (int i = 0; i < params.length; i++) 
			architecture.add(Integer.parseInt(params[i]));
		return architecture;
	}
	
	ArrayList<ArrayList<Double>> getWeights(BufferedReader br, ArrayList<Integer> layerSizes) throws IOException {
		ArrayList<ArrayList<Double>> allWeightsAndBiases = new ArrayList<>();
		ArrayList<ArrayList<Double>> neuronWeightsAndBiases;
		ArrayList<Double> weightsAndBias;
		String s;
		String[] inputWeights;
 		// iterate over the layers
		for (int i = 0; i < layerSizes.size(); i++) {
			neuronWeightsAndBiases = new ArrayList<>();

			// iterate over the neurons
			for (int j = 0; j < layerSizes.get(i); j++) {
				s = br.readLine();
				inputWeights = s.split(",");
				weightsAndBias = new ArrayList<>();

				// iterate over the neuron's weights
				for (int k = 0; k < inputWeights.length; k++)
					weightsAndBias.add(Double.parseDouble(inputWeights[k]));
				neuronWeightsAndBiases.add(weightsAndBias);
			}
			allWeightsAndBiases.addAll(neuronWeightsAndBiases);
		}
		return allWeightsAndBiases;
	}
	
	ArrayList<Double> getBiases(ArrayList<ArrayList<Double>> weightsAndBiases) {
		ArrayList<Double> biases = new ArrayList<>();
		for (ArrayList<Double> neuronWeightsAndBiases : weightsAndBiases) {
			biases.add(neuronWeightsAndBiases.remove(neuronWeightsAndBiases.size()-1));
		}
		return biases;
	}
	
	int getInputSetNumber(BufferedReader br) throws IOException{
		String num = br.readLine();
		return Integer.parseInt(num);
	}
	
	ArrayList<ArrayList<Double>> getInputs(BufferedReader br, int trainingNum) throws IOException {
		ArrayList<ArrayList<Double>> allInput = new ArrayList<>();
		ArrayList<Double> oneInput;
		String s;
		String[] in;
		for (int i = 0; i < trainingNum; i++) {
			oneInput = new ArrayList<>();
			s = br.readLine();
			in = s.split(",");
			for (int j = 0; j< in.length; j++) {
				oneInput.add(Double.parseDouble(in[j]));
			}
			allInput.add(oneInput);
		}		
		return allInput;
	}
	
	public ArrayList<Double> getTrainingParams(BufferedReader br) throws IOException {
		String s = br.readLine();
		String[] params = s.split(",");
		ArrayList<Double> trainingParams = new ArrayList<>();
		for (int i = 0; i < params.length; i++)
			trainingParams.add(Double.parseDouble(params[i]));
		return trainingParams;
	}
	
	public ArrayList<ArrayList<Double>> getExpectedOutputs(ArrayList<ArrayList<Double>> allInputs, int inputSize,
			int outputSize) {
		ArrayList<ArrayList<Double>> expectedOutputs = new ArrayList<>();
		for (ArrayList<Double> input : allInputs) {
			ArrayList<Double> oneExpectedOutput = new ArrayList<>();
			for (int i = inputSize; i < inputSize + outputSize; i++)
				oneExpectedOutput.add(input.remove(i));
			expectedOutputs.add(oneExpectedOutput);
		}
		return expectedOutputs;
	}
	
	// Writer part:
	// TODO: write to any ostream 
	
	public void writeArchitecture(ArrayList<Integer> arc) {
		String string = "";
		boolean isFirst = true;
		for (int i = 0; i < arc.size(); i++) {
			if (!isFirst)
				string += ",";
			else
				isFirst = false;
			string += arc.get(i).toString();
		}
		System.out.println(string);
	}
	
	public void writeTrainingNum(int trainingnum) {
		System.out.println(trainingnum);
	}
	
	public void writeWeightsAndBiases(ArrayList<ArrayList<ArrayList<Double>>> weights) {
		StringBuilder string = new StringBuilder();
		
		boolean isFirstNeuron = true;
		for (ArrayList<ArrayList<Double>> layerW : weights) {
			for (ArrayList<Double> neuronW : layerW) {
				if (!isFirstNeuron)
					string.append("\n");
				else
					isFirstNeuron = false;
				
				boolean isFirstW = true;
				for (Double w : neuronW) {
					if (!isFirstW) 
						string.append(",");
					else
						isFirstW = false;
					string.append(w);
				}
			}
			
		}
		System.out.println(string);
	}

	public void writeAllOutputs(ArrayList<ArrayList<Double>> allOutputs) {
		StringBuilder string = new StringBuilder();
		
		boolean isFirstOutput = true;
		for (ArrayList<Double> output : allOutputs) {
			if (!isFirstOutput)
				string.append("\n");
			else
				isFirstOutput = false;
			
			boolean isFirstDouble = true;
			for (Double double1 : output) {
				if (!isFirstDouble)
					string.append(",");
				else
					isFirstDouble = false;
				string.append(double1);
			}
		}
		System.out.println(string);
	}

	public void writePartialDerivates(ArrayList<ArrayList<ArrayList<Double>>> allPartialDerivates) {
		writeWeightsAndBiases(allPartialDerivates);	
	}

	public void writeSquareErrors(ArrayList<Double> squareErrors) {
		for (Double double1 : squareErrors) {
			System.out.println(double1);
		}	
	}

}
