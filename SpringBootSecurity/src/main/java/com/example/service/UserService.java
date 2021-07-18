package com.example.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.model.User;

import java.util.List;

public interface UserService  {
     void deleteById(Long id);
     User updateUserAndHisRoles(User user,List<String> rolesValues);
     User getUserById(Long id);
     List <String> getRoles();
     List<User> listUsersWithRoles();
     User addUserWithRoles(User user,List<String> roles);
}
