package com.capstone.FMS.FeedbackManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.capstone.FMS.FeedbackManagementSystem.Model.Event;
import com.capstone.FMS.FeedbackManagementSystem.service.EventService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/events")
public class EventsController {

	@Autowired
	private EventService eventService;

	
	@GetMapping(value = "/getEvents")
	@PreAuthorize("hasRole('ADMIN') or hasRole('PMO')") 
	public Flux<Event> getEvents() {
			  return eventService.getAllEventList();
	}	

	@GetMapping("/getEvents/{eventId}")
	@PreAuthorize("hasRole('ADMIN')  or hasRole('PMO')")
	public Flux<Event> getEventsById(@PathVariable String eventId) {
		return eventService.searchEventsById(eventId);
	}
	
	@GetMapping("/getEventsById/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('PMO')")
	public Mono<Event> getEventsById(@PathVariable int id) {
		return eventService.findEventById(id);
	}

	@GetMapping("/getEventsByVh/{vh}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('PMO')")
	public Flux<Event> getEventsByVolunteerHours(@PathVariable int vh) {
		return eventService.searchEventsByVH(vh);
	}

	@GetMapping("/sendEmail")
	@PreAuthorize("hasRole('PMO')")
	public Mono<String> sendEmail() {
		return eventService.SendMail();
	}
}
