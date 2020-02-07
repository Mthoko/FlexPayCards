package com.flexpay.cards.FlexPayCards.services;

import com.flexpay.cards.FlexPayCards.model.Card;

import java.util.List;

public interface CardService {

    void createCard(Card card);
    void removeCard(Card card);
    Card updateCard(Card card);
    Card findByCardNumber(String cardNumber);

    List<Card> findAll();

    Card find(long id);

    List<Card> findByCardStatus(Card.CardStatus status);
}
