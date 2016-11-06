package bayes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.math3.*;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.linear.BlockRealMatrix;

public class Main {

	public static void main(String[] args) throws IOException {
		double alfaU, alfaV;
		alfaU = 0.1;
		alfaV = 0.1;
		
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
		
		setPrior(U, alfaU);
		setPrior(V, alfaV);
				
	}
	
	public static void setPrior(BlockRealMatrix matrix, double alfa) {
		int rows = matrix.getRowDimension();
		
		double[] means = new double[rows];
		for (int i = 0; i < rows; i++)
			means[i] = 0.0;
		
		double[][] covariances = new double[rows][rows];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				if (i == j)
					covariances[i][j] = 1 / alfa;
				else
					covariances[i][j] = 0.0;
			}
		}
		MultivariateNormalDistribution normalDist = new MultivariateNormalDistribution(means, covariances);
		
		for (int j = 0; j < matrix.getColumnDimension(); j++)
			matrix.setColumn(j, normalDist.sample());
	}
	
	
	public static String writeMatrix(BlockRealMatrix matrix) {
		StringBuilder outString = new StringBuilder();
		matrix.transpose();
		for (int i = 0; i < matrix.getRowDimension(); i++) {
			for ( int j = 0; j < matrix.getColumnDimension(); j++) {
				if (j == 0) {
					outString.append(matrix.getEntry(i, j));
				}
				else outString.append(matrix.getEntry(i, j) + ",");		
			}
			if (i != 0)
				outString.append("\n");
		}	
		System.out.println(outString);
		return outString.toString();		
	}
	

}
