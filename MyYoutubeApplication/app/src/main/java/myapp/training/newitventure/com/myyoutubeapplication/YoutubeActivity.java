package myapp.training.newitventure.com.myyoutubeapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import myapp.training.newitventure.com.myyoutubeapplication.Module.Channel;
import myapp.training.newitventure.com.myyoutubeapplication.Module.Item;
import myapp.training.newitventure.com.myyoutubeapplication.Remote.RetrofitClient;
import myapp.training.newitventure.com.myyoutubeapplication.Remote.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private MyPlayerStateChangeListener playerStateChangeListener;
    private MyPlaybackEventListener playbackEventListener;
    private  MyPlayListEventListener playListEventListener;

    YouTubePlayerView youTubePlayerView;
    TextView txtTitle, txtDescription, txtChannel;
    public String videoId;
    ImageButton imageButton;
    Context context;
   List<Item> channelArrayList = new ArrayList<>();
   RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);


        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);

        youTubePlayerView.initialize("AIzaSyDSWJulhotedal58TWD6KjcJ2SX3GGh6w4",this);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtChannel = (TextView) findViewById(R.id.txtChannel);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        imageButton = (ImageButton) findViewById(R.id.image_btn);

        getIncomingIntent();
        getChannelData();

        playerStateChangeListener = new MyPlayerStateChangeListener();
        playbackEventListener = new MyPlaybackEventListener();
        playListEventListener = new MyPlayListEventListener();


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(YoutubeActivity.this, PlaylistActivity.class);
               //intent.putExtra("uploads",Data.getContentDetails().getRelatedPlaylists().getUploads());
               startActivity(intent);
            }
        });

    }

    private void getChannelData() {
        retrofitInterface = RetrofitClient.getRetrofitClient().create(RetrofitInterface.class);
        Call<Channel> call1 = retrofitInterface.getUploadData();

        call1.enqueue(new Callback<Channel>() {
            @Override
            public void onResponse(@NonNull Call<Channel> call, @NonNull Response<Channel> response) {

                try {

                    channelArrayList = response.body().getItems();
                    Picasso.with(YoutubeActivity.this).load(channelArrayList.get(0).getSnippet().getThumbnails().getDefault().getUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageButton);


                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Channel> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if(null== player) return;

        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        videoId = getIntent().getStringExtra("video");

        if (!wasRestored) {
            player.cueVideo(videoId);
        }
    }

    public void getIncomingIntent() {
        if(getIntent().hasExtra("title")&&getIntent().hasExtra("description")&&getIntent().hasExtra("channel"))
        {
            String title = getIntent().getStringExtra("title");
            String description = getIntent().getStringExtra("description");
            String channelTitle = getIntent().getStringExtra("channel");
            setItem(title, description, channelTitle);
    }
}

    private void setItem(String title, String description, String channelTitle) {
        TextView txt_title = findViewById(R.id.txtTitle);
        txt_title.setText(title);

        TextView txt_description = findViewById(R.id.txtDescription);
        txt_description.setText(description);

        TextView txt_channel = findViewById(R.id.txtChannel);
        txt_channel.setText(channelTitle);

    }


    private class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener{
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    }

    private class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    }

    private class MyPlayListEventListener implements YouTubePlayer.PlaylistEventListener {
        @Override
        public void onPrevious() {

        }

        @Override
        public void onNext() {

        }

        @Override
        public void onPlaylistEnded() {

        }
    }
}
