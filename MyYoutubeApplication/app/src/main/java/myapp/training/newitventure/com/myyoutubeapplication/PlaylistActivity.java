package myapp.training.newitventure.com.myyoutubeapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import myapp.training.newitventure.com.myyoutubeapplication.Adapter.PlaylistAdapter;
import myapp.training.newitventure.com.myyoutubeapplication.Mode.Item;
import myapp.training.newitventure.com.myyoutubeapplication.Mode.Playlist;
import myapp.training.newitventure.com.myyoutubeapplication.Remote.RetrofitClient;
import myapp.training.newitventure.com.myyoutubeapplication.Remote.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistActivity extends MainActivity{

    RetrofitInterface retrofitInterface;
    PlaylistAdapter playlistAdapter;
    RecyclerView recyclerView;
    ArrayList<Item> playlistArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        getPlaylistData();

        playlistAdapter = new PlaylistAdapter(this, playlistArrayList, new PlaylistAdapter.PostItemListener(){

            public void onPostClick(String Id) {
                Toast.makeText(PlaylistActivity.this, "Id is" +Id, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PlaylistActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(playlistAdapter);
    }

    private void getPlaylistData() {
        retrofitInterface = RetrofitClient.getRetrofitClient().create(RetrofitInterface.class);
        Call<Playlist> call = retrofitInterface.getPlaylist();

        call.enqueue(new Callback<Playlist>() {
            @Override
            public void onResponse(@NonNull Call<Playlist> call, @NonNull Response<Playlist> response) {

                try {

                    playlistArrayList = (ArrayList<Item>) response.body().getItems();
                    playlistAdapter.updatePlaylist(playlistArrayList);

                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Playlist> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
