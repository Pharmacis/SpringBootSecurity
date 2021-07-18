package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.model.Role;
import com.example.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist (role);
    }

    @Override
    public Role getRoleByName(String name) {
        return (Role) entityManager.createQuery ("from Role where role =:name")
                .setParameter ("name", name)
                .getSingleResult ();
    }

    @Override
    public Role update(Role role) {
        return entityManager.merge (role);
    }

    @Override
    public List<String> getRoles() {
        return entityManager.createQuery ("select role from Role ")
                .getResultList ();
    }
}
