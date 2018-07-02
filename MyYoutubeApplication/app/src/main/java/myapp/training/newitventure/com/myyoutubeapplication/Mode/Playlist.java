package myapp.training.newitventure.com.myyoutubeapplication.Mode;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playlist {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Playlist withItems(List<Item> items) {
        this.items = items;
        return this;
    }

}