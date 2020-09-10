package edu.books.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.books.domain.User;
import edu.books.domain.UserRole;
import edu.books.model.SecurityUser;
import edu.books.repository.UserRepository;
import edu.books.repository.UserRoleRepository;

@Transactional
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBuilder builder = null;
        User user = userRepository.findByEmail(username);
        if (user != null) {

            List<UserRole> userRoles = userRoleRepository.findByUser(user);

            List<GrantedAuthority> authorities = buildUserAuthorities(userRoles);

            SecurityUser securityUser = new SecurityUser(user.getEmail(), user.getPassword(), authorities);
            securityUser.setStatus(user.getEnabled());
            return securityUser;
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

    private List<GrantedAuthority> buildUserAuthorities(List<UserRole> userRoles) {

        List<GrantedAuthority> authorities = new ArrayList<>(0);
        for (UserRole userRole : userRoles) {
            String role = userRole.getRole().getName();
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
