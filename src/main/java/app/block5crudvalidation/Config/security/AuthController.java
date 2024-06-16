package app.block5crudvalidation.Config.security;

import app.block5crudvalidation.Config.security.JwtUtils;
import app.block5crudvalidation.User.Domain.Entities.User;
import app.block5crudvalidation.User.Infraestructure.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getGmail(),
                        loginRequest.getContrasena()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                new JwtResponse(
                        jwt,
                        userDetails.getUsername()
                )
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(
            @Valid @RequestBody SignupRequest signUpRequest
    ) {
        if (userRepository.existsByGmail(signUpRequest.getGmail())) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Error: Email is already in use!")
            );
        }

        // Create new user's account
        User user = new User(
                0, // ID should be auto-generated
                signUpRequest.getNombre(),
                signUpRequest.getApellido(),
                signUpRequest.getGmail(),
                encoder.encode(signUpRequest.getContrasena())
        );

        User newUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponse("User registered successfully! " + newUser.toString())
        );
    }
}
