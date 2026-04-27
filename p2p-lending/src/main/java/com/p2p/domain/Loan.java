package com.p2p.domain;

public class Loan {
    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    private Status status;

    public Loan() {
        this.status = Status.PENDING;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }


    public void approve() {
    this.status = Status.APPROVED;

    }

    public void reject() {
    this.status = Status.REJECTED;
    }
}
