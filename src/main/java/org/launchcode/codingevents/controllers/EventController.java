package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
//import org.launchcode.codingevents.models.EventType;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("events")
public class EventController {
//    private static List<Event> events = new ArrayList<>();
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventCategoryRepository eventCategoryRepository;
    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model){
//        events.put("Menteaship","A fun meetup for connecting with mentors");
//        events.put("Code With Pride","A fun meetup sponsored by LaunchCode");
//        events.put("Javascripty", "An imaginary meetup for Javascript developers");
       if(categoryId == null) {
           model.addAttribute("events", eventRepository.findAll());
           model.addAttribute("title", "All Events");
       }else {
           Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
           if(result.isEmpty()){
               model.addAttribute("title","Invalid Category ID: " + categoryId);
           } else {
               EventCategory category = result.get();
               model.addAttribute("title", "Events in Category: " + category.getName());
               model.addAttribute("events", category.getEvents());
           }
       }
        return "events/index";
    }
//lives at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
//            model.addAttribute("errorMsg", "Bad Data!!");
            return "events/create";
        }
//        EventData.add(newEvent);
        eventRepository.save(newEvent);
        return "redirect:/events";
    }
    //lives at /events/delete
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title","Delete Event");
        model.addAttribute("events",eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
//                EventData.remove(id);
                eventRepository.deleteById(id);
            }
        }

        return "redirect:/events";
    }

    //lives at /events/edit/{eventId}
    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId){
//        Event eventToEdit = EventData.getByID(eventId);
        Event eventToEdit = EventData.getByID(eventId);
        model.addAttribute("event", eventToEdit);
        String title = "Edit Event:  " + eventToEdit.getName();
        model.addAttribute("title", title );
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditEventsForm(int eventId, String name, String  description) {
        Event eventToEdit = EventData.getByID(eventId);
        eventToEdit.setName(name);
        eventToEdit.setDescription(description);
        return "redirect:/events";
    }
}
