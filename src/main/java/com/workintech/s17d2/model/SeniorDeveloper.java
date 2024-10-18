package com.workintech.s17d2.model;

public class SeniorDeveloper extends Developer {

    public SeniorDeveloper(Experience experience, int id, String name, double salary) {
        super(experience.SENİOR, id, name, salary);
    }
}