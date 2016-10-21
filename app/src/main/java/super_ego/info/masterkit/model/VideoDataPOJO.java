package super_ego.info.masterkit.model;

/**
 * Created by Lion on 21.10.2016.
 */

public class VideoDataPOJO {
    private Integer id;
    private String title;
    private String subtitle;
    private String video;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
