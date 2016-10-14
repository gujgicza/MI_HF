package mlp;

import java.util.ArrayList;

public class NeuronLayer {
	
	ArrayList<Neuron> neurons;
	NeuronLayer prevLayer;
	NeuronLayer nextLayer;
	
	public NeuronLayer(ArrayList<Neuron> neurons) {
		this.neurons = neurons;
		prevLayer = null;
		nextLayer = null;
	}

	public ArrayList<ArrayList<Double>> getWeightsAndBiases() {
		ArrayList<ArrayList<Double>> weightsL = new ArrayList<>();
		for (Neuron neuron : neurons) {
			weightsL.add(neuron.getWeightsAndBias());
		}
		return weightsL;
	}

	public ArrayList<Double> getLayerOutputs(ArrayList<Double> input) {
		ArrayList<Double> outputs = new ArrayList<>();
		ArrayList<Double> param;
		if (prevLayer == null) 
			param = input;
		else 
			param = prevLayer.getLayerOutputs(input);
		for (Neuron neuron : neurons) 
				outputs.add(neuron.getOutput(param));
		return outputs;
		
	}

	

}
