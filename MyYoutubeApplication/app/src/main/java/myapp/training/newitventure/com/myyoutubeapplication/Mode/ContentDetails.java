package myapp.training.newitventure.com.myyoutubeapplication.Mode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentDetails {

    @SerializedName("itemCount")
    @Expose
    private Integer itemCount;

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public ContentDetails withItemCount(Integer itemCount) {
        this.itemCount = itemCount;
        return this;
    }

}