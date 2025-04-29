package com.example.model;

public class Actor {

    private Integer actorID;
    private String firstName;
    private String lastName;

    public Actor() {
    }

    public Actor(Integer actorID, String firstName, String lastName) {
        this.actorID = actorID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getActorID() {
        return actorID;
    }
    public void setActorID(Integer actorID) {
        this.actorID = actorID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Actor [actorID=" + actorID + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
    
    
    
}