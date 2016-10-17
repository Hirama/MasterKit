package super_ego.info.masterkit.model;

import com.google.gson.annotations.SerializedName;
/**
 * Created by Lion on 15.10.2016.
 */
public class TokenPOJO {
    @SerializedName("access_token")
    private String token;


    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


}
