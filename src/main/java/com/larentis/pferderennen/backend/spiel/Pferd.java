package com.larentis.pferderennen.backend.spiel;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Pferd {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    String name;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    int geschwindigkeit, zurueckgelegterWeg = 0;


    public Pferd(String name, int lebensenergie) {
        this.name = name;
        this.geschwindigkeit = lebensenergie;
    }

    // start Rennen
    public void laufen() {
        zurueckgelegterWeg += geschwindigkeit;
    }

    public boolean isAngekommen(int laenge) {
        if (zurueckgelegterWeg >= laenge) {
            return true;
        }
        return false;
    }

    public String toString(){
        return "{ \"name\": \"" + name + "\"," +
                " \"geschwindigkeit\": " + geschwindigkeit +
                ", \"zurueckgelegterWeg\": " + zurueckgelegterWeg +
                "}";
    }

}