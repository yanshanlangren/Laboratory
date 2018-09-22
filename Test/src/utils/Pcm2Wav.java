package utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.apache.commons.io.IOUtils;

public class Pcm2Wav { 
	
	public static void main(String[] args) throws Exception { 
		pcm2wav("d:/1.pcm", "d:/11.wav"); 
	}
	
	public static void pcm2wav(String source, String target) throws Exception { 
		float sampleRate = 16000; 
		int sampleSizeInBits = 16; 
		int channels = 1; 
		boolean signed = true; 
		boolean bigEndian = false; 
		AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian); 
		File sourceFile = new File(source); 
		FileOutputStream out = new FileOutputStream(new File(target)); 
		AudioInputStream audioInputStream = new AudioInputStream(new FileInputStream(sourceFile), af, sourceFile.length()); 
		AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out); 
		audioInputStream.close(); 
		out.flush(); 
		out.close(); 
	}
	

	/**
	 * WAV转PCM(截掉前44个字节数据)
	 * @author Elvis
	 */
	public static String wav2pcm(String wavfilepath,String pcmfilepath){
		FileInputStream fileInputStream;
		FileOutputStream fileOutputStream;
		try {
			fileInputStream = new FileInputStream(wavfilepath);
			fileOutputStream = new FileOutputStream(pcmfilepath);
			byte[] wavbyte = InputStreamToByte(fileInputStream);
			byte[] pcmbyte = Arrays.copyOfRange(wavbyte, 44, wavbyte.length);
			fileOutputStream.write(pcmbyte);
			IOUtils.closeQuietly(fileInputStream);
			IOUtils.closeQuietly(fileOutputStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pcmfilepath;
	}

	/**
	 * 输入流转byte二进制数据
	 * @param fis
	 * @return
	 * @throws IOException
	 */
	private static byte[] InputStreamToByte(FileInputStream fis) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		long size = fis.getChannel().size();
		byte[] buffer = null;
		if (size <= Integer.MAX_VALUE) {
			buffer = new byte[(int) size];
		} else {
			buffer = new byte[8];
			for (int ix = 0; ix < 8; ++ix) {
				int offset = 64 - (ix + 1) * 8;
				buffer[ix] = (byte) ((size >> offset) & 0xff);
			}
		}
		int len;
		while ((len = fis.read(buffer)) != -1) {
			byteStream.write(buffer, 0, len);
		}
		byte[] data = byteStream.toByteArray();
		IOUtils.closeQuietly(byteStream);
		return data;
	}


}