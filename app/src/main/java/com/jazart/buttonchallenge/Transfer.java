package com.jazart.buttonchallenge;

/**
 * Created by jazart on 3/21/2018.
 */

public class Transfer {
    private int id;
    private String amount;
    private int user_id;
    private String candidate;

    public Transfer() {

    }

    public Transfer(int transId, String amount, int user_id, String candidate) {
        this.id = transId;
        this.amount = amount;
        this.user_id = user_id;
        this.candidate = candidate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
