package com.zhouyi.zhouyi.object;

public class Divination {
    private String time;
    private String id;
    private String reason;

    public Divination() {
        this.time = "none";
        this.id = "none";
        this.reason = "none";
    }

    public Divination(String time, String reason) {
        this.time = time;
        this.id = "none";
        this.reason = reason;
    }

    public Divination(String time, String id, String reason) {
        this.time = time;
        this.id = id;
        this.reason = reason;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return this.time;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return this.reason;
    }
}
