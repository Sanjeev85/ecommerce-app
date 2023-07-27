package com.example.backend.services;


import com.example.backend.models.AuthenticationToken;
import com.example.backend.models.User;
import com.example.backend.repositories.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final TokenRepo tokenRepo;

    public AuthService(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepo.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepo.findTokenByUser(user);
    }


    public User getUser(String token) {
        AuthenticationToken authenticationToken = tokenRepo.findTokenByToken(token);
        if (authenticationToken != null) {
            if (authenticationToken.getUser() != null) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }


    public void authenticate(String token) throws Exception {
        if (token == null) {
            throw new Exception("MessageStrings.AUTH_TOEKN_NOT_PRESENT");
        }
        var findToken = tokenRepo.findTokenByToken(token);
        if (findToken == null)
            throw new Exception("MessageStrings.AUTH_TOEKN_NOT_VALID");
    }
}