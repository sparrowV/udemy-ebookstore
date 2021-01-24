package ge.odvali.ebookstore.services;

import ge.odvali.ebookstore.entities.User;
import ge.odvali.ebookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                    + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";

    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public boolean isValidEmail(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public ResponseEntity save(User user) {
        validateUser(user);
        User saved = userRepository.save(user);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    private void validateUser(User user) {
        if (!StringUtils.hasText(user.getFirstName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "first name should not be empty");
        }
        if (!StringUtils.hasText(user.getLastName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "last name should not be empty");
        }
        if (!StringUtils.hasText(user.getEmail()) || !isValidEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "please provide a valid email!");
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
