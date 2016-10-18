package super_ego.info.masterkit.model;

import java.util.List;

/**
 * Created by Andrey on 18.10.2016.
 */

public class LearingPlanPOJO {

    private Integer code;

    private List<MaterialsPOJO> data;

    public List<MaterialsPOJO> getResult() {
        return data;
    }

    public void setResult(List<MaterialsPOJO> result) {
        this.data = result;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}
