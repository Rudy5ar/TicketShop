package org.ticketshop.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketshop.dto.UserDTO;
import org.ticketshop.mapper.UserMapper;
import org.ticketshop.model.User;
import org.ticketshop.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable long id) {
        try {
            return new ResponseEntity<>(userMapper.toDto(userService.getUser(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return new ResponseEntity<>(userService.getUsers(PageRequest.of(pageNumber, pageSize))
                .stream().map(userMapper::toDto).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            User savedUser = userService.saveUser(userMapper.fromDto(userDTO));
            return new ResponseEntity<>(userMapper.toDto(savedUser), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
        try {
            User updatedUser = userService.updateUser(userMapper.fromDto(userDTO), id);
            return new ResponseEntity<>(userMapper.toDto(updatedUser), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable long id) {
        userService.deleteUser(id);
    }

}
