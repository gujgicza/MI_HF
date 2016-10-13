package mlp;

import java.util.ArrayList;

public class NeuronLayer {
	
	ArrayList<Neuron> neurons;
	NeuronLayer prevLayer;
	NeuronLayer nextLayer;
	
	public NeuronLayer(ArrayList<Neuron> neurons) {
		// TODO Auto-generated constructor stub
		this.neurons = neurons;
	}

	public ArrayList<ArrayList<Double>> getWeights() {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Double>> weightsL = new ArrayList<>();
		for (Neuron neuron : neurons) {
			weightsL.add(neuron.getWeights());
		}
		return weightsL;
	}

	public ArrayList<Double> getLayerOutputs(ArrayList<Double> input) {
		// TODO Auto-generated method stub
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
