package org.sid.authservice.services;

import java.util.List;

import org.sid.authservice.entities.RoleEntity;
import org.sid.authservice.entities.UserEntity;


public interface AccountService {
    UserEntity addNewUser(UserEntity userEntity);
    RoleEntity addNewRole(RoleEntity roleEntity);
    void addRoleToUser(String username,String name);
    UserEntity loadUserByUsername(String username);
    List<UserEntity> listUsers();
}
