package pl.playarena.api.impl.exception;

import org.springframework.social.OperationNotPermittedException;

public class MessageException extends OperationNotPermittedException{
    public MessageException(String message){
        super(message);
    }
}
