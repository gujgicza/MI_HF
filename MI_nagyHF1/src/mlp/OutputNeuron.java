package mlp;

import java.util.ArrayList;

public class OutputNeuron extends Neuron{

	public OutputNeuron(ArrayList<Double> neuronWeights, Double neuronBias) {
		super(neuronWeights, neuronBias);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double ReLu(double x) {
		return x;
	}
}
