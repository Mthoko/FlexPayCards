package com.flexpay.cards.FlexPayCards.services;

import com.flexpay.cards.FlexPayCards.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    void createNewClient(Client client);
    void deleteClient(Client client);
    Client findClient(Optional<Long> id);
    Client updateClient(Client client);
    List<Client> findAllClients();
}
