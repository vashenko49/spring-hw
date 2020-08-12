package org.example.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
public class Customer extends AbstractEntity implements UserDetails {
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String phone;
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Account> accounts = new ArrayList<>();
    @ManyToMany(cascade = {
            CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "customer_employer",
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "employer_id", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"customer_id", "employer_id"}
            )}
    )
    List<Employer> employers = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
