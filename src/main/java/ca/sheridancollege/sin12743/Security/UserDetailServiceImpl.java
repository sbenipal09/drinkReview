package ca.sheridancollege.sin12743.Security;

import ca.sheridancollege.sin12743.repositories.DrinkRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private DrinkRepository  drinkRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  =  drinkRepo.getUserByUsername(username);
        if (user == null){
            System.out.println("User was not found");
            throw  new UsernameNotFoundException("User not Found");
        }
        List<String> roles = drinkRepo.getRolesById(user.getUserId());
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        for(String role : roles){
            grantList.add(new SimpleGrantedAuthority(role));
        }

        org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getEncryptedPassword(),grantList);
        return (UserDetails)springUser;


    }
}
