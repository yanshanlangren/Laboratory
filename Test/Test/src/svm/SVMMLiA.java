package svm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;

public class SVMMLiA {
	
	private static Matrix dataArr;
	private static Matrix labelArr;
	
	public static void loadDataSet(String fileName) throws IOException{
		File file=new File(fileName);
		FileInputStream in=new FileInputStream(file);
		InputStreamReader isr=new InputStreamReader(in);
		BufferedReader br=new BufferedReader(isr);
		String temp;
		temp=br.readLine();
		List arrList=new ArrayList();
		while(temp!=null){
			arrList.add(temp.split("\t"));
			temp=br.readLine();
		}
		double[][] data=new double[arrList.size()][2];
		double[][] label=new double[1][arrList.size()];
		for(int i=0;i<arrList.size();i++){
			String[] tmp=(String[]) arrList.get(i);
			data[i][0]=Double.parseDouble(tmp[0]);
			data[i][1]=Double.parseDouble(tmp[1]);
			label[0][i]=Double.parseDouble(tmp[2]);
		}
		dataArr=new Matrix(data);
		labelArr=new Matrix(label);
	}
	
	private static Matrix zeros(int row, int col){
		double[][] retMat=new double[row][col];
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
				retMat[i][j]=0.0;
		return new Matrix(retMat);
	}
	
	public static Matrix multiply(Matrix a,Matrix b) throws Exception{
		if(a.getRowDimension()!=b.getRowDimension()||a.getColumnDimension()!=b.getColumnDimension())
			throw new Exception("Matrix row or column dimension not equal");
		double[][] retMat=new double[a.getRowDimension()][a.getColumnDimension()];
		for(int i=0;i<a.getRowDimension();i++)
			for(int j=0;j<a.getColumnDimension();j++)
				retMat[i][j]=a.get(i, j)*b.get(i, j);
		return new Matrix(retMat);
	}
	
	public static Matrix getRow(Matrix a, int row){
		double[][] retMat=new double[1][a.getColumnDimension()];
		for(int i=0;i<a.getColumnDimension();i++){
			retMat[0][i]=a.get(row, i);
		}
		return new Matrix(retMat);
	}
	
	public static void smoSimple(Matrix dataMatIn,Matrix classLabels,int C,float toler,int maxIter) throws Exception{
		Matrix dataMatrix=dataMatIn;
		Matrix labelMat=classLabels.transpose();
		int b=0;
		int m=dataMatrix.getRowDimension();
		int n=dataMatrix.getColumnDimension();
		Matrix alphas=zeros(m,1);
		int iter=0;
		int j;
		while(iter<maxIter){
			int alphaPairsChanged=0;
			for(int i=0;i<m;i++){
				float fXi=(float) multiply(alphas,labelMat).transpose().times(dataMatrix.times(getRow(dataMatrix,i).transpose())).get(0, 0)+b;
				float Ei=fXi-(float)labelMat.get(0,i);
				if(((labelMat.get(i,0)*Ei<-toler) && (alphas.get(i,0)<C)) || ((labelMat.get(i,0)*Ei>toler)&&(alphas.get(i,0)>0))){
					j=selectJrand(i,m);
					float fXj=(float) (multiply(alphas,labelMat).transpose().times(dataMatrix.times(getRow(dataMatrix,j)).transpose()).get(0,0)+b);
				}
			}
		}
	}
	
	private static int selectJrand(int i, int m) {
		int j=i;
		while(j==i)
			j=(int) (m*Math.random());
		return j;
	}

	public static void main(String[] args) throws IOException{
		loadDataSet("C:\\Users\\Elvis\\Desktop\\python\\SVM\\dataFile.txt");
	}
}
