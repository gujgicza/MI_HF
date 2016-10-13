package mlp;

import java.util.ArrayList;

public class Neuron {
	
	ArrayList<Double> weights;
	ArrayList<Double> parcDerivedWeights;
	
	Double bias;
	Double parcDerivedBias;
	
	Double outputReal;
	Double outputNonLinear;
	
	public Neuron(ArrayList<Double> neuronWeights, Double neuronBias) {
		// TODO Auto-generated constructor stub
		weights = neuronWeights;
		bias = neuronBias;
	}

	public double ReLu(double x) {
		return Math.max(0, x);
	}

	public ArrayList<Double> getWeights() {
		// TODO Auto-generated method stub
		ArrayList<Double> weightsN = new ArrayList<>();
		for (Double w : weights) {
			weightsN.add(w);
		}
		weightsN.add(bias);
		return weightsN;
	}

	public Double getOutput(ArrayList<Double> inputs) {
		outputReal = 0.0;
		for (int i = 0; i < inputs.size(); i++) {
			outputReal += inputs.get(i) * weights.get(i);
		}
		outputReal += bias;
		outputNonLinear = ReLu(outputReal);
		
		return outputNonLinear;
	}
	
}
