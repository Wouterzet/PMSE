package nl.avans.movieapp.service;

public class RemoveMovieFromListApiResponse {

    private final boolean succes;
    private final int status_code;
    private final String status_message;

    public RemoveMovieFromListApiResponse(boolean succes, int status_code, String status_message) {
        this.succes = succes;
        this.status_code = status_code;
        this.status_message = status_message;
    }

    public boolean isSucces() {
        return  succes;
    }

    public int getStatus_code() {
        return status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    @Override
    public String toString() {
        return "RemoveMovieFromListApiResponse{" +
                "succes=" + succes +
                ", status_code=" + status_code +
                ", status_message='" + status_message + '\'' +
                '}';
    }
}
