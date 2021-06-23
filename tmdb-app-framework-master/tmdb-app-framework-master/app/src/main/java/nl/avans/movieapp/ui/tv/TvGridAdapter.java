package nl.avans.movieapp.ui.tv;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import nl.avans.movieapp.R;
import nl.avans.movieapp.domain.Comment;
import nl.avans.movieapp.domain.Tv;

/**
 *
 */
public class TvGridAdapter
        extends RecyclerView.Adapter<TvGridAdapter.TvGridViewholder> {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private final ArrayList<Tv> moviesArrayList = new ArrayList<>();

    public TvGridAdapter() {
        Log.d(LOG_TAG, "Constructor aangeroepen");
    }

    @NonNull
    @Override
    public TvGridViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LOG_TAG, "onCreateViewHolder aangeroepen");

        int layoutIdForListItem = R.layout.comments_movie_detail;
        final boolean shouldAttachToParentImmediately = false;

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new TvGridViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvGridViewholder holder, int position) {
        Tv movie = moviesArrayList.get(position);
        // Log.d(LOG_TAG, "onBindViewHolder");

//
//        holder.mUsername.setText(movie.getAuthor());
//        holder.mUserComment.setText(movie.getContent());


    }

    @Override
    public int getItemCount() {
        return moviesArrayList.size();
    }

    public void setMovieList(List<Tv> movies) {
        Log.d(LOG_TAG, "setMovieList");
        this.moviesArrayList.clear();
        this.moviesArrayList.addAll(movies);
        this.notifyDataSetChanged();
    }

    public class TvGridViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mUsername;
        public TextView mUserComment;

        public TvGridViewholder(@NonNull View itemView) {
            super(itemView);
            mUsername = (TextView) itemView.findViewById(R.id.tv_comment_username);
            mUserComment = (TextView) itemView.findViewById(R.id.tv_comment_content);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
    public interface OnMovieSelectionListener {
        void onMovieSelected(int position);
    }
}
