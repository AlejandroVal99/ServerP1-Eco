package communication;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import events.OnMessageListener;
import model.Colors;
import model.Direction;
import model.Generic;
import model.Name;

public class TCPConnection extends Thread {

	

	private static TCPConnection instance;

	private TCPConnection() {

	}

	public static TCPConnection getInstance() {
		if (instance == null) {
			instance = new TCPConnection();
			instance.start();
		}
		return instance;
	}

	
	private Socket socket;
	private BufferedWriter writer;
	
	private OnMessageListener observer;
	
	public void setObserver(OnMessageListener observer) {

		this.observer = observer;

	}
	
	public void run() {

		try {
			ServerSocket server = new ServerSocket(5000);
			System.out.println("Esperando conexion");
			socket = server.accept();
			System.out.println("Cliente conectado");

			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);

			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			writer = new BufferedWriter(osw);

			Gson gson = new Gson();

			while (true) {
				String line = reader.readLine();
				System.out.println("Recibido: " + line);
				
				Generic generic = gson.fromJson(line, Generic.class);

				switch (generic.getType()) {

				case "Color":
					Colors color = gson.fromJson(line, Colors.class);
					observer.OnColorReceived(color);
					break;
					
				case "Direction":
					Direction direction = gson.fromJson(line, Direction.class);
					observer.OnDirectionReceived(direction);
					break;
				
				case "Name":
					Name name = gson.fromJson(line, Name.class);
					observer.OnNameReceived(name);
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg) {
		new Thread(() -> {
			try {
				writer.write(msg + "\n");
				writer.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}
}
