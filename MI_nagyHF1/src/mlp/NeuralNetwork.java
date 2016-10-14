package mlp;

import java.util.ArrayList;
import java.util.Random;

public class NeuralNetwork {
	
	int  inputSize;
	ArrayList<NeuronLayer> layers;
	//ArrayList<Double> expectedOutPut;
	
	public NeuralNetwork(int inputSize, ArrayList<Integer> layerSizes,
			ArrayList<ArrayList<Double>> weights, ArrayList<Double> biases) {
		int idx = 0;;
		this.inputSize = inputSize;
		layers = new ArrayList<>();
		
		ArrayList<Neuron> layerNeurons;
		// layers
		for (int i = 0; i < layerSizes.size(); i++) {
			int size;
			if (i == 0)
				size = inputSize;
			else
				size = layerSizes.get(i - 1);
			// neurons
			layerNeurons = new ArrayList<>();
			for (int k = 0; k < layerSizes.get(i); k++) {

				// weights
				ArrayList<Double> neuronWeights = new ArrayList<>();
				for (int j = 0; j < size; j++) {
					neuronWeights.add(weights.get(idx).get(j));
				}
				if (i < layerSizes.size() - 1)
					layerNeurons.add(new Neuron(neuronWeights, biases.get(idx)));
				else
					layerNeurons.add(new OutputNeuron(neuronWeights, biases.get(idx)));
				idx++;
			}
			addLayer(new NeuronLayer(layerNeurons));
		}
	}
	
	private void addLayer(NeuronLayer layer) {
		if (!layers.isEmpty()) {
			NeuronLayer currLastLayer = layers.get(layers.size()-1);
			layer.prevLayer = currLastLayer;
			currLastLayer.nextLayer = layer;
		}
		layers.add(layer);
	}
	
	public void clearOutput() {
		for (NeuronLayer neuronLayer : layers) {
			for (Neuron neuron : neuronLayer.neurons) {
				neuron.outputNonLinear = null;
			}
		}
	}
	
	public ArrayList<Double> getOutput(ArrayList<Double> input) {
		clearOutput();
		return layers.get(layers.size()-1).getLayerOutputs(input);
	}

	public ArrayList<Double> calculateError(ArrayList<Double> expectedOutput, ArrayList<Double> realOutput) {
		return null;
		// TODO Auto-generated method stub
		
	}

	// returns the partialDerivates of the weights and biases
	public ArrayList<ArrayList<Double>> backPropagation(ArrayList<Double> currentErrors) {
		// TODO Auto-generated method stub
		return null;
	}

	public void modifyWeights(ArrayList<ArrayList<Double>> partialDerivates) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<ArrayList<ArrayList<Double>>> getWeightsAndBiases() {
		ArrayList<ArrayList<ArrayList<Double>>> weightsNN = new ArrayList<>();
		for (NeuronLayer layer : layers) {
			weightsNN.add(layer.getWeightsAndBiases());
		}
		return weightsNN;
	}
	

}
