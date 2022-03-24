package nl.lyashevska.mmtspringboot.service;

import nl.lyashevska.mmtspringboot.controller.UserRegistrationDto;
import nl.lyashevska.mmtspringboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

// method to save user data
public interface UserService extends UserDetailsService {
   User save(UserRegistrationDto registrationDto);

}
