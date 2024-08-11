package africa.semicolon.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @Id
    private String id;
    private String content;
    private String title;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
}
