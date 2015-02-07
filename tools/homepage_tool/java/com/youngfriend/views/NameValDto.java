package com.youngfriend.views;

/**
 * Created by pandongyu on 15/1/4.
 */
public class NameValDto {
    private String name;
    private String value;

    public NameValDto(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public NameValDto(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NameValDto)) return false;

        NameValDto that = (NameValDto) o;

        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
