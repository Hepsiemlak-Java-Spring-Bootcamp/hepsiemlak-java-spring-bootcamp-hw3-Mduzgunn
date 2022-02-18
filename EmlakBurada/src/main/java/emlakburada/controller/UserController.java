package emlakburada.controller;

import emlakburada.dto.Messagerequest;
import emlakburada.dto.UserRequest;
import emlakburada.dto.response.MessageResponse;
import emlakburada.dto.response.UserResponse;
import emlakburada.service.KullaniciService;
import emlakburada.service.MesajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private KullaniciService kullaniciService;

    @GetMapping(value = "/user")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(kullaniciService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        return new ResponseEntity<>(kullaniciService.saveUser(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{userNo}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(required = false) int userNo) {
        return new ResponseEntity<>(kullaniciService.getUserById(userNo), HttpStatus.OK);
    }

    @GetMapping(value = "/favs/{userId}")
    public ResponseEntity<UserResponse> getAdvertByUserId(@PathVariable(required = false) int userId) {
        return new ResponseEntity<>(kullaniciService.getUserById(userId), HttpStatus.OK);
    }
}
