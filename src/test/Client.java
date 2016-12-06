package test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			String msg = sc.nextLine();
			try {
				Socket socket = new Socket("127.0.0.1", 8080);
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pw.println(msg);
				pw.flush();
				String line = is.readLine();
				System.out.println("received from server: " + line+"/n");
				// pw.close();
				// is.close();
				// socket.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}
}
