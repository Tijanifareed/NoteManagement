package africa.semicolon.services;

import africa.semicolon.data.models.Note;
import africa.semicolon.dtos.requests.AddNoteRequest;
import africa.semicolon.dtos.requests.DeleteNoteRequest;
import africa.semicolon.dtos.requests.UpdateNoteRequest;
import africa.semicolon.dtos.responses.AddNoteResponse;
import africa.semicolon.dtos.responses.DeleteNoteResponse;
import africa.semicolon.dtos.responses.UpdateNoteResponse;

import java.util.List;

public interface NoteService {
    AddNoteResponse createNoteWith(AddNoteRequest request);
    Long getTotalNotes();
    UpdateNoteResponse updateNoteWith(UpdateNoteRequest request);
    DeleteNoteResponse deleteNote(DeleteNoteRequest request1);
    List<Note> getAllNotes();
    Note findNotesByTitle(String noteTitle);
}