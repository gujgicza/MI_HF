package mlp;

import java.lang.reflect.Array;
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

	public ArrayList<ArrayList<Double>> getPartialDerivates(ArrayList<Double> input) {
		ArrayList<ArrayList<Double>> partialDerivatesL = new ArrayList<>();
		ArrayList<Double> params;
		if (prevLayer == null)
			params = input;
		else
			params = prevLayer.getLayerOutputs(input);
		for (Neuron neuron : neurons) {
			partialDerivatesL.add(neuron.getPartialDerivates(params));
		}
		return partialDerivatesL;
	}

	public void calculateDeltas(ArrayList<Double> currentErrors) {
		NeuronLayer nextLayerParams;
		int idx = 0;
		if (nextLayer == null) {
			for (int i = 0; i < currentErrors.size(); i++) {
				neurons.get(i).delta = currentErrors.get(i);
			}
		}
		else {
			nextLayerParams = nextLayer;
			nextLayer.calculateDeltas(currentErrors);

			for (Neuron neuron : neurons) {
				neuron.calculateDelta(nextLayerParams, idx++);
			}
		}
	}
}
