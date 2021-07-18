package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.dao.RoleDao;
import com.example.dao.UserDao;
import com.example.model.Role;
import com.example.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Autowired
    void setRoleDao(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.getUserWithRoles (name);
        return (UserDetails) user;
    }
}
