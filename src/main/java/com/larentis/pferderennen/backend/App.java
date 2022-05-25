package com.larentis.pferderennen.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larentis.pferderennen.backend.spiel.Rennen;
import com.larentis.pferderennen.backend.spiel.Spiel;
import com.larentis.pferderennen.backend.spiel.Stadion;

import com.larentis.pferderennen.backend.spiel.Pferd;
import com.larentis.pferderennen.backend.util.Log;

import java.io.File;
import java.io.IOException;

class App {

    private static void debugValues(Spiel spiel) {
        Log.debug("Setting debug values");


        Rennen rennen = new Rennen("Test Rennen", 600);
        rennen.addPferd(new Pferd("Alfred", 10));
        rennen.addPferd(new Pferd("Berta", 10));
        rennen.addPferd(new Pferd("Hugo", 10));
        rennen.addPferd(new Pferd("Butter Rocket", 10));

        Stadion stadion = new Stadion("Stadion", 100);
        stadion.addRennen(rennen);

        spiel.addStadion(stadion);

    };

    public static void main(String[] args) {
        
        Log.info("Initializing Spiel ...");
        Spiel game = new Spiel();
        debugValues(game);

        Log.info("Initializing Socket Server ...");
        SocketServer server = new SocketServer();
        server.setSpiel(game);
        
        server.start(4545);
    }
}

