package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employer",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"name"}
                )
        })
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employer extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @ManyToMany(mappedBy = "employers", fetch = FetchType.LAZY)
    private List<Customer> customers = new ArrayList<>();
}
