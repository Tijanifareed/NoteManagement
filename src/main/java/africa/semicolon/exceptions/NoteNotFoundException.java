package africa.semicolon.exceptions;

public class NoteNotFoundException extends MyNotesException {
    public NoteNotFoundException(String message){
        super(message);
    }
}
