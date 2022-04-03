package me.samcefalo.sistemaiadbackend.resources.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(long timeStamp, int status, String error, String message, String path) {
        super(timeStamp, status, error, message, path);
    }

    public void addError(String nome, String message) {
        errors.add(new FieldMessage(nome, message));
    }

}
