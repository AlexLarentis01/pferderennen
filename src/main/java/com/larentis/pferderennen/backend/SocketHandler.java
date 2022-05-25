package com.larentis.pferderennen.backend;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larentis.pferderennen.backend.spiel.Spiel;
import com.larentis.pferderennen.backend.spiel.Stadion;
import com.larentis.pferderennen.backend.util.Log;
import sun.nio.cs.UTF_8;


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


    public String getJsonSpielStatus(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(game);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println(game.toString());
            while (true) {
                String call = in.readLine();
                handle_call(out, call);
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

    public void handle_call(PrintWriter out, String call) throws IOException{
        if (call == null) {
            return;
        }


        Log.info("Received call: " + call);
        switch(call.split("/")[0]){
            case "get_spiel_status":
                out.println(game.toString());
                break;
            case "add_stadium":
                game.addStadion(new Stadion(call.split("/")[1], Integer.parseInt(call.split("/")[2])));
                out.println(game.toString());
                break;
            case "remove_stadium":
                game.removeStadion(game.findStadion(call.split("/")[1]));
                out.println(game.toString());
                break;
        }
    }

    public void send(String message) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
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