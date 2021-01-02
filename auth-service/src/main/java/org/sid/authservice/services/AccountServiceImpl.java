package org.sid.authservice.services;

import java.util.List;

import javax.transaction.Transactional;

import org.sid.authservice.entities.RoleEntity;
import org.sid.authservice.entities.UserEntity;
import org.sid.authservice.repositories.RoleRepository;
import org.sid.authservice.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    
    
    public AccountServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity addNewUser(UserEntity userEntity) {
    	String password = userEntity.getPassword();
        userEntity.setPassword(passwordEncoder.encode(password));
        return userRepository.save(userEntity);
    }

    @Override
    public RoleEntity addNewRole(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        UserEntity userEntity=userRepository.findByUsername(username);
        RoleEntity roleEntity=roleRepository.findByRole(role);
        userEntity.getAppRoles().add(roleEntity);
    }

    @Override
    public UserEntity loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserEntity> listUsers() {
        return userRepository.findAll();
    }
}