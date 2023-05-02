package com.contact.service.repositories;

import com.contact.service.entities.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Contacts, Integer> {
}
