package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employer",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"name"}
                )
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer extends AbstractEntity {
    @Column(name = "name")
    private String name;
    private String address;
    @ManyToMany(mappedBy = "employers")
    private List<Customer> customers = new ArrayList<>();
}
