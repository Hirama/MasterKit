package super_ego.info.masterkit.model;

/**
 * Created by Andrey on 19.10.2016.
 */

public class TempModel {
    String name;
    Integer imageId;
    String typeOfTraining;
    public String getTypeOfTraining() {
        return typeOfTraining;
    }

    public void setTypeOfTraining(String typeOfTraining) {
        this.typeOfTraining = typeOfTraining;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}
