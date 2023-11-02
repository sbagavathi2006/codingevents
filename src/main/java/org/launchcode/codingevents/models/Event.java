package org.launchcode.codingevents.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.Objects;
@Entity
public class Event extends AbstractEntity{
//    @Id
//    @GeneratedValue
//    private int id;
//    private static int nextId=1;
    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
//    private EventType type;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails eventDetails;
    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

    public Event(String name, EventCategory eventCategory, EventDetails eventDetails) {
//        this();
        this.name = name;
        this.eventCategory = eventCategory;
        this.eventDetails = eventDetails;
    }
    public Event(){
//        this.id = nextId;
//        nextId++;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }
    //    public int getId(){
//        return id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Event event = (Event) o;
//        return id == event.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}