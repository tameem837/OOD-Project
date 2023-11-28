package bd.edu.diu.swe.purplesense.service;

import bd.edu.diu.swe.purplesense.config.MyUserDetails;
import bd.edu.diu.swe.purplesense.model.User;
import bd.edu.diu.swe.purplesense.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplement implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

    public User getByUserEmail(String email) {
        return userRepository.getUserByUserName(email);
    }
}
