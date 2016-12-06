package test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8080);
		while (true) {
		try {
		
			Socket socket = server.accept();
				BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String line = is.readLine();
				System.out.println("received from client: " + line);
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				pw.println("received data: " + line+"--服务器接收到了");
				pw.flush();
				// pw.close();
				// is.close();
				// socket.close();
				// server.close();
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		}
	}
}
