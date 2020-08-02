package org.example.entity;

import lombok.*;
import org.example.entity.enums.Currency;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Account extends AbstractEntity {
    @GeneratorType(type = CustomGeneratorUUID.class, when = GenerationTime.INSERT)
    @Column(name = "number", unique = true, nullable = false, updatable = false)
    private String number;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(nullable = false)
    private Double balance;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Customer customer;
}
