package be.technifutur.exoSecu.exceptions;

public class PasswordNotValidException extends Exception{
    public PasswordNotValidException() {
        super("Invalid Password");
    }
}
