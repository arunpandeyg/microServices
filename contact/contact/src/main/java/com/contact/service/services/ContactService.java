package com.contact.service.services;

import com.contact.service.entities.Contacts;

import java.util.Optional;

public interface ContactService {
    public Optional<Contacts> getContactsOfUser(Integer userId);
}
