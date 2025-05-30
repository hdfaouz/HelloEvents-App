package com.enaa.helloevents.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


public class EvenementDto {
    private Long idEvent;
    private String eventname;
    private String description;

    public EvenementDto(Long idEvent, String eventname, String description) {
        this.idEvent = idEvent;
        this.eventname = eventname;
        this.description = description;
    }

    public EvenementDto() {
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


