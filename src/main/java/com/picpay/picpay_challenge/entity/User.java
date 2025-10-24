package com.picpay.picpay_challenge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 300)
    String nomeCompleto;

    @Column(unique = true)
    String cpf;

    @Column(unique = true)
    String email;

    String senha;
}
