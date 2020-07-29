package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "account",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"number"}
                )
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractEntity{

    @Column(name = "number")
    private String number;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
