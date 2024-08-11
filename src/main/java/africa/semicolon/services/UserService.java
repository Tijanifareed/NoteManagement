package africa.semicolon.services;

import africa.semicolon.data.models.User;
import africa.semicolon.dtos.requests.AddNoteRequest;
import africa.semicolon.dtos.requests.LoginRequest;
import africa.semicolon.dtos.requests.LogoutRequest;
import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.responses.AddNoteResponse;
import africa.semicolon.dtos.responses.LoginResponse;
import africa.semicolon.dtos.responses.LogoutResponse;
import africa.semicolon.dtos.responses.RegisterUserResponse;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerUserWith(RegisterUserRequest request);

    List<User> getAllUsers();

    LoginResponse login(LoginRequest request);

    AddNoteResponse createNote(AddNoteRequest request);

    LogoutResponse logout(LogoutRequest request);
}
