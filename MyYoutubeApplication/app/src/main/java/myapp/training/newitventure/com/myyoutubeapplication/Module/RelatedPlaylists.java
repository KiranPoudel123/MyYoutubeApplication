package myapp.training.newitventure.com.myyoutubeapplication.Module;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedPlaylists {

    @SerializedName("uploads")
    @Expose
    private String uploads;

    public String getUploads() {
        return uploads;
    }

    public void setUploads(String uploads) {
        this.uploads = uploads;
    }

}