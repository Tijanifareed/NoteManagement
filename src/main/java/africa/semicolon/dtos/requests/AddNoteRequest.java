package africa.semicolon.dtos.requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNoteRequest {
    private String authorEmail;
    private String title;
    private String content;
}
