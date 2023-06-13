package com.github.amarpreetbhatia.phonenumbers.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PhoneNumberVO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("mobileNumber")
    private String number;
    @JsonProperty("customerId")
    private Long customerId;
    @JsonProperty("active")
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumberVO that = (PhoneNumberVO) o;
        return active == that.active && Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, customerId, active);
    }
}

