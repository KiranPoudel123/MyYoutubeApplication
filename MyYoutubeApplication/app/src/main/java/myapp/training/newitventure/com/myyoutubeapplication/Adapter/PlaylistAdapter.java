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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import myapp.training.newitventure.com.myyoutubeapplication.MainActivity;
import myapp.training.newitventure.com.myyoutubeapplication.Mode.Item;
import myapp.training.newitventure.com.myyoutubeapplication.R;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    private List<Item> videoList;
    Context context;
    PostItemListener mItemListener;

    public PlaylistAdapter(Context context, ArrayList<Item> videoList, PostItemListener postItemListener) {
        this.context = context;
        this.mItemListener = postItemListener;
        this.videoList = videoList;
    }


    @NonNull
    @Override
    public PlaylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.playlist_item, parent,false);
        PlaylistAdapter.ViewHolder viewHolder = new PlaylistAdapter.ViewHolder(view,this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.ViewHolder holder, int position) {

        final Item Set = videoList.get(position);
        holder.pl_title.setText(Set.getSnippet().getTitle());
        Picasso.with(context).load(Set.getSnippet().getThumbnails().getStandard().getUrl())
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.image_view);

        holder.linear_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("id",Set.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    private Item getItem(int adapterPosition) {
        return videoList.get(adapterPosition);
    }

    public void updatePlaylist(List<Item> data) {
        videoList = data;
        notifyDataSetChanged();
    }

    public interface PostItemListener {
        void onPostClick(String id);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private final PostItemListener mItemListener;
        TextView pl_title;
        ImageView image_view;
        LinearLayout linear_layout;

        ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            this.mItemListener = postItemListener;

            pl_title = itemView.findViewById(R.id.pl_title);
            image_view = itemView.findViewById(R.id.image_view);
            linear_layout = itemView.findViewById(R.id.linear_layout);
        }

        @Override
        public void onClick(View v) {
            notifyDataSetChanged();

            Item item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getId());
        }
    }
}
