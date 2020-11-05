package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Customer implements Comparable<Customer> {

    private long id;
    private String name;
    @JsonProperty("duetime")
    private ZonedDateTime dueTime;
    @JsonProperty("jointime")
    private ZonedDateTime joinTime;

    public Customer() {}

    public Customer(long id, String name, ZonedDateTime dueTime, ZonedDateTime joinTime) {
        this.id = id;
        this.name = name;
        this.dueTime = dueTime;
        this.joinTime = joinTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(ZonedDateTime dueTime) {
        this.dueTime = dueTime;
    }

    public ZonedDateTime getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(ZonedDateTime joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    public int compareTo(Customer customer) {
        return getDueTime().compareTo(customer.getDueTime());
    }
}
