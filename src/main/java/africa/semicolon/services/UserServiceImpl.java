package africa.semicolon.services;

import africa.semicolon.data.models.Note;
import africa.semicolon.data.models.User;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dtos.requests.*;
import africa.semicolon.dtos.responses.*;
import africa.semicolon.exceptions.EmailExistsException;
import africa.semicolon.exceptions.IncorrectPasswordException;
import africa.semicolon.exceptions.UserLoginException;
import africa.semicolon.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static africa.semicolon.utils.MapUtils.map;
import static africa.semicolon.utils.MapUtils.mapUserLogin;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private NoteService noteService;

    @Override
    public RegisterUserResponse registerUserWith(RegisterUserRequest request) {
        validateExistingEmail(request.getEmail());
        User user = new User();
        map(request, user);
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return map(user);
    }

    private void validateExistingEmail(String email) {
        boolean existsByEmail = userRepository.existsByEmail(email);
        if (existsByEmail)throw new EmailExistsException(email+" already exists");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = findByEmail(request.getEmail());
        validatePassword(user, request.getPassword());
        user.setLoggedIn(true);
        userRepository.save(user);
        return mapUserLogin(user);
    }

    private void validatePassword(User user, String password) {
        if (!user.getPassword().equals(password)) {
            throw new IncorrectPasswordException("invalid details");
        }
    }

    @Override
    public AddNoteResponse createNote(AddNoteRequest request) {
        User user = findByEmail(request.getAuthorEmail());
        validateUserLogin(user);
        AddNoteResponse response = noteService.createNoteWith(request);
        Note note = noteService.findNotesByTitle(response.getNoteTitle());
        List<Note> notes = user.getNoteList();
        notes.add(note);
        user.setNoteList(notes);
        userRepository.save(user);
        return response;
    }

    @Override
    public LogoutResponse logout(LogoutRequest request) {
        User user = findByEmail(request.getEmail());
        user.setLoggedIn(false);
        userRepository.save(user);
        LogoutResponse response = new LogoutResponse();
        response.setMessage("user logged out successfully");
        response.setLoggedIn(user.isLoggedIn());
        return response;
    }



    @Override
    public UpdateNoteResponse updateNoteWith(UpdateNoteRequest request) {
        return noteService.updateNoteWith(request);
    }

    @Override
    public DeleteNoteResponse deleteNote(DeleteNoteRequest request1) {
        return noteService.deleteNote(request1);
    }

    @Override
    public Note findNoteByTitle(String title) {
     return noteService.findNotesByTitle(title);
    }

    private void validateUserLogin(User user) {
        if(!user.isLoggedIn())throw new UserLoginException("you need to log in");
    }



    private User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("user not found"));
    }
}
