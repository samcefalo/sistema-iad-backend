package me.samcefalo.sistemaiadbackend.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {

    private long timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;


}
