package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;


@Entity
public class EventDetails extends AbstractEntity{
    @OneToOne(mappedBy = "eventDetails")
    private Event event;
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
    public EventDetails(String description, String contactEmail, String location, int numberOfAttendees, boolean registered, Event event) {
        this.description = description;
        this.contactEmail = contactEmail;
        this.registered = registered;
        this.location = location;
        this.numberOfAttendees =numberOfAttendees;
        this.event =event;
    }
    public  EventDetails(){ }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
