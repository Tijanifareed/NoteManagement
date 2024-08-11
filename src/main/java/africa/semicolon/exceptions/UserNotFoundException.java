package africa.semicolon.exceptions;

public class UserNotFoundException extends MyNotesException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
