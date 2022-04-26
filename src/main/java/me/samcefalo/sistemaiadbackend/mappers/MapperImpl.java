package me.samcefalo.sistemaiadbackend.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.reflections.Reflections;

import java.util.Set;

public abstract class MapperImpl<M, D> {

    private ModelMapper modelMapper;

    public MapperImpl() {
        modelMapper = new ModelMapper();
    }

    public ModelMapper configure(Class model, Class dto) {
        Reflections reflections = new Reflections("me.samcefalo.sistemaiadbackend");
        Set<Class> modelSubTypes = reflections.getSubTypesOf(model);
        Set<Class> dtoSubTypes = reflections.getSubTypesOf(dto);

        for (Class tClass : modelSubTypes) {
            modelMapper.typeMap(dto, model)
                    .setConverter(converterWithDestinationSupplier(tClass));
            modelMapper.typeMap(tClass, dto)
                    .setConverter(converterWithDestinationSupplier(findClassInSet(dtoSubTypes, tClass.getSimpleName() + "DTO")));
        }

        for (Class tClass : dtoSubTypes) {
            modelMapper.typeMap(model, dto)
                    .setConverter(converterWithDestinationSupplier(tClass));
            modelMapper.typeMap(tClass, model)
                    .setConverter(converterWithDestinationSupplier(findClassInSet(modelSubTypes, tClass.getSimpleName().replaceAll("DTO", ""))));
        }
        return modelMapper;
    }

    private Class findClassInSet(Set<Class> set, String nome) {
        return set.stream()
                .filter(aClass -> aClass.getSimpleName().equalsIgnoreCase(nome))
                .findAny().orElse(null);
    }

    private <S, T> Converter<S, T> converterWithDestinationSupplier(Class<? extends T> supplier) {
        return ctx -> ctx.getMappingEngine().map(ctx.create(ctx.getSource(), supplier));
    }

    public M mapToModel(D dto, Class<? extends M> mapper) {
        return modelMapper.map(dto, mapper);
    }

    public D mapToDTO(M model, Class<? extends D> mapper) {
        return modelMapper.map(model, mapper);
    }
}
