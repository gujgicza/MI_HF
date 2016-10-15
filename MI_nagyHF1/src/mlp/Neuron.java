package mlp;

import java.util.ArrayList;
import java.util.Random;

public class Neuron {

	ArrayList<Double> weights;
	ArrayList<Double> parcDerivedWeights;
	
	Double delta;

	Double bias;
	Double parcDerivedBias;

	Double outputReal;
	Double outputNonLinear;

	public Neuron(ArrayList<Double> neuronWeights, Double neuronBias) {
		weights = neuronWeights;
		parcDerivedWeights = new ArrayList<>();
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

	public ArrayList<Double> getPartialDerivates(ArrayList<Double> input) {
		if (parcDerivedWeights.isEmpty() && parcDerivedBias == null) {
			parcDerivedBias = delta;
			parcDerivedWeights = new ArrayList<>();
			for (int i = 0; i < input.size(); i++)
				parcDerivedWeights.add(input.get(i)*delta);
		}
		ArrayList<Double> partialDerivatesN = new ArrayList<>(parcDerivedWeights);
		partialDerivatesN.add(parcDerivedBias);
		return partialDerivatesN;
	}

	public Double calculateDelta(NeuronLayer nextLayer, int idx) {
		if (delta == null) {
			double sum = 0;
			for (int i = 0; i < nextLayer.neurons.size(); i++) {
				sum += nextLayer.neurons.get(i).weights.get(idx) * nextLayer.neurons.get(i).delta;
			}
			delta = sum * derivateOutput(outputNonLinear);
		}
		
		return delta;
	}

	private Double derivateOutput(Double outputNonLinear) {
		if (outputNonLinear > 0)
			return 1.0;
		else
			return 0.0;
	}

}
