package nagyHF1;

import java.util.ArrayList;

public class Layer {
	
	ArrayList<Perceptron> neurons;
	Layer nextLayer;
	Layer prevLayer;
	
	ArrayList<Double> outputs;
	ArrayList<Double> deltas;
	
	public ArrayList<Perceptron> getNeurons() {
		return neurons;
	}
	
	public Layer(int neuronNum, int car) {
		neurons = new ArrayList<>();
		for (int i = 0; i < neuronNum; i++) {
			neurons.add(new Perceptron(car));
		}
		nextLayer = null;
		prevLayer = null;
		
		outputs = null;
		deltas = null;
	}
	
	public void setNextLayer(Layer next) {
		nextLayer = next;
	}
	
	public void setPrevLayer(Layer prev) {
		prevLayer = prev;
	}

	public ArrayList<Double> getLayerOutput() {
		if (outputs == null)
			calculateOutputs();
		return outputs;
	}

	private void calculateOutputs() {
		if (prevLayer == null) {
			// TODO: bemeneti vektor
		}
		outputs = new ArrayList<>();
		for (Perceptron perceptron : neurons) {
			outputs.add(perceptron.getOutput(prevLayer.getLayerOutput()));
		}
		
	}
	
	public ArrayList<Double> getLayerDeltas() {
		if (deltas == null)
			calculateDeltas();
		return deltas;
	}

	private void calculateDeltas() {
		if (nextLayer == null) {
			// TODO: végén csak a delta
		}
		deltas = new ArrayList<>();
		for (Perceptron perceptron : neurons) {
			deltas.add(perceptron.getDelta(nextLayer.getNeurons()));
		}
	}
}
