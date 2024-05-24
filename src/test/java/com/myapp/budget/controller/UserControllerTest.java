package com.myapp.budget.controller;

import com.myapp.budget.model.User;
import com.myapp.budget.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userService.findAll()).thenReturn(users);
        ResponseEntity<List<User>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        when(userService.findById("1")).thenReturn(user);
        ResponseEntity<User> response = userController.getUserById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        when(userService.save(user)).thenReturn(user);
        ResponseEntity<User> response = userController.createUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        User updatedUser = new User();
        updatedUser.setEmail("newemail@example.com");
        updatedUser.setUsername("newusername");
        updatedUser.setPassword("newpassword");

        when(userService.findById("1")).thenReturn(user);
        when(userService.save(user)).thenReturn(updatedUser);

        ResponseEntity<User> response = userController.updateUser("1", updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userService).deleteUser("1");
        ResponseEntity<User> response = userController.deleteUser("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
