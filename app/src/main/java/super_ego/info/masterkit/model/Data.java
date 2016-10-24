package super_ego.info.masterkit.model;

import java.util.ArrayList;
import java.util.List;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("finish")
    @Expose
    private List<Integer> finish = new ArrayList<Integer>();
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("mp3")
    @Expose
    private String mp3;
    @SerializedName("ogg")
    @Expose
    private String ogg;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("training_form")
    @Expose
    private TrainingForm trainingForm;
    @SerializedName("training_back_form")
    @Expose
    private Boolean trainingBackForm;
    @SerializedName("next_button")
    @Expose
    private String nextButton;
    @SerializedName("back_button")
    @Expose
    private Boolean backButton;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The finish
     */
    public List<Integer> getFinish() {
        return finish;
    }

    /**
     * @param finish The finish
     */
    public void setFinish(List<Integer> finish) {
        this.finish = finish;
    }

    /**
     * @return The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return The mp3
     */
    public String getMp3() {
        return mp3;
    }

    /**
     * @param mp3 The mp3
     */
    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    /**
     * @return The ogg
     */
    public String getOgg() {
        return ogg;
    }

    /**
     * @param ogg The ogg
     */
    public void setOgg(String ogg) {
        this.ogg = ogg;
    }

    /**
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return The trainingForm
     */
    public TrainingForm getTrainingForm() {
        return trainingForm;
    }

    /**
     * @param trainingForm The training_form
     */
    public void setTrainingForm(TrainingForm trainingForm) {
        this.trainingForm = trainingForm;
    }

    /**
     * @return The trainingBackForm
     */
    public Boolean getTrainingBackForm() {
        return trainingBackForm;
    }

    /**
     * @param trainingBackForm The training_back_form
     */
    public void setTrainingBackForm(Boolean trainingBackForm) {
        this.trainingBackForm = trainingBackForm;
    }

    /**
     * @return The nextButton
     */
    public String getNextButton() {
        return nextButton;
    }

    /**
     * @param nextButton The next_button
     */
    public void setNextButton(String nextButton) {
        this.nextButton = nextButton;
    }

    /**
     * @return The backButton
     */
    public Boolean getBackButton() {
        return backButton;
    }

    /**
     * @param backButton The back_button
     */
    public void setBackButton(Boolean backButton) {
        this.backButton = backButton;
    }

}
