package ru.itis.healthauthimpl.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "account")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;
}
