package ba.bitcamp.hikmet.randomquotegenerator.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Class Server
 * 
 * @author Hikmet
 *
 */
public class Server {
	
	private static final String pass = "jj";
	public static final int port = 1717;
	
	/**
	 * Method starts the server and handles communication with Client
	 * @throws IOException
	 */
	public static void serverStart() throws IOException {
		
		ServerSocket server = null;
		File quotes = new File("C:\\Users\\Mafa\\Desktop\\quotes.txt");
		File auth = new File("C:\\Users\\Mafa\\Desktop\\auth_log.txt");
		
		try {
			
			//Server startup
			server = new ServerSocket(port);
			while (true) {
				System.out.println("Server waiting...");
				Socket user = server.accept();
				System.out.println("User Connected");
				
				InputStream in = user.getInputStream();
				InputStreamReader isr = new InputStreamReader(in);	
				BufferedReader br = new BufferedReader(isr);
				
				OutputStream out = user.getOutputStream();
				
				while (true) {
					/* Password evaluation:
					 * If password is correct will return some random movie quote to Client
					 * else denies connection and logs users IP
					 */
					if (br.readLine().equals(pass)) {                      // Password correct case
						
						//Login status message
						System.out.println("User logged in succesfully");
						String message = "Password correct, welcome!\n";
						
						//Generating quote and sending it to Client
						String quote = getQuote(quotes);
						message += quote + "\n";
						out.write(message.getBytes());
						out.flush();
						break;
					} else {                                               // Password incorrect case
						
						//Login status message
						System.out.println("Users log in not authorised");
						String message = "Password wrong, log in denied!\n";
						out.write(message.getBytes());
						
						//Writing users IP into log file
						FileWriter fw = new FileWriter(auth, true);
						StringBuilder sbOut = new StringBuilder();
						sbOut.append("[").append(new Date()).append(" - ").append(user.getRemoteSocketAddress()).append("]");
						fw.write(sbOut.toString() + "\r"+ "\n");
						fw.flush();
						fw.close();
						break;
					}
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			server.close();
		}
		
	}
	
	/**
	 * Method will return one random String quote from file quotes.txt
	 * @param quotes = File to read from
	 * @return String quote
	 */
	private static String getQuote(File quotes) {
		int rand = (int) (Math.random() * 15 + 1);
		String str = "";
		try {
			FileInputStream fs = new FileInputStream(quotes);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			for (int i = 0; i < rand; i++) {
				br.readLine();
			}
			str = br.readLine();
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		 
		return str;
	}
	
	/**
	 * Main method of class Server
	 */
	public static void main(String[] args) {
		try {
			serverStart();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}