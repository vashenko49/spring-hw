package org.example.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"email"}
                )
        })
public class Customer extends AbstractEntity implements Serializable {
    private String name;
    @Column(name = "email")
    private String email;
    private Integer age;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Account> accounts = new ArrayList<>();
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "customer_employer",
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "employer_id",referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"customer_id", "employer_id"}
            )}
    )
    List<Employer> employers = new ArrayList<>();
}
