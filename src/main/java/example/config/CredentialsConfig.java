package example.config;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter @Setter
@Component
@ConfigurationProperties(prefix = "tests")
public class CredentialsConfig {

    private Map<String,UserCredentials> credentials = Maps.newHashMap();
    private UserCredentials get(String type) { return credentials.get(type);}


    public String username(String type){return get(type).getUsername();}
    public String password(String type){return get(type).getPassword();}

    @Getter @Setter
    public static class UserCredentials{
        private String username;
        private String password;
    }
}
