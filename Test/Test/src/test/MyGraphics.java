package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyGraphics implements Car{
	
	int x,y;
	String dir;  
	
	MyGraphics(){
		x=1;
		y=1;
		dir="north";
	}

	public void move(String command) {
		// TODO Auto-generated method stub
		//cmd==fwd
		//cmd==cw
		//else 
	}

	public int getPositionX() {
		// TODO Auto-generated method stub
		return x;
	}

	public int getPositionY() {
		// TODO Auto-generated method stub
		return y;
	}

	public String getOrientation() {
		// TODO Auto-generated method stub
		return dir;
	}
	
	public void draw(){
		if("north".equals(dir)){
			System.out.println("(y)");
			System.out.println(" +---+---+---+---+");
			for(int i=0;i<4;i++){
				System.out.print(4-i+"|");
				for(int j=0;j<4;j++){
					if(x==j+1&&y+i==4)
						System.out.print(" C ");
					else
						System.out.print("   ");
					System.out.print("|");
				}
				System.out.println();
				System.out.println(" +---+---+---+---+");
			}
			System.out.println("   1   2   3   4  (x)");
		}
		else if("east".equals(dir)){
			
		}
		else if("south".equals(dir)){
			
		}
		else if("west".equals(dir)){
			
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		MyGraphics mycar=new MyGraphics();
		String cmd="";
		cmd = br.readLine();
		while("".equals(cmd)){
			mycar.move(cmd);
			mycar.draw();
			br=new BufferedReader(new InputStreamReader(System.in));
			cmd=br.readLine();
		}
	}
}
