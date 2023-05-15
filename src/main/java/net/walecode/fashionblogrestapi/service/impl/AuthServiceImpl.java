package net.walecode.fashionblogrestapi.service.impl;

import net.walecode.fashionblogrestapi.dto.LoginDto;
import net.walecode.fashionblogrestapi.dto.RegisterDto;
import net.walecode.fashionblogrestapi.entity.Role;
import net.walecode.fashionblogrestapi.entity.User;
import net.walecode.fashionblogrestapi.exception.BlogAPIException;
import net.walecode.fashionblogrestapi.repository.RoleRepository;
import net.walecode.fashionblogrestapi.repository.UserRepository;
import net.walecode.fashionblogrestapi.security.JwtTokenProvider;
import net.walecode.fashionblogrestapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username  already exist!.");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email already exist!.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully!.";
    }
}



















//=========================================================================


//Basic Login and register


//package net.walecode.fashionblogrestapi.service.impl;
//
//import net.walecode.fashionblogrestapi.dto.LoginDto;
//import net.walecode.fashionblogrestapi.dto.RegisterDto;
//import net.walecode.fashionblogrestapi.entity.Role;
//import net.walecode.fashionblogrestapi.entity.User;
//import net.walecode.fashionblogrestapi.exception.BlogAPIException;
//import net.walecode.fashionblogrestapi.repository.RoleRepository;
//import net.walecode.fashionblogrestapi.repository.UserRepository;
//import net.walecode.fashionblogrestapi.service.AuthService;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class AuthServiceImpl implements AuthService{
//
//    private AuthenticationManager authenticationManager;
//
//    private UserRepository userRepository;
//
//    private RoleRepository rolerepository;
//
//    private PasswordEncoder passwordEncoder;
//
//    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository rolerepository, PasswordEncoder passwordEncoder ) {
//        this.authenticationManager = authenticationManager;
//        this.rolerepository = rolerepository;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public String login(LoginDto loginDto) {
//
//       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                loginDto.getUsernameOrEmail(), loginDto.getPassword()
//        ));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return "User Logged In Successfully!";
//    }
//
//    @Override
//    public String register(RegisterDto registerDto) {
//
//        if (userRepository.existsByUsername(registerDto.getUsername())){
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username already exist");
//        }
//        if (userRepository.existsByEmail(registerDto.getEmail())){
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email already exist");
//        }
//
//        User user = new User();
//
//        user.setName(registerDto.getName());
//        user.setUsername(registerDto.getUsername());
//        user.setEmail(registerDto.getEmail());
//        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//
//
//        Set<Role> roles = new HashSet<>();
//        Role userRole = rolerepository.findByName("ROLE_USER").get();
//        roles.add(userRole);
//        user.setRoles(roles);
//
//        userRepository.save(user);
//
//
//        return "User registered successfully!";
//    }
//}