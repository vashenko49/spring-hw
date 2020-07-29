package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"email"}
                )
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends AbstractEntity {
    private String name;
    @Column(name = "email")
    private String email;
    private Integer age;
    private String password;
    private String phone;
    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
