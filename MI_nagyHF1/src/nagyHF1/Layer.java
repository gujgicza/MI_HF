package nagyHF1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Layer {
	
	ArrayList<Perceptron> neurons;
	Layer nextLayer;
	Layer prevLayer;
	
	ArrayList<Double> outputs;
	ArrayList<Double> deltas;
	
	public ArrayList<Perceptron> getNeurons() {
		return neurons;
	}
	
	
	public Layer(int neuronNum, int cardinality) {
			
		neurons = new ArrayList<>();
		for (int i = 0; i < neuronNum; i++) {
			neurons.add(new Perceptron(cardinality));
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
		/* perceptronnak nem kéne tudnia a layerekről:
		 * getDelta paraméterei delták, és a hozzá tartozó súlyvektorok kéne legyenek.
		 */
		if (nextLayer == null) {
			// TODO: végén csak a delta
		}
		deltas = new ArrayList<>();
		for (Perceptron perceptron : neurons) {
			deltas.add(perceptron.getDelta(nextLayer.getNeurons()));
		}
	}
	
	public String getPerceptronsWeights() {
		String weights = "";
		
		// writes the values of the perceptron's weights
		boolean isFirstPerceptron = true;
		for (Perceptron perceptron : neurons) {
			if (!isFirstPerceptron)
				weights += "\n";
			else
				isFirstPerceptron = false;
			
			String perWeights = "";
			boolean isFirstWeight = true;
			for (Double weight : perceptron.weights) {
				if (!isFirstWeight) 
					perWeights += ",";
				else
					isFirstWeight = false;
				perWeights += weight.toString();
			}
			// writes out the bias too
			perWeights += ",";
			perWeights += perceptron.bias;
			
			weights += perWeights;
		}
		
		
		return weights;
	}
}
