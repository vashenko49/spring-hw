package org.example.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "account",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"number"}
                )
        })
public class Account extends AbstractEntity implements Serializable {

    @Column(name = "number")
    private String number;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
