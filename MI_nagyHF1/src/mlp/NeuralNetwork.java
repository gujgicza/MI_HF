package mlp;

import java.util.ArrayList;
import java.util.Random;

public class NeuralNetwork {
	
	int  inputSize;
	ArrayList<NeuronLayer> layers;
	//ArrayList<Double> expectedOutPut;
	
	public NeuralNetwork(int inputSize, ArrayList<Integer> layerSizes,
			ArrayList<ArrayList<Double>> weights, ArrayList<Double> biases) {
		//TODO: ez nagyon ronda
		int idx = 0;;
		this.inputSize = inputSize;
		layers = new ArrayList<>();
		
		if(weights == null && biases == null) {
			if (layerSizes.size() > 1) {
				addLayer(new NeuronLayer(layerSizes.get(0), inputSize));
				int i;
				for(i = 1; i < layerSizes.size() - 1; i++)
					addLayer(new NeuronLayer(layerSizes.get(i), layerSizes.get(i - 1)));
				addLayer(new LastNeuronLayer(layerSizes.get(i), layerSizes.get(i-1)));

			} else {
				addLayer(new NeuronLayer(layerSizes.get(0), inputSize));
			}
		}
		else {
			if (layerSizes.size() > 1) {
				ArrayList<Neuron> firstLayerNeurons = new ArrayList<>();
				for (int j = 0; j < layerSizes.get(0); j++) {
					ArrayList<Double> neuronWeights = new ArrayList<>();
					for (int k = 0; k < inputSize; k++) {
						neuronWeights.add(weights.get(idx).get(k));
					}
					firstLayerNeurons.add(new Neuron(neuronWeights, biases.get(idx)));
					idx++;
				}
				addLayer(new NeuronLayer(firstLayerNeurons));

				for (int i = 1; i < layerSizes.size() - 1; i++) {
					ArrayList<Neuron> hiddenLayerNeurons = new ArrayList<>();
					for (int j = 0; j < layerSizes.get(i); j++) {
						ArrayList<Double> neuronWeights = new ArrayList<>();
						for (int k = 0; k < layerSizes.get(i - 1); k++) {
							neuronWeights.add(weights.get(idx).get(k));
						}
						hiddenLayerNeurons.add(new Neuron(neuronWeights, biases.get(idx)));
						idx++;
					}
					addLayer(new NeuronLayer(hiddenLayerNeurons));
				}

				ArrayList<Neuron> lastLayerNeurons = new ArrayList<>();
				for (int j = 0; j < layerSizes.get(layerSizes.size() - 1); j++) {
					ArrayList<Double> neuronWeights = new ArrayList<>();
					for (int k = 0; k < layerSizes.get(layerSizes.size() - 2); k++) {
						neuronWeights.add(weights.get(idx).get(k));
					}
					lastLayerNeurons.add(new OutputNeuron(neuronWeights, biases.get(idx)));
					idx++;
				}
				addLayer(new NeuronLayer(lastLayerNeurons));
			} else {
				ArrayList<Neuron> lastLayerNeurons = new ArrayList<>();
				for (int j = 0; j < layerSizes.get(layerSizes.size() - 1); j++) {
					ArrayList<Double> neuronWeights = new ArrayList<>();
					for (int k = 0; k < inputSize; k++) {
						neuronWeights.add(weights.get(idx).get(k));
					}
					lastLayerNeurons.add(new OutputNeuron(neuronWeights, biases.get(idx)));
					idx++;
				}
				addLayer(new NeuronLayer(lastLayerNeurons));
			}
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
	
	
	
	public ArrayList<Double> getOutput(ArrayList<Double> input) {
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

	public ArrayList<ArrayList<ArrayList<Double>>> getWeights() {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<ArrayList<Double>>> weightsNN = new ArrayList<>();
		for (NeuronLayer layer : layers) {
			weightsNN.add(layer.getWeights());
		}
		return weightsNN;
	}
	

}
