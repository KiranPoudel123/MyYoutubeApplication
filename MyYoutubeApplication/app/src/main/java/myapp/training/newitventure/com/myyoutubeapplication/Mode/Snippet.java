package myapp.training.newitventure.com.myyoutubeapplication.Mode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Snippet {

    @SerializedName("channelId")
    @Expose
    private String channelId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("thumbnails")
    @Expose
    private Thumbnails thumbnails;
    @SerializedName("channelTitle")
    @Expose
    private String channelTitle;
    @SerializedName("localized")
    @Expose
    private Localized localized;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Snippet withChannelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Snippet withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Snippet withDescription(String description) {
        this.description = description;
        return this;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public Snippet withThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
        return this;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public Snippet withChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
        return this;
    }

    public Localized getLocalized() {
        return localized;
    }

    public void setLocalized(Localized localized) {
        this.localized = localized;
    }

    public Snippet withLocalized(Localized localized) {
        this.localized = localized;
        return this;
    }

}