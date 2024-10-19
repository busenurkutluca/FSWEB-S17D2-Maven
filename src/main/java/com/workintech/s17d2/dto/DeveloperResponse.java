package com.workintech.s17d2.dto;

import com.workintech.s17d2.model.Developer;



import com.workintech.s17d2.model.Developer;

public class DeveloperResponse {
    private Developer developer;
    private String  message;
    private int status;


    public DeveloperResponse(Developer developer, String message, int status) {
        this.developer = developer;
        this.message = message;
        this.status = status;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
