package exceptions;

/**
 * Created by Alexandra Kolpakova on 11.10.2016.
 */
public class LoginException extends RuntimeException{

    public LoginException(){
        super("Login failed. Wrong credentials were used.");
    }
}
