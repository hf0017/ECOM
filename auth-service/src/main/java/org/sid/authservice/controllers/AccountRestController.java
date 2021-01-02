package org.sid.authservice.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sid.authservice.config.JWTUtil;
import org.sid.authservice.entities.RoleEntity;
import org.sid.authservice.entities.RoleForm;
import org.sid.authservice.entities.UserEntity;
import org.sid.authservice.services.AccountService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;




@RestController
public class AccountRestController {
    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/users")
    @PostAuthorize("hasAuthority('USER')")
    public List<UserEntity> appUsers(){
        return accountService.listUsers();
    }

    @PostMapping(path = "/users")
    @PostAuthorize("hasAuthority('ADMIN')")
    public UserEntity saveUser(@RequestBody UserEntity userEntity){
        return accountService.addNewUser(userEntity);
    }

    @PostMapping(path = "/roles")
    @PostAuthorize("hasAuthority('ADMIN')")
    public RoleEntity savaRole(@RequestBody RoleEntity roleEntity){
        return accountService.addNewRole(roleEntity);
    }

    @PostMapping(path = "/addRoleToUser")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void addRoleToUser(@RequestBody RoleForm roleForm){
        accountService.addRoleToUser(roleForm.getUsername(),roleForm.getRole());
    }

    @GetMapping(path = JWTUtil.ENDPOINTS)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authToken=request.getHeader(JWTUtil.AUTH_HEADER);
        if(authToken!=null && authToken.startsWith(JWTUtil.PREFIX)){
            try {
                String jwt=authToken.substring(JWTUtil.PREFIX_LENGTH);
                Algorithm algorithm=Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jwtVerifier= JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username=decodedJWT.getSubject();
                UserEntity userEntity=accountService.loadUserByUsername(username);
                String jwtAccessToken = JWT.create()
                        .withSubject(userEntity.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",userEntity.getAppRoles().stream().map(role -> role.getRole()).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> idToken =new HashMap<>();
                idToken.put("access-token",jwtAccessToken);
                idToken.put("refresh-token",jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);

            }
            catch (Exception e){
                throw e;
            }
        }
        else {
            throw new RuntimeException("Refresh token required");
        }
    }
    
    @GetMapping(path = "/profile")
    public UserEntity profile(Principal principal){
        return accountService.loadUserByUsername(principal.getName());
    }
}
