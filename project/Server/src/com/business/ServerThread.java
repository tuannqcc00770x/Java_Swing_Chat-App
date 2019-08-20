/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.dal.UserDAO;
import com.entity.Client;
import com.entity.Server;
import com.entity.Users;
import com.ui.ServerBox;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TrongDuyDao
 */
public class ServerThread implements Runnable {

    private ServerSocket server;
    @SuppressWarnings("unused")
	private Server chatServer;
    private Socket socket;

    public ServerThread(Server chatServer) {
        this.chatServer = chatServer;
        try {
            server = new ServerSocket(chatServer.getPort());

        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
         /*insert code to handle a connection from each client here*/
         try {
			while (true) {
				socket = server.accept();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				ObjectInputStream ois = new ObjectInputStream(dis);
				Object obj = ois.readObject();
				if (obj instanceof Users) {
					Users m = (Users)obj;
					UserDAO u = new UserDAO();
					u.addUser(m);
					Client c = new Client();
					c.setUsername(m.getUsername());
					c.setSocket(socket);
					ServerBox.clients.addElement(c);
					System.out.println("Welcome " + m.getUsername());
					ClientHandler ch = new ClientHandler(socket, c, ois);
					clients.put(c.getUsername(), ch);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
    }

    public static HashMap<String, ClientHandler> clients = new HashMap<>();

}
