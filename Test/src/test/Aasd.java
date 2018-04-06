package test;

public class Aasd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long a=Long.parseLong("9223372036854775803");
		long flag=a;
		for(;;)
		{
			a++;
			if(a<0)
				break;
			flag=a;
			System.out.println(a);
		}

		System.out.println("end:		"+a);
		
		// short 2^15
	}

}
