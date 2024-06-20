package com.learnthistime.learnthistime.service;
//
import com.learnthistime.learnthistime.config.JwtService;
import com.learnthistime.learnthistime.repo.TokenRepository;
import com.learnthistime.learnthistime.repo.UserRepo;
import com.learnthistime.learnthistime.utils.AuthenticationRequest;
import com.learnthistime.learnthistime.utils.AuthenticationResponse;
import com.learnthistime.learnthistime.utils.RegisterRequest;
import com.learnthistime.learnthistime.utils.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import com.learnthistime.learnthistime.models.User;
import com.learnthistime.learnthistime.models.Token;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
//    private final UserRepo userRepository;
//    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepoService userRepoService;
//
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .userName(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build();
        var savedUser = userRepoService.insert(user);
        if (!savedUser) {
            return AuthenticationResponse.builder()
                    .accessToken("User already exists")
                    .build();
        }
        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//        saveUserToken(savedUser, jwtToken);
        tokenService.addUserToken(user.getUsername(), jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
                .build();
    }

//    private void saveUserToken(User user, String jwtToken) {
//        var token = Token.builder()
//                .user(user)
//                .token(jwtToken)
//                .tokenType(TokenType.BEARER)
//                .expired(false)
//                .revoked(false)
//                .build();
//        tokenRepository.save(token);
//    }

//
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepoService.getUserFromUserName(request.getUsername());
        if (user == null) {
            throw new NullPointerException();
        }
        if (tokenService.isUserTokenValid(user.getUsername())) {
            System.out.println("Found already active user token");
            String token = tokenService.getUserToken(user.getUsername());
            return AuthenticationResponse.builder()
                    .accessToken(token)
                    .build();
        } else {
            System.out.println("User token is not invalid");
            tokenService.removeUserToken(user.getUsername());
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
        tokenService.addUserToken(user.getUsername(), jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
                .build();
//        return null;
    }

    public String logout(String username) {
        User user = userRepoService.getUserFromUserName(username);
        if (user == null) throw new NullPointerException();
        tokenService.removeUserToken(username);
        SecurityContextHolder.clearContext();
        return username + " logged out";
    }
//
//    private void saveUserToken(User user, String jwtToken) {
//        var token = Token.builder()
//                .user(user)
//                .token(jwtToken)
//                .tokenType(TokenType.BEARER)
//                .expired(false)
//                .revoked(false)
//                .build();
//        tokenRepository.save(token);
//    }
//
//    private void revokeAllUserTokens(User user) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//    }
//
//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String refreshToken;
//        final String userEmail;
//        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        refreshToken = authHeader.substring(7);
//        userEmail = jwtService.extractUsername(refreshToken);
//        if (userEmail != null) {
//            var user = this.repository.findByUserName(userEmail)
//                    .orElseThrow();
//            if (jwtService.isTokenValid(refreshToken, user)) {
//                var accessToken = jwtService.generateToken(user);
//                revokeAllUserTokens(user);
//                saveUserToken(user, accessToken);
//                var authResponse = AuthenticationResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
//    }
}