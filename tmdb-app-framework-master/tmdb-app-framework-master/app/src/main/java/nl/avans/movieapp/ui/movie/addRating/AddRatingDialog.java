package nl.avans.movieapp.ui.movie.addRating;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.slider.Slider;

import org.jetbrains.annotations.NotNull;

import nl.avans.movieapp.R;
import nl.avans.movieapp.controller.AddRatingController;
import nl.avans.movieapp.controller.MovieController;
import nl.avans.movieapp.ui.movie.addToList.AddMovieToListDialog;

public class AddRatingDialog extends DialogFragment {

    private Button button;
    private Slider slider;
    private int movieId;

    public AddRatingDialog(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.dialog_add_rating, container);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(inflater.inflate(R.layout.dialog_add_rating, container))
                .setMessage(R.string.dialog_add_to_movielist_message)
                .setTitle(R.string.dialog_add_to_movielist_title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        AddRatingDialog.this.getDialog().cancel();
                    }
                });

        this.slider = root.findViewById(R.id.rating_slider);
        this.button = root.findViewById(R.id.rating_button);

        this.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddRatingController controller = new AddRatingController();
                controller.addRating(slider.getValue(), movieId);
                AddRatingDialog.this.getDialog().cancel();
                Toast toast = Toast.makeText(root.getContext(), "Succes!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return root;
    }
}
