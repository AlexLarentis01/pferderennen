package com.larentis.pferderennen.backend.services;

import java.io.*;
import java.net.*;

import com.larentis.pferderennen.backend.spiel.Spiel;
import com.larentis.pferderennen.backend.util.Log;



public class SocketServer {
    ServerSocket serverSocket;
    Spiel game;
    
    public void setSpiel(Spiel game) {
        this.game = game;
    }

    /***
     * 
     * @param port
     * @throws IOException
     */
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Log.info("Accepted connection from " + clientSocket.getInetAddress() + " on port " + port);
                new Thread(() -> new SocketHandler(clientSocket, game).run()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}