package com.microservices.controller;




import com.microservices.entities.Contact;
import com.microservices.entities.User;
import com.microservices.repositories.UserRepo;
import com.microservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class Controller {
    @Autowired
    private UserService userService;
    @Autowired
    public UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;
    //add new user
    @PostMapping("/")
    public String addUser(@RequestBody User user){
        userService.saveUser(user);
        return "New User is added";
    }

    //get by id
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId){


        Optional<User> user = Optional.ofNullable(userService.getUserById(userId));

        if (user.isPresent()) {


            return new ResponseEntity<>(user.get(), HttpStatus.OK);

        } else {
            throw new RuntimeException("user Not found "+userId);
        }
    }
    @GetMapping(value = "/template/contacts")
    public String getContactList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(
                "http://localhost:8090/contacts/", HttpMethod.GET, entity, String.class).getBody();
    }
    //getting contacts by userId
    @GetMapping(value = "/template/contacts/{userId}")
    public String getContactById(@PathVariable Integer userId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(
                "http://contact/contacts/"+userId, HttpMethod.GET, entity, String.class).getBody();
    }

    //get all users
    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    //update user
    @PutMapping("/{userId}")
    public User updateUser(@RequestBody User newUser, @PathVariable Integer userId) {

        return userRepo.findById(userId).map(user -> {
            user.setName(newUser.getName());
            user.setPhone(newUser.getPhone());

            return userRepo.save(user);
        }).orElseGet(() -> {
            newUser.setUserId(userId);
            return userRepo.save(newUser);
        });
    }
    //deleting an user
    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable Integer userId) {
        userRepo.deleteById(userId);
    }
}
