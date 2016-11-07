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
		
		calculate(20, H, U, V, alfaU, alfaV, beta);
		calculateAndSave(30, H, U, V, alfaU, alfaV, beta);				
	}
	
	public static void calculateAndSave(int iter, RealMatrix H, RealMatrix U, RealMatrix V, double alfaU, double alfaV, double beta) {
		RealMatrix averageU = new BlockRealMatrix(U.getRowDimension(), U.getColumnDimension());
		RealMatrix averageV = new BlockRealMatrix(V.getRowDimension(), V.getColumnDimension());
		
		for (int i = 0; i < iter; i++) {
			averageU = averageU.add(refreshMatrix(U, alfaU, beta, V, H));
			averageV = averageV.add(refreshMatrix(V, alfaV, beta, U, H.transpose()));
		}
		
		averageU = averageU.scalarMultiply(1.0/iter);
		averageV = averageV.scalarMultiply(1.0/iter);
		
		writeMatrixTranspose(averageU);
		writeMatrixTranspose(averageV);

	}
	
	public static void calculate(int iter, RealMatrix H, RealMatrix U, RealMatrix V, double alfaU, double alfaV, double beta) {
		for (int i = 0; i < iter; i++) {
			refreshMatrix(U, alfaU, beta, V, H);
			refreshMatrix(V, alfaV, beta, U, H.transpose());
		}
	}
	
	public static RealMatrix refreshMatrix(RealMatrix matrix, double alfa, double beta, RealMatrix otherMatrix, RealMatrix H) {
		RealMatrix lambda = calculateLambda(beta, otherMatrix, alfa);
		RealMatrix lambdaInverse = MatrixUtils.inverse(lambda);

		RealMatrix psi;
		for (int j = 0; j < matrix.getColumnDimension(); j++) {
			psi = calculatePsi(lambdaInverse, beta, H, otherMatrix, j);
			modifyColumn(matrix, psi, lambdaInverse, j);
		}
		return matrix;
	}
	
	public static RealMatrix calculatePsi(RealMatrix lambdaInverse, double beta, RealMatrix H, RealMatrix otherMatrix, int i) {
		RealMatrix Htrans = H.getRowMatrix(i).transpose();
		RealMatrix psi = Htrans.preMultiply(otherMatrix);
		psi = psi.scalarMultiply(beta);
		psi = psi.preMultiply(lambdaInverse);
		return psi;
	}
	
	public static RealMatrix calculateLambda(double beta, RealMatrix otherMatrix, double alfa) {
		int L = otherMatrix.getRowDimension();
		RealMatrix lambda = new BlockRealMatrix(L, L);

		lambda = otherMatrix.multiply(otherMatrix.transpose());
		lambda = lambda.scalarMultiply(beta);
		RealMatrix identityMatrix = MatrixUtils.createRealIdentityMatrix(L);
		lambda = lambda.add(identityMatrix.scalarMultiply(alfa));
		return lambda;
	}
	
	public static void modifyColumn(RealMatrix matrix, RealMatrix meansVec, RealMatrix covariancesMatrix, int col) {
		double[] means = meansVec.getColumn(0);
		double[][] covariances = covariancesMatrix.getData();

		MultivariateNormalDistribution normalDist = new MultivariateNormalDistribution(means, covariances);
		matrix.setColumn(col, normalDist.sample());
	}
		
	public static void setPrior(RealMatrix matrix, double alfa) {
		int rows = matrix.getRowDimension();		
		double[] means = new double[rows];	
		double[][] covariances = MatrixUtils.createRealIdentityMatrix(rows).scalarMultiply(1/alfa).getData();

		MultivariateNormalDistribution normalDist = new MultivariateNormalDistribution(means, covariances);		
		for (int j = 0; j < matrix.getColumnDimension(); j++)
			matrix.setColumn(j, normalDist.sample());
	}
		
	public static void writeMatrixTranspose(RealMatrix matrix) {
		RealMatrix matrixT = matrix.transpose();
		StringBuilder matrixString = new StringBuilder();
		for (int i = 0; i < matrixT.getRowDimension(); i++) {		
			for ( int j = 0; j < matrixT.getColumnDimension(); j++) {
				if (j == 0) {
					matrixString.append(matrixT.getEntry(i, j));
				}
				else matrixString.append("," + matrixT.getEntry(i, j));		
			}
			matrixString.append("\n");
		}
		System.out.println(matrixString);
	}
}
