package super_ego.info.masterkit.model;

import java.util.List;

/**
 * Created by Lion on 18.10.2016.
 */

public class GoalResultPOJO {
    private String code;
    private GoalSectionPOJO data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GoalSectionPOJO getData() {
        return data;
    }

    public void setData(GoalSectionPOJO data) {
        this.data = data;
    }
}
