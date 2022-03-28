package me.samcefalo.sistemaiadbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonTypeName("Tecnico")
public class TecnicoDTO extends EntidadeDTO {

    private int equipeId;

}
