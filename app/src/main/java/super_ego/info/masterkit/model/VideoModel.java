package super_ego.info.masterkit.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Andrey on 20.10.2016.
 */

public class VideoModel  {
    String date;
    String name;
    int image;
    String videoId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }


}
