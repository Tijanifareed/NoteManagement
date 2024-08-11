package africa.semicolon.dtos.responses;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AddNoteResponse {
    private String noteId;
    private String noteTitle;
    private String noteContent;
    private LocalDateTime dateCreated;
}
