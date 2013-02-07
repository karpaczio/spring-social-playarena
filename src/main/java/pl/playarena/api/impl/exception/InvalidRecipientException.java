package pl.playarena.api.impl.exception;

import org.springframework.social.OperationNotPermittedException;

public class InvalidRecipientException extends OperationNotPermittedException{
    public InvalidRecipientException(String message){
        super(message);
    }
}
