package me.samcefalo.sistemaiadbackend.resources.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationError {

    private long timeStamp;
    private int status;
    private String path;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(long timeStamp, int status, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.path = path;
    }

    public void addError(String nome, String message) {
        errors.add(new FieldMessage(nome, message));
    }

}
