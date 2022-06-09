package com.brainstars.bullsandcows.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @Column(name = "enabled")
  boolean isEnabled;
}
