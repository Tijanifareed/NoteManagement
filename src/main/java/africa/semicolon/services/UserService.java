package africa.semicolon.services;

import africa.semicolon.data.models.User;
import africa.semicolon.dtos.requests.*;
import africa.semicolon.dtos.responses.*;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerUserWith(RegisterUserRequest request);

    List<User> getAllUsers();

    LoginResponse login(LoginRequest request);

    AddNoteResponse createNote(AddNoteRequest request);

    LogoutResponse logout(LogoutRequest request);

    UpdateNoteResponse updateNoteWith(UpdateNoteRequest request);

    DeleteNoteResponse deleteNote(DeleteNoteRequest request1);
}
