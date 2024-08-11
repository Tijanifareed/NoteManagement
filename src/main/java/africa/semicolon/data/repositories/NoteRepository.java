package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface NoteRepository extends MongoRepository<Note, String> {
    boolean existsByTitle(String title);

    Note findByTitle(String noteTitle);

}
