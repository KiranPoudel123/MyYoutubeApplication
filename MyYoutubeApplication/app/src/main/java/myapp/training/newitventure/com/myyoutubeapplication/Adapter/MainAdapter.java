package myapp.training.newitventure.com.myyoutubeapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import myapp.training.newitventure.com.myyoutubeapplication.Model.Item;
import myapp.training.newitventure.com.myyoutubeapplication.R;
import myapp.training.newitventure.com.myyoutubeapplication.YoutubeActivity;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final Context context;
    private final PostItemListener mItemListener;
    private List<Item> dataList;

    public MainAdapter(Context context, List<Item> dataList, PostItemListener postItemListener) {
        this.dataList =  dataList;
        this.context = context;
        this.mItemListener = postItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_view, parent,false);
        ViewHolder viewHolder = new ViewHolder(view,this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.ViewHolder viewHolder, int position) {

            final Item List = dataList.get(position);

            viewHolder.txt_title.setText(List.getSnippet().getTitle());
            viewHolder.txt_description.setText(List.getSnippet().getDescription());
            viewHolder.txt_channel.setText(List.getSnippet().getChannelTitle());
            Picasso.with(context).load(List.getSnippet().getThumbnails().getDefault().getUrl())
                    .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(viewHolder.imageView);


        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String video = List.getSnippet().getResourceId().getVideoId();
                Toast.makeText(context,"you clicked" + List.getSnippet().getTitle(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(v.getContext(),YoutubeActivity.class);
                intent.putExtra("title", List.getSnippet().getTitle());
                intent.putExtra("description", List.getSnippet().getDescription());
                intent.putExtra("channel", List.getSnippet().getChannelTitle());
                intent.putExtra("video", video);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public  void updateVideos(List<Item>videos){
        dataList = videos;
        notifyDataSetChanged();
    }

    private Item getItem(int adapterPosition) {
        return dataList.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(String id);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_title, txt_channel, txt_description;
        LinearLayout linearLayout;
        PostItemListener mItemListener;
        ImageView imageView;


        ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);

            txt_title = itemView.findViewById(R.id.txt_title);
            txt_channel = itemView.findViewById(R.id.txt_channel);
            txt_description = itemView.findViewById(R.id.txt_description);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            imageView = itemView.findViewById(R.id.imageView);

        }

        @Override
        public void onClick(View v) {
            notifyDataSetChanged();

            Item item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getSnippet().getPlaylistId());

        }
    }
}
