package be.technifutur.exoSecu.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VoyageNotFoundException extends Exception {

    public VoyageNotFoundException(String message) {
        super(message);
    }
}
