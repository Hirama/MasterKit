package super_ego.info.masterkit.model;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
/**
 * Created by Lion on 15.10.2016.
 */
public class User {
    private String id;
    private String email;
    private String token;

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public MultiValueMap<String, String> setHeader() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("X-User-Email", this.email);
        headers.add("X-User-Token", this.token);
        headers.add("Accepted", "application/json");
        return headers;
    }
}
