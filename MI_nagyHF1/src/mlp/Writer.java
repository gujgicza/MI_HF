package mlp;

import java.util.ArrayList;

public class Writer {
	
	public String weightsToString(ArrayList<ArrayList<ArrayList<Double>>> weights) {
		String string = "";
		
		boolean isFirstNeuron = true;
		for (ArrayList<ArrayList<Double>> layerW : weights) {
			for (ArrayList<Double> neuronW : layerW) {
				if (!isFirstNeuron)
					string += "\n";
				else
					isFirstNeuron = false;
				
				boolean isFirstW = true;
				for (Double w : neuronW) {
					if (!isFirstW) 
						string += ",";
					else
						isFirstW = false;
					string += w.toString();
				}
			}
			
		}
		
		return string;
	}

	public String outputsToString(ArrayList<ArrayList<Double>> allOutputs) {
		String string = "";
		
		boolean isFirstOutput = true;
		for (ArrayList<Double> output : allOutputs) {
			if (!isFirstOutput)
				string += "\n";
			else
				isFirstOutput = false;
			
			boolean isFirstDouble = true;
			for (Double double1 : output) {
				if (!isFirstDouble)
					string += ",";
				else
					isFirstDouble = false;
				string += double1.toString();
			}
		}
		return string;
	}
}
