package com.flexpay.cards.FlexPayCards.services;

import com.flexpay.cards.FlexPayCards.data.CardRepository;
import com.flexpay.cards.FlexPayCards.data.ClientRepository;
import com.flexpay.cards.FlexPayCards.model.Card;
import com.flexpay.cards.FlexPayCards.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void createNewClient(Client client) {
        String selectedCardNumber = client.getSelectedCardNumber();
        Card selectedCard = cardRepository.findByCardNumber(selectedCardNumber);

        Predicate<Card> cardPredicate = card -> card.getCardStatus() == Card.CardStatus.ACTIVE;
        if (client.getListOfCards().stream().filter(cardPredicate).count() <= 6) {
           if( client.getListOfCards().stream().noneMatch(Card::isPrimaryIndicator))
           {
               selectedCard.setPrimaryIndicator(true);
           }
            selectedCard.setActivatedate(new Date());
            selectedCard.setExpiryDate(getCardExpiryDate());
            selectedCard.setCardStatus(Card.CardStatus.ACTIVE);

            client.getListOfCards().add(selectedCard);
            clientRepository.save(client);
        }
    }

    private Date getCardExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 5);
        return calendar.getTime();
    }

    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public Client findClient(long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client updateClient(Client client) {
            clientRepository.save(client);
        return client;
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> findByName(String name)
    {
        return clientRepository.findByFirstName(name);
    }

}
