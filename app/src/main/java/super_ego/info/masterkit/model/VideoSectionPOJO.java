package super_ego.info.masterkit.model;

import java.util.List;

/**
 * Created by Lion on 21.10.2016.
 */

public class VideoSectionPOJO {
    private VideoDataPOJO last;
    private List<VideoDataPOJO> history;

    public VideoDataPOJO getLast() {
        return last;
    }

    public void setLast(VideoDataPOJO last) {
        this.last = last;
    }

    public List<VideoDataPOJO> getHistory() {
        return history;
    }

    public void setHistory(List<VideoDataPOJO> history) {
        this.history = history;
    }
}
