package com.flexpay.cards.FlexPayCards.services;

import com.flexpay.cards.FlexPayCards.model.Card;
import com.flexpay.cards.FlexPayCards.model.Client;

import java.util.List;

public interface ClientService {

    void createNewClient(Client client);
    void deleteClient(Client client);
    Client findClient(long id);
    Client updateClient(Client client);
    List<Client> findAllClients();
}
