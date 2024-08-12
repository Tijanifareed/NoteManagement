package africa.semicolon.controllers;


import africa.semicolon.dtos.requests.*;
import africa.semicolon.dtos.responses.*;
import africa.semicolon.exceptions.MyNotesException;
import africa.semicolon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/user")

public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterUserRequest request){
        try{
            return new ResponseEntity<>(new ApiResponse(true, userService.registerUserWith(request)), CREATED);
        }
        catch (MyNotesException exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest request){
        try{
            LoginResponse response = userService.login(request);
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }
        catch (MyNotesException exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> userNoteContact(@RequestBody AddNoteRequest request){
        try{
            AddNoteResponse response = userService.createNote(request);
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }
        catch (MyNotesException exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteNoteRequest request){
        try{
            DeleteNoteResponse response = userService.deleteNote(request);
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }
        catch (MyNotesException exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateNoteRequest request){
        try{
            UpdateNoteResponse response = userService.updateNoteWith(request);
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }
        catch (MyNotesException exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody LogoutRequest request){
        try{
            LogoutResponse response = userService.logout(request);
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }
        catch (MyNotesException exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), BAD_REQUEST);
        }
    }





}
