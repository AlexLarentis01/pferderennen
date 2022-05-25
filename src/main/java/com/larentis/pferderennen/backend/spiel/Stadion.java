package com.larentis.pferderennen.backend.spiel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Stadion {
   @JsonFormat(shape = JsonFormat.Shape.STRING)
   String name;
   @JsonFormat(shape = JsonFormat.Shape.NUMBER)
   int laenge;
   @JsonFormat(shape = JsonFormat.Shape.ARRAY)
   List<Rennen> rennen = new LinkedList<>();

   public Stadion(String name, int laenge) {
      this.name = name;
      this.laenge = laenge;
   }

   /**
    * Fuegt ein rennen hinzu
    */

   public void addRennen(Rennen rennen) {
      this.rennen.add(rennen);
   }

   /**
    * Loescht renne
    */
   public void removeRennen(Rennen rennen) {
      this.rennen.remove(rennen);
   }

  public List<Rennen> getRennen() {
      return rennen;
  }

      /**
    * Loescht renne
    */
    public void printRennen(Rennen rennen) {
      System.out.println(rennen.toString());
   }

   public String toString(){
      return "{\"name\": \"" + name + "\", \"rennen\": " + rennen + "}";
   }
}
