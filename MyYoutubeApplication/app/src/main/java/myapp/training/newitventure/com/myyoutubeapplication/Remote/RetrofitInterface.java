package myapp.training.newitventure.com.myyoutubeapplication.Remote;

import myapp.training.newitventure.com.myyoutubeapplication.Mode.Playlist;
import myapp.training.newitventure.com.myyoutubeapplication.Model.Videos;
import myapp.training.newitventure.com.myyoutubeapplication.Module.Channel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    //%2C = , and %2F = / in url
    @GET("playlistItems?part=snippet&maxResults=8&playlistId=PLw4Q6fU31Lt8zpS9KMCyUfvhrwfSXCkRJ&fields=items(id,snippet(channelTitle,channelId,description,resourceId/videoId,thumbnails/default,title))&key=AIzaSyDSWJulhotedal58TWD6KjcJ2SX3GGh6w4")
    Call<Videos> getData();

    @GET("playlistItems?part=snippet&maxResults=8&playlistId=PLw4Q6fU31Lt-k4ksuhBRc8eER_6aOB4rL&fields=items(id,snippet(channelTitle,channelId,description,resourceId/videoId,thumbnails/default,title))&key=AIzaSyDSWJulhotedal58TWD6KjcJ2SX3GGh6w4")
    Call<Videos> getData1();

    @GET("playlistItems?part=snippet&maxResults=8&playlistId=PLw4Q6fU31Lt8mY_2eZqBwGZWdz7jdgbdj&fields=items(id,snippet(channelTitle,channelId,description,resourceId/videoId,thumbnails/default,title))&key=AIzaSyDSWJulhotedal58TWD6KjcJ2SX3GGh6w4")
    Call<Videos> getSnippetData();

    @GET("channels?part=snippet,contentDetails,statistics&id=UC6am0tFqAQVqYwF2YV31zZQ&fields=items(contentDetails/relatedPlaylists/uploads,id,snippet(thumbnails/default/url,title))&key=AIzaSyDSWJulhotedal58TWD6KjcJ2SX3GGh6w4")
    Call<Channel> getUploadData();

    @GET("playlists?part=snippet,contentDetails&channelId=UC6am0tFqAQVqYwF2YV31zZQ&maxResults=30&fields=items(contentDetails,id,snippet(channelId,channelTitle,description,localized,thumbnails/standard/url,title))&key=AIzaSyDSWJulhotedal58TWD6KjcJ2SX3GGh6w4")
    Call<Playlist> getPlaylist();
}



