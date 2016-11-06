package bayes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.math3.*;
import org.apache.commons.math3.linear.BlockRealMatrix;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] params = s.split(",");
		
		int I, J, L;
		
		I = Integer.parseInt(params[0]);
		J = Integer.parseInt(params[1]);
		L = Integer.parseInt(params[2]);
		
		double beta;
		
		beta = Double.parseDouble(params[3]);
		
		BlockRealMatrix H, U, V;
		double[][] matrixParamsH = new double[I][J];
		for (int i = 0; i < I; i++) {
			s = br.readLine();
			params = s.split(",");
			for (int j = 0; j < J; j++)
				matrixParamsH[i][j] = Double.parseDouble(params[j]);
		}
		
		H = new BlockRealMatrix(matrixParamsH);
		U = new BlockRealMatrix(L, I);
		V = new BlockRealMatrix(L, J);
		
		// ezeket lehet hangolni
		double alfaU, alfaV;
		
		
	}

}
