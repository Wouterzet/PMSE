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
import nl.avans.movieapp.ui.movielist.MoviePageGridAdapter;

/**
 *
 */
public class TvGridAdapter
        extends RecyclerView.Adapter<TvGridAdapter.TvGridViewholder> {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private final ArrayList<Tv> moviesArrayList = new ArrayList<>();
    private final TvGridAdapter.OnTVselectionListener listener;

    public TvGridAdapter(TvGridAdapter.OnTVselectionListener listener) {
        this.listener = listener;
        Log.d(LOG_TAG, "Constructor aangeroepen");
    }

    @NonNull
    @Override
    public TvGridViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LOG_TAG, "onCreateViewHolder aangeroepen");

        int layoutIdForListItem = R.layout.tvshow_list_grid_item;
        final boolean shouldAttachToParentImmediately = false;

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new TvGridViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvGridViewholder holder, int position) {
        Tv movie = moviesArrayList.get(position);
        // Log.d(LOG_TAG, "onBindViewHolder");


        Picasso.get()
                .load(movie.getPoster_path())
                .resize(700, 700)
                .centerInside()
                .into(holder.mPoster);
        if (movie.getName() != null) {
            holder.mTitle.setText(movie.getName());
        }
            holder.mSeasons.setText(String.valueOf(movie.getOverview().substring(0,100).trim() + "...more info"));
            holder.mDate.setText(movie.getFirst_air_date().substring(0,4));
            Log.d("Test", movie.toString());
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

        public TextView mTitle;
        public TextView mDate;
        public TextView mSeasons;
        public ImageView mPoster;

        public TvGridViewholder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_tv_title);
            mDate = (TextView) itemView.findViewById(R.id.tv_tv__griditem_releaseYear);
            mSeasons = (TextView) itemView.findViewById(R.id.tv_tv_seasons);
            mPoster = (ImageView) itemView.findViewById(R.id.tv_griditem_imageurl);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onTVSelected(getAdapterPosition());
        }
    }
    public interface OnTVselectionListener {
        void onTVSelected(int position);
    }
}
