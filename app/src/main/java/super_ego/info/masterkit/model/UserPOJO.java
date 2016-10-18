package super_ego.info.masterkit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 16.10.2016.
 */

public class UserPOJO {
    @SerializedName("id")
    private Integer id;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("email")
    private String email;
    @SerializedName("image")
    private String image;
    @SerializedName("token")
    private String token;
    @SerializedName("material")
    private Integer material;
    @SerializedName("level")
    private Integer level;
    @SerializedName("step")
    private Integer step;
    @SerializedName("steps_count")
    private Integer steps_count;
    @SerializedName("steps_total")
    private Integer steps_total;
    @SerializedName("consciousness")
    private String consciousness;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getMaterial() {
        return material;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getSteps_count() {
        return steps_count;
    }

    public void setSteps_count(Integer steps_count) {
        this.steps_count = steps_count;
    }

    public Integer getSteps_total() {
        return steps_total;
    }

    public void setSteps_total(Integer steps_total) {
        this.steps_total = steps_total;
    }

    public String getConsciousness() {

        return consciousness.replace("%","");
    }

    public void setConsciousness(String consciousness) {
        this.consciousness = consciousness;
    }




}
