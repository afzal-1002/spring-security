package com.spring.security.wut.spring.security.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String userName;
   private String password;
   private String jwtToken;


}
