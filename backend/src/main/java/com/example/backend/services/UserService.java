package com.example.backend.services;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.user.SignInDto;
import com.example.backend.dto.user.SignInResponseDto;
import com.example.backend.dto.user.SignupDto;
//import com.example.backend.enums.ResponseStatus;
import com.example.backend.enums.Role;
import com.example.backend.models.AuthenticationToken;
import com.example.backend.models.User;
import com.example.backend.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

@Service
public class UserService {
    final UserRepo userRepository;

    final AuthService authenticationService;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(AuthService authenticationService, UserRepo userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }


    public ResponseDto signUp(SignupDto signupDto) throws Exception {
        logger.info("g---email" + signupDto.toString());
        if (userRepository.findByEmail(signupDto.getEmail()) != null) {
            throw new Exception("User already exists");
        }
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }


        User user = new User(signupDto.getName(), signupDto.getEmail(), Role.user, encryptedPassword);

        User createdUser;
        try {
            // save the User
            createdUser = userRepository.save(user);
            // generate token for user
            final AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
            // save token in database
            authenticationService.saveConfirmationToken(authenticationToken);
            // success in creating
            return new ResponseDto("SUCCESS", "USER_CREATED");
        } catch (Exception e) {
            // handle signup error
            throw new Exception(e.getMessage());
        }
    }

    public SignInResponseDto signIn(SignInDto signInDto) throws  Exception {
        // first find User by email
        System.out.println("User Pass ********" + signInDto.getEmail()  +  " " + signInDto.getPassword());
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (user == null) {
            throw new Exception("user not present");
        }
        try {
            // check if password is right
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                // passowrd doesnot match
                throw new Exception("MessageStrings.WRONG_PASSWORD");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
            throw new Exception(e.getMessage());
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if (token == null) {
            // token not present
            throw new Exception("token not present");
        }

        return new SignInResponseDto("success", token.getToken());
    }


    String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }


    boolean canCrudUser(Role role) {
        if (role == Role.admin || role == Role.manager) {
            return true;
        }
        return false;
    }

    boolean canCrudUser(User userUpdating, Integer userIdBeingUpdated) {
        Role role = userUpdating.getRole();
        // admin and manager can crud any user
        if (role == Role.admin || role == Role.manager) {
            return true;
        }
        // user can update his own record, but not his role
        if (role == Role.user && userUpdating.getId() == userIdBeingUpdated) {
            return true;
        }
        return false;
    }
}
