package test;

import java.io.File;

import log.Logger;

public class GradientDescent {
	
	String function="y=x^3-x";
	static Logger logger;

	public static void main(String[] args) throws Exception{
		logger=new Logger("C:\\Users\\Elvis\\Desktop\\GradientDescent.log");
		logger.log(String.valueOf(GradientDescent(100, 0.01)));
	}
	
	/**
	 * 
	 * @param count:loop account
	 * @param namda: gap
	 * @return
	 * @throws Exception 
	 */
	public static double GradientDescent(int count, double namda) throws Exception{
		double x=1.0;
		double delta=0.0;
		logger.log("x0="+x+"\t namda="+namda);
		for(int i=0;i<count;i++){
			//if delta's absolute value is getting bigger, then function is not convergenting 
			if(Math.abs((4*x*x*x-18*x*x+11*x-6)*namda)>Math.abs(delta)){
				logger.log("x="+x+", initial value not proper leading to function non-convergenting");
				throw new Exception("initial value setting error");
			}
			delta=(4*x*x*x-18*x*x+11*x-6)*namda;
			x-=delta;
			logger.log(("x"+i+"="+x+"\t¦Ä="+delta));
		}
		return x;
	}
}
