package nl.avans.movieapp.ui.movie.addToList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.CreateMovieListController;
import nl.avans.movieapp.controller.MovieListsController;
import nl.avans.movieapp.ui.gallery.CreateMovieListDialog;

public class AddMovieToListDialog extends DialogFragment {
    private final String LOG_TAG = this.getClass().getSimpleName();

    // Use this instance of the interface to deliver action events
    private MovieListsController.MovieListsControllerListener listener;

    public AddMovieToListDialog(MovieListsController.MovieListsControllerListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(inflater.inflate(R.layout.dialog_select_movielist, null))
                .setMessage(R.string.dialog_create_movielist_message)
                .setTitle(R.string.dialog_create_movielist_title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        AddMovieToListDialog.this.getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
