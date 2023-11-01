package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    @Size(max = 500, message = "Description too long!")
    private String description;
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    @NotBlank(message = "Location is required")
    private String location;

    @AssertTrue(message = "Registration is required")
    private boolean registered = true;

    @Positive(message="Number of attendees must be one or more.")
    private int numberOfAttendees;
//    private EventType type;
    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

    public Event(String name, String description, String contactEmail, EventCategory eventCategory) {
//        this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.eventCategory = eventCategory;
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


    public Event(String name, String description, String contactEmail, String location, int numberOfAttendees, boolean registered) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.registered = registered;
        this.location = location;
        this.numberOfAttendees =numberOfAttendees;
    }
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isRegistered() {
        return registered;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
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

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description =description;
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