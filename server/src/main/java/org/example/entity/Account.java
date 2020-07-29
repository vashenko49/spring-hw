package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;


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

    @Column(name = "number", nullable = false)
    private String number;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Size
    @Column(nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
