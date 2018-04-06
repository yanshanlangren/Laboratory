package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket socket=new Socket("127.0.0.1",4700);
			BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String readline;
			readline=sin.readLine(); //��ϵͳ��׼�������һ�ַ���
			while(!readline.equals("bye")){
				os.println(readline);
				os.flush();
				System.out.println("Client:"+readline);
				System.out.println("Server:"+is.readLine());
				readline=sin.readLine(); //��ϵͳ��׼�������һ�ַ���
			}
			os.close(); //�ر�Socket�����
			is.close(); //�ر�Socket������
			socket.close(); //�ر�Socket
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
