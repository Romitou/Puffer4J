package fr.romitou.puffer4j;

import retrofit2.Response;

import java.io.IOException;

public class PufferException extends Exception {

    public PufferException(String message) {
        super(message);
    }

    public PufferException(String message, IOException e) {
        super("An error occurred while " + message + ": " + e);
    }

    public <T> PufferException(Response<T> response) {
        super("Request on " + response.raw().request().url() + " was not successful and returned " + HttpResponse.valueOf("HTTP_" + response.code()).label + " HTTP code.");
    }
}
