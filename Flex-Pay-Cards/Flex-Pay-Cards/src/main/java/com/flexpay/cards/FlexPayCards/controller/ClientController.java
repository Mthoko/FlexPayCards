package com.flexpay.cards.FlexPayCards.controller;

import com.flexpay.cards.FlexPayCards.model.Card;
import com.flexpay.cards.FlexPayCards.model.Client;
import com.flexpay.cards.FlexPayCards.services.CardService;
import com.flexpay.cards.FlexPayCards.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {

@Autowired
private CardService cardService;
@Autowired
private ClientService clientService;

    @GetMapping("/add")
    public String getAddClientPage( Model model) {

        List<Card> listOfCards = cardService.findByCardStatus(Card.CardStatus.INACTIVE);
            model.addAttribute("myClient", new Client());
            model.addAttribute("listOfCards", listOfCards);
            model.addAttribute("selectedCard", listOfCards.get(0));

        return "add-client";
    }

    @PostMapping("/add-client")
    public String createCard(Client client, Model model) {
        clientService.createNewClient(client);
        model.addAttribute("message", "client " + client.getFirstName() + " was created successfully");
        return "redirect:/client/all_clients";
    }

    @GetMapping("/all-clients")
    public String getAllClient(Model model)
    {
        List<Client> clientList = clientService.findAllClients();
        model.addAttribute("listOfClients", clientList);
        return "list-clients";
    }

    @PostMapping("/delete-client")
    public String removeClient(@PathVariable("id") Optional<Long> id, Model model)
    {
        Client client = clientService.findClient(id);

        if(Objects.nonNull(client))
        {
            clientService.deleteClient(client);
        }
        return "redirect:/client/all_clients";

    }



}
