package africa.semicolon.utils;


import africa.semicolon.data.models.Note;
import africa.semicolon.data.models.User;
import africa.semicolon.dtos.requests.AddNoteRequest;
import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.responses.AddNoteResponse;
import africa.semicolon.dtos.responses.LoginResponse;
import africa.semicolon.dtos.responses.RegisterUserResponse;
import africa.semicolon.dtos.responses.UpdateNoteResponse;

import java.time.LocalDateTime;

public class MapUtils {
    public static void map(AddNoteRequest request, Note note){
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setDateCreated(LocalDateTime.now());
    }
    public static AddNoteResponse map(Note note) {
        AddNoteResponse response = new AddNoteResponse();
        response.setNoteId(note.getId());
        response.setNoteTitle(note.getTitle());
        response.setNoteContent(note.getContent());
        response.setDateCreated(note.getDateCreated());
        return response;
    }
    public static UpdateNoteResponse mapNoteUpdateResponse(Note note){
        UpdateNoteResponse response = new UpdateNoteResponse();
        response.setNoteId(note.getId());
        response.setUpdatedTitle(note.getTitle());
        response.setUpdatedContent(note.getContent());
        response.setDateUpdated(note.getDateModified());
        return response;
    }
    public static void map(RegisterUserRequest request, User user){
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
    }
    public static RegisterUserResponse map(User user){
        RegisterUserResponse response = new RegisterUserResponse();
        response.setEmail(user.getEmail());
        response.setMessage(user.getEmail() + " registered successfully");
        return response;
    }
    public static LoginResponse mapUserLogin(User user){
        LoginResponse response = new LoginResponse();
        response.setEmail(user.getEmail());
        response.setMessage(user.getEmail() + " logged in successfully");
        response.setLoggedIn(user.isLoggedIn());
        return response;
    }
}

