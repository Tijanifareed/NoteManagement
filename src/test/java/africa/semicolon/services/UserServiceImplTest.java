package africa.semicolon.services;

import africa.semicolon.data.models.User;
import africa.semicolon.data.repositories.NoteRepository;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dtos.requests.*;
import africa.semicolon.dtos.responses.*;
import africa.semicolon.exceptions.EmailExistsException;
import africa.semicolon.exceptions.IncorrectPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository users;
    @Autowired
    private NoteRepository notes;

    @BeforeEach
    public void setUp() {
        notes.deleteAll();
        users.deleteAll();
    }

    @Test
    public void registerUserTest(){
        RegisterUserResponse response = createNewUser();
        assertThat(response).isNotNull();
        assertThat(userService.getAllUsers().size()).isEqualTo(1L);
    }

    private RegisterUserResponse createNewUser() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setFirstName("first name");
        request.setLastName("last name");
        request.setEmail("email@email.com");
        request.setPassword("password");
        return userService.registerUserWith(request);
    }

    @Test
    public void RegisterUserWithSameEmail_throwsExceptionTest(){
        createNewUser();
        RegisterUserRequest request = new RegisterUserRequest();
        request.setFirstName("first name");
        request.setLastName("last name");
        request.setEmail("email@email.com");
        request.setPassword("password");
        assertThrows(EmailExistsException.class, ()-> userService.registerUserWith(request));
    }


    @Test
    public void loginTest(){
        createNewUser();
        LoginResponse response = userLogin();
        assertThat(response.isLoggedIn()).isEqualTo(true);
    }

    private LoginResponse userLogin() {
        LoginRequest request = new LoginRequest();
        request.setEmail("email@email.com");
        request.setPassword("password");
        return userService.login(request);

    }

    @Test
    public void loginWithIncorrectPassword_throwsExceptionTest(){
        createNewUser();
        LoginRequest request = new LoginRequest();
        request.setEmail("email@email.com");
        request.setPassword("wrong password");
        assertThrows(IncorrectPasswordException.class, ()-> userService.login(request));
    }
    @Test
    public void createNoteTest_successfulTest(){
        createNewUser();
        LoginResponse response = userLogin();
        AddNoteRequest request = new AddNoteRequest();
        request.setTitle("I am a boy");
        request.setContent("new content");
        request.setAuthorEmail("email@email.com");
        AddNoteResponse response1 = userService.createNote(request);
        assertThat(response1.getNoteId()).isNotNull();
        User user = users.findByEmail(response.getEmail()).orElseThrow();
        assertThat(user.getNoteList().size()).isEqualTo(1);
    }

    @Test
    public void editContact(){
        createNewUser();
        LoginResponse response = userLogin();
        AddNoteRequest request = new AddNoteRequest();
        request.setTitle("I am a boy");
        request.setContent("new content");
        request.setAuthorEmail("email@email.com");
        AddNoteResponse response1 = userService.createNote(request);
        UpdateNoteRequest request1 = new UpdateNoteRequest();
        request1.setNewContent("cont");
        request1.setNewTitle("title");
        request1.setId(response1.getNoteId());
        UpdateNoteResponse responses = userService.updateNoteWith(request1);
        assertThat(responses.getUpdatedTitle()).contains("title");

    }

    @Test
    public void testTHatUserCanDeleteContact() {
        createNewUser();
        LoginResponse response = userLogin();
        AddNoteRequest request = new AddNoteRequest();
        request.setTitle("I am a boy");
        request.setContent("new content");
        request.setAuthorEmail("email@email.com");
        AddNoteResponse response1 = userService.createNote(request);
        DeleteNoteRequest request1 = new DeleteNoteRequest();
        request1.setNoteId(response1.getNoteId());
        DeleteNoteResponse response2 = userService.deleteNote(request1);
    }

    @Test
    public void logoutTest(){
        createNewUser();
        userLogin();
        LogoutRequest request = new LogoutRequest();
        request.setEmail("email@email.com");
        LogoutResponse response = userService.logout(request);
        assertThat(response.getMessage()).contains("logged out");
    }

}