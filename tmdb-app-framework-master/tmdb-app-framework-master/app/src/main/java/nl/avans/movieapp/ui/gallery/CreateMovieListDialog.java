package nl.avans.movieapp.ui.gallery;

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

public class CreateMovieListDialog extends DialogFragment {

    private final String LOG_TAG = this.getClass().getSimpleName();

    // Use this instance of the interface to deliver action events
    private MovieListsController.MovieListsControllerListener listener;

    public CreateMovieListDialog(MovieListsController.MovieListsControllerListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(inflater.inflate(R.layout.dialog_create_movielist, null))
                .setMessage(R.string.dialog_create_movielist_message)
                .setTitle(R.string.dialog_create_movielist_title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText etListName = (EditText) getDialog().findViewById(R.id.dialog_create_movielist_listname);
                        String listName = etListName.getText().toString();
                        Log.d(LOG_TAG, "New list name is " + listName);

                        // To Do: add and get the description field
                        String description = "No description given yet";

                        // Call API request
                        CreateMovieListController controller = new CreateMovieListController(listener);
                        controller.createMovieList(listName, description);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        CreateMovieListDialog.this.getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
