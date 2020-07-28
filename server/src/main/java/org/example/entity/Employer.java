package org.example.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "employer",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"name"}
                )
        })
public class Employer extends AbstractEntity implements Serializable {
    @Column(name = "name")
    private String name;
    private String address;
   @ManyToMany(mappedBy = "employers")
    private List<Customer> customers = new ArrayList<>();
}
