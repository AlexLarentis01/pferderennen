package com.larentis.pferderennen.backend.spiel;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class Spiel{
    @JsonFormat(shape = Shape.ARRAY)
    public List<Stadion> stadions = new LinkedList<>();

    @Override
    public String toString(){
        return "{\"stadions\": " + stadions + "}";
    }

    // find
    public Stadion findStadion(String name){
        for(Stadion stadion : stadions){
            if(stadion.name.equals(name)){
                return stadion;
            }
        }
        return null;
    }

    public void addStadion(Stadion stadion){
        stadions.add(stadion);
    }

    public void removeStadion(Stadion stadion){
        stadions.remove(stadion);
    }




}