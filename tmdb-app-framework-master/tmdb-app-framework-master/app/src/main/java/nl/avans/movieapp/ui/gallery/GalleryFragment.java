package nl.avans.movieapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.MovieListsController;
import nl.avans.movieapp.domain.MovieList;

public class GalleryFragment extends Fragment {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private final ArrayList<MovieList> movieLists = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MovieListAdapter movieListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.gallery_movielists);

        int numGridColumns = 1;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(container.getContext(), numGridColumns);
        mRecyclerView.setLayoutManager(layoutManager);
        movieListAdapter = new MovieListAdapter(movieLists);
        mRecyclerView.setAdapter(movieListAdapter);

        // Call API request
        MovieListsController movieListsController = new MovieListsController(movieListAdapter);
        movieListsController.loadMovieListsForUser();

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a Create MovieList dialog to get the new list name.
                // We need to get back to the current activity when the user
                // has typed in the new list name. 'This' is the listenener.
                CreateMovieListDialog dialog = new CreateMovieListDialog(movieListAdapter);
                dialog.show(getParentFragmentManager(), "CreateNewList");
            }
        });


        return root;
    }

//    @Override
//    public void onDialogPositiveClick(DialogFragment dialog) {
//        // User clicked OK in the CreateMovieListDialog
//    }
//
//    @Override
//    public void onDialogNegativeClick(DialogFragment dialog) {
//        // User clicked Cancel in the CreateMovieListDialog
//        // The dialog closes itself, no further action required.
//    }
}