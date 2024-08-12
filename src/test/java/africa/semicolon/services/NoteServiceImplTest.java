package africa.semicolon.services;

import africa.semicolon.data.models.Note;
import africa.semicolon.data.repositories.NoteRepository;
import africa.semicolon.dtos.requests.AddNoteRequest;
import africa.semicolon.dtos.requests.DeleteNoteRequest;
import africa.semicolon.dtos.requests.UpdateNoteRequest;
import africa.semicolon.dtos.responses.AddNoteResponse;
import africa.semicolon.dtos.responses.DeleteNoteResponse;
import africa.semicolon.dtos.responses.UpdateNoteResponse;
import africa.semicolon.exceptions.TitleExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NoteServiceImplTest {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private NoteService noteService;

    @BeforeEach
    public void setUp(){
        noteRepository.deleteAll();
    }

    @Test
    public void addNoteTest(){
        AddNoteResponse response = createNewNote();
        assertThat(response).isNotNull();
        assertThat(response.getNoteId()).isNotNull();
        assertThat(noteService.getTotalNotes()).isEqualTo(1L);
    }

    private AddNoteResponse createNewNote() {
        AddNoteRequest request = new AddNoteRequest();
        request.setTitle("title");
        request.setContent("content");
        return noteService.createNoteWith(request);
    }
    @Test
    public void addNoteWithSameTitle_throwsExceptionTest(){
        AddNoteResponse response = createNewNote();
        AddNoteRequest request = new AddNoteRequest();
        request.setTitle("title");
        request.setContent("new content");
        assertThrows(TitleExistsException.class, ()->noteService.createNoteWith(request));
    }

    @Test
    public void editNoteTest(){
        AddNoteResponse response = createNewNote();
        UpdateNoteRequest request = new UpdateNoteRequest();
        request.setId(response.getNoteId());
        request.setNewTitle("updated title");
        request.setNewContent("updated content");
        UpdateNoteResponse response1 = noteService.updateNoteWith(request);
        assertThat(response1.getUpdatedTitle()).contains("updated");
        assertThat(response.getNoteId()).isEqualTo(request.getId());
    }

    @Test
    public void deleteNoteTest(){
        AddNoteResponse response = createNewNote();
        String noteId = response.getNoteId();
        DeleteNoteRequest request = new DeleteNoteRequest();
        request.setNoteId(noteId);
        DeleteNoteResponse response1 = noteService.deleteNote(request);
        assertThat(response1.getMessage()).contains("deleted");
    }

    @Test
    public void getAllNotesTest(){
        AddNoteResponse response = createNewNote();
        List<Note> notes = noteService.getAllNotes();
        assertThat(notes.size()).isEqualTo(1L);
    }
}