package africa.semicolon.controllers;


import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.responses.ApiResponse;
import africa.semicolon.dtos.responses.RegisterUserResponse;
import africa.semicolon.exceptions.MyNotesException;
import africa.semicolon.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterUserRequest request){
        try{
            RegisterUserResponse response = userService.registerUserWith(request);
            return new ResponseEntity<>(new ApiResponse(true,response), CREATED);
        }
        catch (MyNotesException exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), BAD_REQUEST);
        }
    }

}
