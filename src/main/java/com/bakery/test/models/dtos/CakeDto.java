package com.bakery.test.models.dtos;

import com.bakery.test.entities.Cake;
import com.bakery.test.models.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@JsonView
public class CakeDto {

    private int id;
    private String cakeTitle;
    private String status;

    public CakeDto() {
    }

    public CakeDto(String cakeTitle) {
        this.cakeTitle = cakeTitle;
    }

    public CakeDto(int id, String cakeTitle, StatusType status) {
        this.id = id;
        this.cakeTitle = cakeTitle;
        this.status = status.title;
    }

    public Cake toEntity(int daysDuration) {
        Calendar calendar = new GregorianCalendar();
        Date start = calendar.getTime();
        calendar.add(Calendar.DATE,daysDuration);
        Date end = calendar.getTime();
        return new Cake(
                this.getCakeTitle(),
                start,
                end);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCakeTitle() {
        return cakeTitle;
    }

    public void setCakeTitle(String cakeTitle) {
        this.cakeTitle = cakeTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status.title;
    }
}
