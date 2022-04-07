package nl.avans.movieapp.ui.movie.addToList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import nl.avans.movieapp.R;
import nl.avans.movieapp.domain.Movie;
import nl.avans.movieapp.domain.MovieList;
import nl.avans.movieapp.ui.movielist.MoviePageGridAdapter;

public class AddMovieToListAdapter extends RecyclerView.Adapter<AddMovieToListAdapter.AddMovieToListViewHolder> {

    private OnMovieListSelectionListener listener;
    private ArrayList<MovieList> movieLists = new ArrayList<>();

    public AddMovieToListAdapter(OnMovieListSelectionListener listener) {
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public AddMovieToListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Log.d("LOG_TAG", "onCreateViewHolder aangeroepen");

        int layoutIdForListItem = R.layout.dialog_select_movielist_item;
        final boolean shouldAttachToParentImmediately = false;

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new AddMovieToListAdapter.AddMovieToListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AddMovieToListViewHolder holder, int position) {
        MovieList movieList = movieLists.get(position);
        if(movieList.getName() != null) {
            holder.textView.setText(movieList.getName().trim());
        }
    }

    @Override
    public int getItemCount() {
        return movieLists.size();
    }

    public void setMovieLists(ArrayList<MovieList> movies) {
        Log.d("LOG_TAG", "setMovieList");
        this.movieLists.clear();
        this.movieLists.addAll(movies);
        this.notifyDataSetChanged();
    }

    public class AddMovieToListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;

        public AddMovieToListViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.select_movielist_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onMovieListSelected(getAdapterPosition());
        }

    }

    public interface OnMovieListSelectionListener {
        void onMovieListSelected(int position);
    }
}
