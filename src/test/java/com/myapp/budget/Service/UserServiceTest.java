package com.myapp.budget.Service;

import com.myapp.budget.model.User;
import com.myapp.budget.repository.UserRepository;
import com.myapp.budget.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testSave() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.save(user));
    }

    @Test
    public void testFindAll() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(users, userService.findAll());
    }

    @Test
    public void testFindById() {
        User user = new User();
        when(userRepository.findById("123")).thenReturn(Optional.of(user));
        assertEquals(user, userService.findById("123"));
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById("123");
        userService.deleteUser("123");
        verify(userRepository, times(1)).deleteById("123");
    }
}
