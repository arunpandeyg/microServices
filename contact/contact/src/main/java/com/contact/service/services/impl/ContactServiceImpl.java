package com.contact.service.services.impl;

import com.contact.service.entities.Contacts;
import com.contact.service.repositories.ContactRepo;
import com.contact.service.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    public ContactRepo contactRepo;

    @Override
    public Optional<Contacts> getContactsOfUser(Integer userId) {
        Optional<Contacts> contacts = this.contactRepo.findById(userId);
        return contacts;
    }




}
