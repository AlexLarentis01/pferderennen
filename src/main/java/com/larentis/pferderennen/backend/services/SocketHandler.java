package com.larentis.pferderennen.backend.services;

import java.io.*;
import java.net.*;

import com.larentis.pferderennen.backend.spiel.Spiel;


public class SocketHandler {
    Socket socket;
    Spiel game;
    boolean isRunning = true;

    public SocketHandler(Socket socket, Spiel game) {
        this.game = game;
        this.socket = socket;
    }

    // print out the message
    public void print(String a) {
        System.out.println(a);
    }

    public void run() {
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(game.toString());
            while (true) {
                String greeting = in.readLine();

                System.out.println(greeting);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void dispose() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}