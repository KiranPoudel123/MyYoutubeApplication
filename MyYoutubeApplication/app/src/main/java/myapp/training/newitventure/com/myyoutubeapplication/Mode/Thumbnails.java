package myapp.training.newitventure.com.myyoutubeapplication.Mode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnails {

    @SerializedName("standard")
    @Expose
    private Standard standard;

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public Thumbnails withStandard(Standard standard) {
        this.standard = standard;
        return this;
    }

}
