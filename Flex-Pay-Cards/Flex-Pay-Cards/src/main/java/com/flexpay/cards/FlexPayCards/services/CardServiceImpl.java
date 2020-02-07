package com.flexpay.cards.FlexPayCards.services;

import com.flexpay.cards.FlexPayCards.data.CardRepository;
import com.flexpay.cards.FlexPayCards.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional// this will make all public methods in there transactional. If you want  a single method to be transactional instead of all, do this anotation at the method level and remove it from the class level
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;
    @Override
    public void createCard(Card card) {
        if(card.getCreatedDate() == null) {
            card.setCreatedDate(new Date());
        }
        cardRepository.save(card);
    }

    @Override
    public void removeCard(Card card) {
        cardRepository.delete(card);
    }

    @Override
    public Card updateCard(Card card) {
        if(!card.getCardStatus().equals(Card.CardStatus.INACTIVE)) {
            return cardRepository.save(card);
        }
        return card;
    }

    @Override
    public Card findByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card find(long id) {
        return cardRepository.findById(id).orElse(null);

    }

    @Override
    public List<Card> findByCardStatus(Card.CardStatus status) {
        return cardRepository.findByCardStatus(status);
    }

}
