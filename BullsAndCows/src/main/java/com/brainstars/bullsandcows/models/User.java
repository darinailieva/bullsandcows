package com.brainstars.bullsandcows.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column (name = "enabled")
    boolean isEnabled;
}
