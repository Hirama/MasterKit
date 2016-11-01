package super_ego.info.masterkit.model;

/**
 * Created by Andrey on 26.10.2016.
 */

public class ListItemVideoStep {
    private String videoStep;
    private String nameofStep;
    private String question;
    private String youtubelink;

    public ListItemVideoStep(String videoStep, String nameofStep, String question, String youtubelink) {
        this.videoStep = videoStep;
        this.nameofStep = nameofStep;
        this.question = question;
        this.youtubelink = youtubelink;
    }

    public String getVideoStep() {
        return videoStep;
    }

    public void setVideoStep(String videoStep) {
        this.videoStep = videoStep;
    }

    public String getNameofStep() {
        return nameofStep;
    }

    public void setNameofStep(String nameofStep) {
        this.nameofStep = nameofStep;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getYoutubelink() {
        return youtubelink;
    }

    public void setYoutubelink(String youtubelink) {
        this.youtubelink = youtubelink;
    }
}
