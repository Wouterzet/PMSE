package nl.avans.movieapp.service;

import java.util.List;

import nl.avans.movieapp.domain.MovieList;

/**
 * Dit object komt overeen met het response zoals je dat van de API in het response ontvangt.
 * Je maakt dus voor een API, voor de JSON die je ontvangt, een specifiek response class.
 *
 * {
 *     "status_code": 1,
 *     "status_message": "The item/record was created successfully.",
 *     "success": true,
 *     "list_id": 7086750
 * }
 *
 *
 */
public class CreateMovieListApiResponse {

    private final int status_code;
    private final String status_message;
    private final int list_id;

    public CreateMovieListApiResponse(int status_code, String status_message, int list_id) {
        this.status_code = status_code;
        this.status_message = status_message;
        this.list_id = list_id;
    }

    public int getStatus_code() {
        return status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public int getList_id() {
        return list_id;
    }

    @Override
    public String toString() {
        return "CreateMovieListApiResponse{" +
                "status_code=" + status_code +
                ", status_message='" + status_message + '\'' +
                ", list_id=" + list_id +
                '}';
    }
}


