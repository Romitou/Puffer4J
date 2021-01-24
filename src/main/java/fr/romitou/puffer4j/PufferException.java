package fr.romitou.puffer4j;

import java.io.IOException;

public class PufferException extends Exception {

    public PufferException(String message, IOException e) {
        super(message + e.getMessage());
    }

    public PufferException(String message) {
        super(message);
    }
}
