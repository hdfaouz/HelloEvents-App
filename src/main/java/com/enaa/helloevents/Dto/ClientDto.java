package com.enaa.helloevents.Dto;

import lombok.*;



public class ClientDto {
    private Long id;
    private String username;
    private String password;

    public ClientDto(Long id, String username, String password) {
        this.id = id;
        this.username = username;

        this.password = password;
    }

    public ClientDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
