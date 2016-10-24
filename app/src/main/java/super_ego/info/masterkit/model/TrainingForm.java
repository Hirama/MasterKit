
package super_ego.info.masterkit.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import super_ego.info.masterkit.model.Field;

@Generated("org.jsonschema2pojo")
public class TrainingForm {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("input")
    @Expose
    private Boolean input;
    @SerializedName("fields")
    @Expose
    private List<Field> fields = new ArrayList<Field>();

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The input
     */
    public Boolean getInput() {
        return input;
    }

    /**
     * 
     * @param input
     *     The input
     */
    public void setInput(Boolean input) {
        this.input = input;
    }

    /**
     * 
     * @return
     *     The fields
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * 
     * @param fields
     *     The fields
     */
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

}
