
package myapp.training.newitventure.com.myyoutubeapplication.Mode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Standard {

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Standard withUrl(String url) {
        this.url = url;
        return this;
    }

}