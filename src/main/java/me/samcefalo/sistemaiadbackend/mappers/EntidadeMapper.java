package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.EntidadeDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogadorDTO;
import me.samcefalo.sistemaiadbackend.dtos.TecnicoDTO;
import me.samcefalo.sistemaiadbackend.models.Entidade;
import me.samcefalo.sistemaiadbackend.models.Jogador;
import me.samcefalo.sistemaiadbackend.models.Tecnico;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class EntidadeMapper {

    private ModelMapper modelMapper;

    public EntidadeMapper() {
        modelMapper = new ModelMapper();
        configure();
    }

    private void configure() {
        //models
        modelMapper.typeMap(JogadorDTO.class, Entidade.class)
                .setConverter(converterWithDestinationSupplier(Jogador::new));
        modelMapper.typeMap(TecnicoDTO.class, Entidade.class)
                .setConverter(converterWithDestinationSupplier(Tecnico::new));

        //dtos
        modelMapper.typeMap(Jogador.class, EntidadeDTO.class)
                .setConverter(converterWithDestinationSupplier(JogadorDTO::new));
        modelMapper.typeMap(Tecnico.class, EntidadeDTO.class)
                .setConverter(converterWithDestinationSupplier(TecnicoDTO::new));
    }

    private <S, D> Converter<S, D> converterWithDestinationSupplier(Supplier<? extends D> supplier) {
        return ctx -> ctx.getMappingEngine().map(ctx.create(ctx.getSource(), supplier.get()));
    }

    public Entidade mapTo(EntidadeDTO entidadeDTO) {
        return modelMapper.map(entidadeDTO, Entidade.class);
    }

    public EntidadeDTO mapTo(Entidade entidade) {
        return modelMapper.map(entidade, EntidadeDTO.class);
    }


}
