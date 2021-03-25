package be.technifutur.exoSecu.controllers;

import be.technifutur.exoSecu.dtos.UserDTO;
import be.technifutur.exoSecu.entities.User;
import be.technifutur.exoSecu.mapper.Mapper;
import be.technifutur.exoSecu.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping(path = "/users")
    @CrossOrigin
    public class UserController {

        private final UserService userService;

        private final Mapper mapper;

        public UserController(UserService userService, Mapper mapper) {
            this.userService = userService;
            this.mapper = mapper;
        }

        @PostMapping
        @CrossOrigin
        public ResponseEntity<Boolean> insertUser(@RequestBody User user) {
            return this.userService.insertUser(user) ? ResponseEntity.ok(true) : ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(false);
        }

        @GetMapping
        @CrossOrigin
        public ResponseEntity<List<UserDTO>> getAllUsers() {
            return ResponseEntity.ok(this.userService.getAllUsers());
        }


        @PostMapping(path = "/connected")
        @CrossOrigin
        public ResponseEntity<UserDTO> getUserConnected(@RequestBody User user) {

            return ResponseEntity.ok(this.mapper.toUserDto((User)this.userService.loadUserByUsername(user.getUsername())));
        }
}
