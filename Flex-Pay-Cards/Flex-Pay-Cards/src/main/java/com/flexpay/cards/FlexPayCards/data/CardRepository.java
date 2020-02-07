package com.flexpay.cards.FlexPayCards.data;

import com.flexpay.cards.FlexPayCards.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardNumber(String cardNumber);

    List<Card> findByCardStatus(Card.CardStatus status);
}
