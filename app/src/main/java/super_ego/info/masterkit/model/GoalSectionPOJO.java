package super_ego.info.masterkit.model;

import java.util.List;

/**
 * Created by Lion on 18.10.2016.
 */

public class GoalSectionPOJO {
    private List<GoalsPOJO> money;
    private List<GoalsPOJO> relationship;
    private List<GoalsPOJO> destiny;
    private List<GoalsPOJO> body;

    public List<GoalsPOJO> getMoney() {
        return money;
    }

    public void setMoney(List<GoalsPOJO> money) {
        this.money = money;
    }

    public List<GoalsPOJO> getRelationship() {
        return relationship;
    }

    public void setRelationship(List<GoalsPOJO> relationship) {
        this.relationship = relationship;
    }

    public List<GoalsPOJO> getDestiny() {
        return destiny;
    }

    public void setDestiny(List<GoalsPOJO> destiny) {
        this.destiny = destiny;
    }

    public List<GoalsPOJO> getBody() {
        return body;
    }

    public void setBody(List<GoalsPOJO> body) {
        this.body = body;
    }
}
