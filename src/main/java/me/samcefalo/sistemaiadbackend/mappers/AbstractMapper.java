package me.samcefalo.sistemaiadbackend.mappers;

import org.modelmapper.ModelMapper;

public interface AbstractMapper<M, D> {

    ModelMapper configure(Class model, Class dto);

    M mapToModel(D dto, Class<? extends M> mapper);

    D mapToDTO(M model, Class<? extends D> mapper);

}
