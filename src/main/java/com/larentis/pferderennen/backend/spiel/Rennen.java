package com.larentis.pferderennen.backend.spiel;

import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Rennen {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    String name;
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    List<Pferd> pferde = new LinkedList<Pferd>();
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    Pferd[] podest = new Pferd[3];
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    int laengeStraecke = 200;

    public Rennen(String name, List<Pferd> pferde, Pferd[] podest, int laengeStraecke) {
        this.name = name;
        this.pferde = pferde;
        this.podest = podest;
        this.laengeStraecke = laengeStraecke;
    }

    public Rennen(String name, int laengeStraecke) {
        this.name = name;
        this.laengeStraecke = laengeStraecke;
    }

    /**
     * Fuegt ein Pferd zum Rennen hinzu
     * 
     * @param pferd
     */
    public void addPferd(Pferd pferd) {
        pferde.add(pferd);
    }

    /**
     * Fuegt Pferde zum Rennen hinzu
     */

    public void addPferde(List<Pferd> pferde) {
        this.pferde.addAll(pferde);
    }

    /**
     * Fuegt ein Pferd zum Podest hinzu
     */

    public void addToPodest(int platz, Pferd pferd) {
        podest[platz] = pferd;
    }

    /**
     * Startet das Rennen
     */
    public void start() {
        Thread newThread = new Thread(() -> {
            List<Pferd> _pferde = new LinkedList<Pferd>();

            _pferde.addAll(pferde);

            while (true) {
                for (Pferd pferd : _pferde) {

                    // Guard Clause - Abbruchbedingung
                    // wenn kein Pferd mehr da ist, dann breche die Schleife ab
                    if (istRennenFertig()) {
                        return;
                    }

                    if (!pferd.isAngekommen(laengeStraecke)) {
                        pferd.laufen();
                    } else {
                        _pferde.remove(pferd);
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(pferd.zurueckgelegterWeg);
                }
            }
        });
        newThread.start();
    }

    boolean istRennenFertig() {
        for (Pferd pferd : pferde) {
            if (pferd.zurueckgelegterWeg < laengeStraecke) {
                return false;
            }
        }
        return true;
    }



    public String toString(){
        return "{\"name\": \"" + name + "\"," +
                " \"pferde\": " + pferde.toString() +
                ", \"podest\": \"" + podest.toString() +
                "\", \"istFertig\": " + istRennenFertig() +
        "}";
    }

}
