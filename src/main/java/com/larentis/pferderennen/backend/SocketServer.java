package com.larentis.pferderennen.backend;

import java.io.*;
import java.net.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        final Spiel pgame = this.game;
        Log.info("Starting Socket Server");
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                final Socket clientSocket = serverSocket.accept();
                Log.info("Accepted connection from " + clientSocket.getInetAddress() + " on port " + port);

                new Thread(){
                    @Override
                    public void run() {

                            new SocketHandler(clientSocket, pgame).run();
                    }
                }.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}