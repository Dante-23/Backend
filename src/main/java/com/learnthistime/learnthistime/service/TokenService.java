package com.learnthistime.learnthistime.service;

import com.learnthistime.learnthistime.config.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.learnthistime.learnthistime.models.User;

import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class TokenService {
    private final ConcurrentHashMap<String, String> userTokenMap;
    private final JwtService jwtService;
    private final UserRepoService userRepoService;

    public boolean addUserToken(String username, String token) {
        if (containsTokenForUser(username)) return false;
        userTokenMap.put(username, token);
        return true;
    }

    public boolean containsTokenForUser(String username) {
        if (userTokenMap.containsKey(username)) return true;
        else return false;
    }

    public void removeUserToken(String username) {
        if (!containsTokenForUser(username)) return;
        userTokenMap.remove(username);
    }

    public boolean isUserTokenValid(String username) {
        if (!containsTokenForUser(username)) {
            System.out.println("!containsTokenForUser(user)");
            return false;
        }
        String token = userTokenMap.get(username);
        User user = userRepoService.getUserFromUserName(username);
        if (user == null) throw new NullPointerException();
        if (jwtService.isTokenValid(token, user)) return true;
        else {
            System.out.println("!jwtService.isTokenValid(token, user)");
            return false;
        }
    }

    public String getUserToken(String username) {
        if (!containsTokenForUser(username)) return null;
        else return userTokenMap.get(username);
    }
    public void testHello() {
        if (userTokenMap != null) {
            System.out.println("Hello from Token Service");
        } else {
            System.out.println("Hello from Token Service. NULL.");
        }
    }
}
