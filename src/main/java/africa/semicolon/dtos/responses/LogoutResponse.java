package africa.semicolon.dtos.responses;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutResponse {
    private String message;
    private boolean isLoggedIn;
}
