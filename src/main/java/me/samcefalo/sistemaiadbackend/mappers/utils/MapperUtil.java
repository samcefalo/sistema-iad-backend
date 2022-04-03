package me.samcefalo.sistemaiadbackend.mappers.utils;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class MapperUtil {

    protected ModelMapper modelMapper;

    public MapperUtil() {
        this.modelMapper = new ModelMapper();
    }

    public <S, D> D mapTo(S source, Class<D> destClass) {
        return this.modelMapper.map(source, destClass);
    }

    public <S, D> List<D> toList(List<S> source, Type destClass) {
        return this.modelMapper.map(source, destClass);
    }

    public <S, D> Page<D> toPage(Page<S> source, Type dest) {
        List<D> list = toList(source.getContent(), dest);
        return new PageImpl<>(list, source.getPageable(), source.getTotalElements());
    }
}
