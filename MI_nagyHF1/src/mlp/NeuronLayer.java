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

	public ArrayList<Double> calculateDeltas(ArrayList<Double> currentErrors) {
		ArrayList<Double> deltasL = new ArrayList<>();
		NeuronLayer nextLayerParams;
		ArrayList<Double> nextLayerDeltas;
		int idx = 0;
		if (nextLayer == null) {
			nextLayerDeltas = currentErrors;
			ArrayList<Neuron> layerNeurons = new ArrayList<>();
			for (int i = 0; i < currentErrors.size(); i++) {
				ArrayList<Double> errorW = new ArrayList<>();
				for (int j = 0; j < neurons.size(); j++) {
					if (j == i)
						errorW.add(1.0);
					else
						errorW.add(0.0);		
				}
				Neuron errorNeuron = new Neuron(errorW, currentErrors.get(i));
				errorNeuron.delta = currentErrors.get(i);
				layerNeurons.add(errorNeuron);
			}
			NeuronLayer errorLayer = new NeuronLayer(layerNeurons);
			nextLayerParams = errorLayer;
		}
		else {
			nextLayerParams = nextLayer;
			nextLayerDeltas = nextLayer.calculateDeltas(currentErrors);
		}
		for (Neuron neuron : neurons) {
			deltasL.add(neuron.calculateDelta(nextLayerParams, nextLayerDeltas,  idx++));
		}
		return deltasL;
		
	}

	

}
