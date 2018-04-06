package com.ibm.ns.alert.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipUtil
{
	public static StringBuffer getContent(File file) throws ZipException, IOException{
		StringBuffer sb = new StringBuffer();
		
		ZipFile zf = new ZipFile(file);
		ZipInputStream zin = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
		
		ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {  
            if (ze.isDirectory()) {
            	//do nothing
            }else{ 
                long size = ze.getSize();  
                if (size > 0) {  
                    BufferedReader br = new BufferedReader(  
                            new InputStreamReader(zf.getInputStream(ze)));  
                    String line;  
                    while ((line = br.readLine()) != null) {  
                        System.out.println(line);
                        sb.append(line);
                    }  
                    br.close();  
                }  
            }  
        }  
        zin.close();
        zf.close();
		
		return sb;
	}
		
	/**
	 * get content form gzip byte[]
	 * @param bytes
	 * @return
	 * @throws ZipException
	 * @throws IOException
	 */
	public static StringBuffer getContentFromGZIPBytes(byte[] bytes) throws ZipException, IOException{
		byte[] b = null;
		ByteArrayInputStream bain = new ByteArrayInputStream(bytes);
		GZIPInputStream gzip = new GZIPInputStream(bain);
		
		byte[] buf = new byte[1024];  
		int num = -1;  
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		while ((num = gzip.read(buf, 0, buf.length)) != -1) {
			baos.write(buf, 0, num);  
		}
		
		b = baos.toByteArray();
		baos.flush();
		baos.close();
		gzip.close();
		bain.close();  
		
		
		return new StringBuffer(new String(b));
	}
	
	
	public static void main(String[] args){
		
		String gzip_base64_file = "C:\\Users\\IBM_ADMIN\\Desktop\\test_gzip_base64.json";
		String gzip_base64_ct = FileUtil.getContentFromFile(new File(gzip_base64_file));
		
		System.out.println("gzip_base64_ct:"+gzip_base64_ct);
		
		byte[] b = Base64Util.decodeBytes(gzip_base64_ct);
		
		try {
			StringBuffer sb = ZipUtil.getContentFromGZIPBytes(b);
			System.out.println("content:"+sb.toString());
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/* Location:           C:\Users\IBM_ADMIN\Desktop\alertStatusRpt.zip
 * Qualified Name:     alertStatusRpt.WEB-INF.classes.com.ibm.ns.alert.utils.ZipUtil
 * JD-Core Version:    0.6.2
 */