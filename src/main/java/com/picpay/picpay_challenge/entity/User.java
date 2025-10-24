package com.picpay.picpay_challenge.entity;

import com.picpay.picpay_challenge.enums.TypeUser;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, length = 11)
    private String cpf;

    @Column(unique = true, length = 100)
    private String email;

    private String password;

    private TypeUser typeUser;
}
