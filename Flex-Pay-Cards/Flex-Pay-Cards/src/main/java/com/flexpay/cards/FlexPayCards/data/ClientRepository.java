package com.flexpay.cards.FlexPayCards.data;

import com.flexpay.cards.FlexPayCards.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByFirstName(String name);
}
