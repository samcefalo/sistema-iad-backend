package me.samcefalo.sistemaiadbackend.resources.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(int status, String message, long timeStamp) {
        super(status, message, timeStamp);
    }

    public void addError(String nome, String message) {
        errors.add(new FieldMessage(nome, message));
    }

}
