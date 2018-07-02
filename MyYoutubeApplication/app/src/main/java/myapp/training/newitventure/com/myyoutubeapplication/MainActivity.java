package myapp.training.newitventure.com.myyoutubeapplication;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import myapp.training.newitventure.com.myyoutubeapplication.Adapter.MainAdapter;
import myapp.training.newitventure.com.myyoutubeapplication.Mode.Playlist;
import myapp.training.newitventure.com.myyoutubeapplication.Model.Item;
import myapp.training.newitventure.com.myyoutubeapplication.Model.Videos;
import myapp.training.newitventure.com.myyoutubeapplication.Module.Channel;
import myapp.training.newitventure.com.myyoutubeapplication.Remote.RetrofitClient;
import myapp.training.newitventure.com.myyoutubeapplication.Remote.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends YoutubeActivity{

    RetrofitInterface retrofitInterface;
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    ProgressDialog pDialog;
    VideoView videoview;
    String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    ArrayList<Item> itemArrayList = new ArrayList<>();
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoview = (VideoView) findViewById(R.id.video_view);

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setTitle("Android Video Streaming Tutorial");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

        try {

            MediaController mediacontroller = new MediaController(MainActivity.this);
            mediacontroller.setAnchorView(videoview);

            Uri video = Uri.parse(VideoURL);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);

            mediacontroller.setPrevNextListeners(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();
                }
            });
            mediacontroller.show(10000);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoview.start();
            }
        });

        mainAdapter = new MainAdapter(this, itemArrayList, new MainAdapter.PostItemListener(){

            @Override
            public void onPostClick(String id) {
                Toast.makeText(MainActivity.this, "Id is" + id, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);

        getVideoData();
        getVideo1Data();
        getVideo2Data();

        id = getIntent().getStringExtra("id");
    }

    private void getVideo1Data () {

         retrofitInterface = RetrofitClient.getRetrofitClient().create(RetrofitInterface.class);
         Call<Videos> call2 = retrofitInterface.getData();

         call2.enqueue(new Callback<Videos>() {
             @Override
             public void onResponse(@NonNull Call<Videos> call, @NonNull Response<Videos> response) {

                 try {

                     itemArrayList = (ArrayList<Item>) response.body().getItems();
                     mainAdapter.updateVideos(itemArrayList);

                 } catch (NullPointerException e) {
                     e.printStackTrace();
                 }
             }

             @Override
             public void onFailure(@NonNull Call<Videos> call, @NonNull Throwable t) {
                 Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });
     }

     private void getVideo2Data () {
         retrofitInterface = RetrofitClient.getRetrofitClient().create(RetrofitInterface.class);
         Call<Videos> call1 = retrofitInterface.getData1();

         call1.enqueue(new Callback<Videos>() {
             @Override
             public void onResponse(@NonNull Call<Videos> call, @NonNull Response<Videos> response) {

                 try {

                     itemArrayList = (ArrayList<Item>) response.body().getItems();
                     mainAdapter.updateVideos(itemArrayList);

                 } catch (NullPointerException e) {
                     e.printStackTrace();
                 }
             }

             @Override
             public void onFailure(@NonNull Call<Videos> call, @NonNull Throwable t) {
                 Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });
     }

     private void getVideoData () {
         retrofitInterface = RetrofitClient.getRetrofitClient().create(RetrofitInterface.class);
         Call<Videos> call = retrofitInterface.getSnippetData();

         call.enqueue(new Callback<Videos>() {
             @Override
             public void onResponse(@NonNull Call<Videos> call, @NonNull Response<Videos> response) {

                 try {

                     itemArrayList = (ArrayList<Item>) response.body().getItems();
                     mainAdapter.updateVideos(itemArrayList);

                 } catch (NullPointerException e) {
                     e.printStackTrace();
                 }
             }

             @Override
             public void onFailure(@NonNull Call<Videos> call, @NonNull Throwable t) {
                 Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });
     }
 }

