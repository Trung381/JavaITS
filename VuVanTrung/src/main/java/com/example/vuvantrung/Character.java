package com.example.vuvantrung;

import lombok.Getter;

@Getter
public class Character {
    private String name;
    private String inMovie;
    private int id;

    public Character (String name, String inMovie, int id) {
        this.name = name;
        this.inMovie = inMovie;
        this.id = id;
    }

    public String toString (){
        return this.name + " " + this.inMovie + " " + this.id;
    }
    public static void main (String[] args) {
        Character c = new Character("Trung", "haizzz", 1);
        System.out.println(c);
    }
}
