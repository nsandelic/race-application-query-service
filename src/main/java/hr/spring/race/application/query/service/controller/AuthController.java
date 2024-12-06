package hr.spring.race.application.query.service.controller;
import hr.spring.race.application.query.service.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final JwtUtil jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtUtil jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        String role;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

             role = authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jwtUtils.generateToken(username, role);
    }
}
