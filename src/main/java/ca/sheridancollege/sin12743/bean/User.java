package ca.sheridancollege.sin12743.bean;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Long userId;
    private String userName;
    private String encryptedPassword;
    private byte enabled;

    public User(String userName, String encryptedPassword, List<GrantedAuthority> grantList) {
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;
    }
}