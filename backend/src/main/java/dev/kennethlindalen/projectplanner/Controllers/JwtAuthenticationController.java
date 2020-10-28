package dev.kennethlindalen.projectplanner.Controllers;


import dev.kennethlindalen.projectplanner.Configurations.JwtTokenUtil;
import dev.kennethlindalen.projectplanner.Users.JWT.JwtRequest;
import dev.kennethlindalen.projectplanner.Users.JWT.JwtResponse;
import dev.kennethlindalen.projectplanner.Users.UserDTO;
import dev.kennethlindalen.projectplanner.Users.UserRepository;
import dev.kennethlindalen.projectplanner.Services.JwtUserDetailsService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {

        EmailValidator emailValidator = EmailValidator.getInstance();

        if (emailValidator.isValid(user.getEmail())){
            if (userRepository.findByEmail(user.getEmail()) != null) {
                return new ResponseEntity<>("That email has already been registrered", HttpStatus.FORBIDDEN);
            } else if(userRepository.findByUsername(user.getUsername()) != null){
                return new ResponseEntity<>("That username has already been registrered", HttpStatus.FORBIDDEN);
            }else {
                return new ResponseEntity<>(userDetailsService.saveUser(user), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("Please supply a valid email address.", HttpStatus.CONFLICT);
        }


    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}