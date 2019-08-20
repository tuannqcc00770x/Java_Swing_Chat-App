package com.business;

import com.entity.MessageDetail;
import com.entity.Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class ClientThread implements Runnable, Serializable {

	private static final long serialVersionUID = 1L;
	//for I/O
    private Socket socket;
    private Server server;
    private JTextArea txtContent;
    //use to read and write data to/from server
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public ClientThread(Server server, JTextArea txtContent) {
        /*insert code for opening a connection to server here*/
       try {
		this.txtContent = txtContent;
		this.server = server;
		socket = new Socket(server.getHost(), server.getPort());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		oos = new ObjectOutputStream(dos);
	} catch (Exception e) {
		// TODO: handle exception
		Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, e);
		}
    }

    @Override
    public void run() {
        /*receiving and output a message from server*/
        try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			ois = new ObjectInputStream(dis);
			while (true) {
				Object obj = ois.readObject();
				if (obj instanceof MessageDetail ) {
					MessageDetail m = (MessageDetail) obj;
					txtContent.append(m.getFromUser() + ": " + m.getContent() + "\n");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, e);
		}

    }

    //send message to client
    public void send(Object line) throws Exception {
        /*sending a message to client*/
        oos.writeObject(line);
        if (line instanceof MessageDetail) {
        	MessageDetail m = (MessageDetail) line;
        	txtContent.append("Me: " + m.getContent() + "\n");
        }
    }
 
}
