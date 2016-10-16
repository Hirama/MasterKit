package super_ego.info.masterkit.model;

import com.google.gson.annotations.SerializedName;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
/**
 * Created by Lion on 15.10.2016.
 */
public class User {
    @SerializedName("access_token")
    private String token;


    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


}
