package com.jazart.buttonchallenge;


/**
 * Created by jazart on 3/20/2018.
 */

public class User {

    private int id;
    private String name;
    private String email;
    private String candidate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name:")
                .append(name)
                .append(" ")
                .append("ID:")
                .append(id)
                .append(" ")
                .append("Email:")
                .append(email)
                .append(" ")
                .append("Candidate:")
                .append(candidate);
        return sb.toString();
    }
}
