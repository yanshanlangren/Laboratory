package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class WritePageRankData {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File file=new File("e:/pageRankData.txt");
		
		if(!file.exists())
			file.createNewFile();
		
		FileWriter fw=new FileWriter(file,true);
		BufferedWriter bw=new BufferedWriter(fw);
		
		for(int i=0;i<100;i++){
			int source=(int) Math.round(Math.random()*25)+65;
			int dest=(int) Math.round(Math.random()*25)+65;
			
			
			System.out.println((char)source+","+(char)dest);
			
			bw.write((char)source+","+(char)dest+"\r\n");
			
		}
		
		
		bw.close();
		
	}

}
