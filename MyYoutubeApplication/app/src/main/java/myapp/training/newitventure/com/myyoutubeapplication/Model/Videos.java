package myapp.training.newitventure.com.myyoutubeapplication.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Videos {

    @SerializedName("items")
    @Expose
    private List<Item> items= null;

/**
 *
 * @return
 * The items
 */

    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */

    public void setItems(List<Item> items) {
        this.items = items;
    }

}

