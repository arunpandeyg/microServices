package com.contact.service.controller;

import com.contact.service.entities.Contacts;
import com.contact.service.repositories.ContactRepo;
import com.contact.service.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class Controller {
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepo contactRepo;
    //get all contacts
    @GetMapping("/")
    public List<Contacts> getAllEmployees() {
        return contactRepo.findAll();
    }

    //get contacts
    @GetMapping("/{userId}")
    public ResponseEntity<Contacts> getById(@PathVariable Integer userId) {

        Optional<Contacts> user = contactService.getContactsOfUser(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            throw new RuntimeException("Contact not found");
        }
    }

    //create contacts
    @PostMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contacts> create(@RequestBody Contacts newContacts) throws ServerException {

        Contacts contacts = contactRepo.save(newContacts);
        if (contacts == null) {
            throw new ServerException("Server is down now wait for some time!");
        } else {
            return new ResponseEntity<>(contacts, HttpStatus.CREATED);
        }
    }
    //update contacts
    @PutMapping("/{userId}")
    public ResponseEntity<Contacts> updateContacts(@PathVariable Integer userId,@RequestBody Contacts contactDetails) {
        Contacts updateContacts = contactRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Contact not exist with id: " + userId));

        updateContacts.setContactEmail(contactDetails.getContactEmail());
        updateContacts.setContactName(contactDetails.getContactName());
        updateContacts.setUserId(contactDetails.getUserId());
        contactRepo.save(updateContacts);

        return ResponseEntity.ok(updateContacts);
    }
    //delete contacts
    @DeleteMapping("/{userId}")
    public Map<String, Boolean> deleteContact(@PathVariable(value = "userId") Integer userId)
            throws RuntimeException {
        Contacts contacts = contactRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Employee not found for this id :: " + userId));

        contactRepo.delete(contacts);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
