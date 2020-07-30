package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"email"}
                )
        })
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Customer extends AbstractEntity {
    @Column(nullable = false)
    private String name;
    @Column( nullable = false)
    private String email;
    @Column(nullable = false )
    private Integer age;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String phone;
    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Account> accounts = new ArrayList<>();
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.LAZY)
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
