package hhu.propra.bati5a.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AppUserService implements OAuth2UserService {

    @Value("${bat.rollen.admin}") private Set<String> admin;
    private final DefaultOAuth2UserService DefaultOAuth2UserService = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = DefaultOAuth2UserService.loadUser(userRequest);
        Set<GrantedAuthority> authorities=new HashSet<>(user.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        String login= user.getAttribute("login");
        System.out.printf("USER LOGIN: %s%n", login);
        System.out.println("ADMIN : " + admin);
        if (admin.contains(login)){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            System.out.printf("ADMIN PRIVILEGES GRANTED TO USER %s%n", login);
        }
        else {
            System.out.printf("ADMIN PRIVILEGES DENIED TO USER %s%n", login);
        }
        return new DefaultOAuth2User(authorities,user.getAttributes(),"login");
    }
}
