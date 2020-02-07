package com.flexpay.cards.FlexPayCards.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "Cards")
public class Card {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String cardNumber;
    private String cardId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date activatedate;
    private boolean primaryIndicator;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;
    private BigDecimal availableBalance = BigDecimal.ZERO;
    private BigDecimal actualBalance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus = CardStatus.INACTIVE;

    public enum CardStatus {
        INACTIVE("Inactive"), ACTIVE("Active/Current"), CLOSED("Closed/Unusable"), STOLEN("Stolen"), LOST("Lost");

        private final String statusName;

        private CardStatus(String statusName)
        {
            this.statusName = statusName;
        }

        public String getStatusName() {
            return statusName;
        }

        public static CardStatus[] listOfActionableStatuses = new CardStatus[]
                {
                        CLOSED, STOLEN, LOST
                };
    }
}
