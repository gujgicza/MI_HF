package nagyHF1;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
	
	ArrayList<Layer> layers;
	
	public NeuralNetwork() {
		layers = new ArrayList<>();
	}
	
	public void addLayer(Layer layer) {
		if(!layers.isEmpty()) {
			layer.setPrevLayer(layers.get(layers.size()-1));
			layers.get(layers.size()-1).setNextLayer(layer);
		}
		
		layers.add(layer);
	}
	
	public String getWeights() {
		String weights = "";
		
		boolean isFirstLayer = true;
		for (Layer layer : layers) {
			if (!isFirstLayer) 
				weights += "\n";
			else
				isFirstLayer = false;
			weights += layer.getPerceptronsWeights();
		}
		
		return weights;
	}

	/* TODO:
	 * calculate deltas meghívása a kimeneti rétegen
	 * calculate outputs meghívása a bemeneti rétegen
	 * clear outputs
	 * clear deltas, ha újraszámolás van
	 * tanítás meghívása
	 */

}
