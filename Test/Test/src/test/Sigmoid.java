package test;

public class Sigmoid {

	public static void main(String[] args) {
		System.out.println(Sigmoid(0.04));
		
		System.out.println(Math.pow(Math.E,3.0));
	}
	
	public static double Sigmoid(double input){
		return 1/(1+Math.pow(Math.E,0.0-input));
	}
}
