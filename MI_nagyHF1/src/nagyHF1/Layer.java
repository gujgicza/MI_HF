package nagyHF1;

import java.util.ArrayList;

public class Layer {
	
	ArrayList<Perceptron> neurons;
	Layer nextLayer;
	Layer prevLayer;
	
	ArrayList<Double> outputs;
	ArrayList<Double> deltas;
	
	public Layer(ArrayList<Perceptron> perceptrons) {
		neurons = perceptrons;
		nextLayer = null;
		prevLayer = null;
		outputs = null;
		deltas = null;
	}
	
	public Layer(int neuronNum, int cardinality, boolean isLast) {
			
		neurons = new ArrayList<>();
		for (int i = 0; i < neuronNum; i++) {
			neurons.add(new Perceptron(cardinality, isLast));
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
	
	
	public ArrayList<Double> getLayerOutputs(ArrayList<Double> inputLayer) {
		outputs = new ArrayList<>();
		if (prevLayer == null) {
			for (Perceptron perceptron : neurons) {
				outputs.add(perceptron.getOutput(inputLayer));
			}
		}
		else {
			for (Perceptron perceptron : neurons) {
				outputs.add(perceptron.getOutput(prevLayer.getLayerOutputs(inputLayer)));
			}
		}
		
		return outputs;		
	}
	
	public ArrayList<Double> getLayerDeltas() {
		/* perceptronnak nem kéne tudnia a layerekről:
		 * getDelta paraméterei delták, és a hozzá tartozó súlyvektorok kéne legyenek.
		 */
		if (nextLayer == null) {
			// TODO: végén csak a delta
		}
		deltas = new ArrayList<>();
		for (Perceptron perceptron : neurons) {
			deltas.add(perceptron.getDelta(nextLayer.neurons));
		}
		
		return null;
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
