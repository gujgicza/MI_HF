package bayes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.math3.*;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

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
	
	
	
	public static void refreshMatrix(RealMatrix matrix, double alfa, double beta, RealMatrix otherMatrix, RealMatrix H) {
		RealMatrix lambda = calculateLambda(beta, otherMatrix, alfa);
		RealMatrix lambdaInverse = MatrixUtils.inverse(lambda);
		
		int I = matrix.getRowDimension();
		for (int i = 0; i < I; i++) {
			RealMatrix psi = calculatePsi(lambdaInverse, beta, H, otherMatrix, i, alfa);
			modifyRow(matrix, psi, lambdaInverse, i);
		}
		
	}
	
	public static RealMatrix calculatePsi(RealMatrix lambda, double beta, RealMatrix H, RealMatrix otherMatrix,
			int i, double alfa) {
		RealMatrix psi;
		RealMatrix Htrans = H.getColumnMatrix(i).transpose();
		psi = Htrans.multiply(otherMatrix);
		psi = psi.scalarMultiply(beta);
		RealMatrix lambdaInverse = MatrixUtils.inverse(lambda);
		psi = psi.preMultiply(lambdaInverse);
		return psi;
	}
	
	public static RealMatrix calculateLambda(double beta, RealMatrix otherMatrix, double alfa) {
		int J = otherMatrix.getColumnDimension();
		RealMatrix lambda = new BlockRealMatrix(J, J);
		RealMatrix vector;
		RealMatrix vectorT;
		for (int j = 0; j < J; j++) {
			vector = otherMatrix.getColumnMatrix(j);
			vectorT = otherMatrix.getColumnMatrix(j).transpose();
			lambda = lambda.add(vector.multiply(vectorT));
		}
		lambda = lambda.scalarMultiply(beta);
		BlockRealMatrix identityMatrix = new BlockRealMatrix(J, J);
		for (int i = 0; i < J; i++)
			identityMatrix.setEntry(i, i, 1);
		lambda = lambda.add(identityMatrix.scalarMultiply(alfa));
		return lambda;
	}
	
	public static void modifyRow(RealMatrix matrix, RealMatrix meansVec, RealMatrix covariancesMatrix, int row) {
		int rows = matrix.getRowDimension();

		double[] means = new double[rows];
		for (int i = 0; i < rows; i++)
			means[i] = meansVec.getEntry(i, 0);
		
		double[][] covariances = new double[rows][rows];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				covariances[i][j] = covariancesMatrix.getEntry(i, j);
			}
		}

		MultivariateNormalDistribution normalDist = new MultivariateNormalDistribution(means, covariances);
		matrix.setColumn(row, normalDist.sample());
	}
		
	public static void setPrior(RealMatrix matrix, double alfa) {
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
		BlockRealMatrix matrixT = matrix.transpose();
		for (int i = 0; i < matrixT.getRowDimension(); i++) {
			for ( int j = 0; j < matrixT.getColumnDimension(); j++) {
				if (j == 0) {
					outString.append(matrixT.getEntry(i, j));
				}
				else outString.append(matrixT.getEntry(i, j) + ",");		
			}
			if (i != 0)
				outString.append("\n");
		}	
		System.out.println(outString);
		return outString.toString();		
	}
	

}
