package com.flexpay.cards.FlexPayCards.controller;

import com.flexpay.cards.FlexPayCards.model.Card;
import com.flexpay.cards.FlexPayCards.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/add-card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/all")
    public String getAllCards(Model model) {
        List<Card> list = cardService.findAll();

        model.addAttribute("listOfCards", list);
        return "list-cards";
    }


    @GetMapping("/add")
    public String getAddCardPage(Model model) {
        model.addAttribute("myNewCard", new Card());
        model.addAttribute("allCardStatuses", Card.CardStatus.values());
        return "add-card";
    }

    @GetMapping("/getEdit/{id}")
    public String getEditPage(@PathVariable("id") Optional<Long> id, Model model) {

        if (id.isPresent()) {
            Card card = cardService.find(id.get());

            model.addAttribute("isEdit", true);
            model.addAttribute("myNewCard", card);
            model.addAttribute("allCardStatuses", Card.CardStatus.listOfActionableStatuses);
        }
        return "add-card";
    }

    @PostMapping("/create-card")
    public String createCard(Card card, Model model) {

        cardService.createCard(card);

        model.addAttribute("message", "Card " + card.getCardNumber() + " was created successfully");
        return "redirect:/add-card/all";
    }
}
