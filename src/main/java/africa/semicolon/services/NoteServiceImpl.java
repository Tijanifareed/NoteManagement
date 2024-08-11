package africa.semicolon.services;

import africa.semicolon.data.models.Note;
import africa.semicolon.data.repositories.NoteRepository;
import africa.semicolon.dtos.requests.AddNoteRequest;
import africa.semicolon.dtos.requests.UpdateNoteRequest;
import africa.semicolon.dtos.responses.AddNoteResponse;
import africa.semicolon.dtos.responses.DeleteNoteResponse;
import africa.semicolon.dtos.responses.UpdateNoteResponse;
import africa.semicolon.exceptions.NoteNotFoundException;
import africa.semicolon.exceptions.TitleExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static africa.semicolon.utils.MapUtils.map;
import static africa.semicolon.utils.MapUtils.mapNoteUpdateResponse;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService{
    private final NoteRepository noteRepository;

    @Override
    public AddNoteResponse createNoteWith(AddNoteRequest request) {
        validateTitle(request.getTitle());
        Note note = new Note();
        map(request,note);
        note = noteRepository.save(note);
        return map(note);
    }

    private void validateTitle(String title) {
        boolean existsByTitle = noteRepository.existsByTitle(title);
        if(existsByTitle) throw new TitleExistsException(title+ " exists");
    }

    @Override
    public Long getTotalNotes() {
        return noteRepository.count();
    }

    @Override
    public UpdateNoteResponse updateNoteWith(UpdateNoteRequest request) {
        Note note = findById(request.getId());
        note.setTitle(request.getNewTitle());
        note.setContent(request.getNewContent());
        note.setDateModified(LocalDateTime.now());
        note = noteRepository.save(note);
        return mapNoteUpdateResponse(note);
    }

    @Override
    public DeleteNoteResponse deleteNote(String noteId) {
        Note note = findById(noteId);
        noteRepository.delete(note);
        DeleteNoteResponse response = new DeleteNoteResponse();
        response.setMessage("note deleted successfully");
        return response;
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note findNotesByTitle(String noteTitle) {
        return noteRepository.findByTitle(noteTitle);
    }

    private Note findById(String id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("note not found"));
    }


}
