package ba.bitcamp.hikmet.randomquotegenerator.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

/**
 * Class Client
 * 
 * @author Hikmet
 *
 */
public class Client {
	
	public static final int port = 1717;
	
	/**
	 * Method connect connects class Client to the Server and
	 * communicates with it
	 * @throws IOException
	 */
	public static void connect() throws IOException {
		
		//Program will ask for IP address to connect to
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter ip you wish to connect to.");
		String ipAddress = scan.nextLine();
		
		Socket user = null;
		File rQuotes = new File("C:\\Users\\Mafa\\Desktop\\receivedQuotes.txt");
		
		try {
			user = new Socket(ipAddress, port);
			
			OutputStream out = user.getOutputStream();
			
			//Program will ask for password
			System.out.println("Please enter the password.");
			String password = scan.nextLine();
			out.write(password.getBytes());	
			
			user.shutdownOutput();
			
			/* Program will receive one quote from the server and it will save it
			 * in the file receivedQuotes.txt
			 */
			InputStream in = user.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			
			// Connection message
			String message = br.readLine();
			System.out.println(message);
			
			// Received quote
			String quote = br.readLine();
			System.out.println(quote);
			
			//Writing to file
			FileWriter fw = new FileWriter(rQuotes, true);
			StringBuilder sb = new StringBuilder();
			sb.append("[").append(new Date()).append(" - ").append("]").append(quote);
			fw.write(sb.toString() + "\r"+ "\n");
			fw.flush();
			fw.close();
			
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			scan.close();
			user.close();
		}
	}
	
	/**
	 * Main method of class Client
	 */
	public static void main(String[] args) {
		try {
			connect();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}