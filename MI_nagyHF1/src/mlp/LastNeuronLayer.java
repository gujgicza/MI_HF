package mlp;

import java.util.ArrayList;

public class LastNeuronLayer extends NeuronLayer {

	public LastNeuronLayer(Integer neuronNum, int cardinal) {
		super(neuronNum, cardinal);
		// TODO Auto-generated constructor stub
	}
	
	public LastNeuronLayer(ArrayList<Neuron> neurons) {
		super(neurons);
	}
	
	@Override
	protected void setNeurons(Integer neuronNum, int cardinal) {
		neurons = new ArrayList<>();
		for (int i = 0; i<neuronNum; i++)
			neurons.add(new OutputNeuron(cardinal));
	}

}

