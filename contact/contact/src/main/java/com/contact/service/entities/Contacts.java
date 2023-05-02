package com.contact.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int contactId;
    private String contactEmail;
    private String contactName;
    private  int userId;
}
