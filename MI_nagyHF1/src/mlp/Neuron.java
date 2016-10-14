package mlp;

import java.util.ArrayList;
import java.util.Random;

public class Neuron {

	ArrayList<Double> weights;
	ArrayList<Double> parcDerivedWeights;

	Double bias;
	Double parcDerivedBias;

	Double outputReal;
	Double outputNonLinear;

	public Neuron(ArrayList<Double> neuronWeights, Double neuronBias) {
		weights = neuronWeights;
		bias = neuronBias;
	}

	public double ReLu(double x) {
		return Math.max(0, x);
	}

	public ArrayList<Double> getWeightsAndBias() {
		ArrayList<Double> weightsN = new ArrayList<>(weights);
		weightsN.add(bias);
		return weightsN;
	}

	public Double getOutput(ArrayList<Double> inputs) {
		if (outputNonLinear == null) {
			outputReal = 0.0;
			for (int i = 0; i < inputs.size(); i++) {
				outputReal += inputs.get(i) * weights.get(i);
			}
			outputReal += bias;
			outputNonLinear = ReLu(outputReal);
		}

		return outputNonLinear;
	}

}
