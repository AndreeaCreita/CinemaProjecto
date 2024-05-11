package com.example.CinemaProjecto.controllersThymeleaf;

import com.example.CinemaProjecto.dtos.RequestCreateTicketDto;
import com.example.CinemaProjecto.dtos.RequestUpdateTicketDto;
import com.example.CinemaProjecto.dtos.TicketDto;
import com.example.CinemaProjecto.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ThymeleafTicketController {
    private final TicketService ticketService;

    @GetMapping("/tickets/view/create")
    public String showCreateTicketForm(Model model) {
        model.addAttribute("ticket", new RequestCreateTicketDto());
        return "create-ticket";
    }

    @PostMapping("/tickets/view/buy")
    public String createTicket(RequestCreateTicketDto ticketDto, Model model) {
        try {
            TicketDto createdTicket = ticketService.createTicket(ticketDto);
            model.addAttribute("ticket", createdTicket);
            return "redirect:/tickets/view/" + createdTicket.getId();
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to create the ticket.");
            return "error";
        }
    }

    @GetMapping("/tickets/view/{ticketId}")
    public String getTicketById(@PathVariable Long ticketId, Model model) {
        try {
            TicketDto ticket = ticketService.getTicketById(ticketId);
            if (ticket == null) {
                model.addAttribute("errorMessage", "No ticket found with ID: " + ticketId);
                return "ticket-not-found";
            }
            model.addAttribute("ticket", ticket);
            return "ticket-details";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while trying to fetch ticket details.");
            return "error";
        }
    }

    @PostMapping("/tickets/view/update")
    public String updateTicket(RequestUpdateTicketDto ticketDto, Model model) {
        try {
            TicketDto updatedTicket = ticketService.updateTicketDate(ticketDto.getTicketId(), ticketDto.getMovieTime());
            model.addAttribute("ticket", updatedTicket);
            return "redirect:/tickets/view/" + updatedTicket.getId();
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update the ticket.");
            return "redirect:/tickets/view/" + ticketDto.getTicketId() + "?error=updatefailed";
        }
    }

    @PostMapping("/tickets/view/cancel/{ticketId}")
    public String cancelTicket(@PathVariable Long ticketId, Model model) {
        try {
            ticketService.cancelTicket(ticketId);
            model.addAttribute("message", "Your ticket has been deleted.");
            return "redirect:/tickets/view/create";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to cancel the ticket.");
            return "redirect:/tickets/view/" + ticketId + "?error=couldnotcancel";
        }
    }
}
