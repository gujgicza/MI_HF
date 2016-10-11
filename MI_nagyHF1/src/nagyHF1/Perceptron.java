package nagyHF1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Perceptron {
	List<Double> weights;
	Double bias;
	
	Double outputReal;
	Double outputNonLinear;
	Double delta;
	
	// Initialize with random weights
	public Perceptron(int card) {
		weights = new ArrayList<>();
		for (int i = 0; i < card; i++) {
			weights.add(Initialize(0.0, 0.1));
		}
		bias = 0.0;
		outputNonLinear = null;
	}
	
	// Initialize with given weights
	public Perceptron(List<Double> w, double b) {
		weights = new ArrayList<>();
		for (Double weight : w) {
			weights.add(weight);
		}
		bias = b;
		outputNonLinear = null;
	}
	
	public Double getOutput(List<Double> inputs){
		if (outputNonLinear == null)
			calculateOutput(inputs);
		return outputNonLinear;	
	}
	
	private void calculateOutput(List<Double> inputs) {
		Double sum = 0.0;
		for (Double in : inputs) {
			sum += in * weights.get(inputs.indexOf(in));
		}
		sum += bias;
		outputNonLinear = ReLu(sum);
	}
	
	public Double getDelta(List<Perceptron> nextNeurons) {
		if (delta == null)
			calculateDelta(nextNeurons);
		return delta;
	}

	private void calculateDelta(List<Perceptron> nextNeurons) {
		// TODO 
		Double sum = 0.0;
		for (Perceptron nextNeuron : nextNeurons) {
			// sum += nextDelta * (kövi réteg, ilyen deltájú perceptronának a súlyvektorának i. eleme)
			// (ez a perceptron az i. a rétegben ) -> nem double, hanem perceptron array kéne paraméternek
		}
		
		// sum *= ReLu derivált (outputReal) helyen
		
	}

	// Activation function
	private Double ReLu(Double sum) {
		if (sum > 0.0)
			return sum;
		else return 0.0;
	}

	protected Double Initialize(double u, double mu){
		
		// Gaussian distributed double value with mean 0.0, standard deviatation 1.0
		double db = new Random().nextGaussian();
		
		// sets the standard deviatation to "mu"
		db = db * mu;
	
		// sets the mean to "u"
		db += u;
		
		return  db;
		
	}

}
