package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogoFutebolDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogoFutsalDTO;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.models.JogoFutebol;
import me.samcefalo.sistemaiadbackend.models.JogoFutsal;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class JogoMapper {

    private ModelMapper modelMapper;

    public JogoMapper() {
        modelMapper = new ModelMapper();
        configure();
    }

    private void configure() {
        //models
        modelMapper.typeMap(JogoFutsalDTO.class, Jogo.class)
                .setConverter(converterWithDestinationSupplier(JogoFutsal::new));
        modelMapper.typeMap(JogoFutebolDTO.class, Jogo.class)
                .setConverter(converterWithDestinationSupplier(JogoFutebol::new));

        //dtos
        modelMapper.typeMap(JogoFutsal.class, JogoDTO.class)
                .setConverter(converterWithDestinationSupplier(JogoFutsalDTO::new));
        modelMapper.typeMap(JogoFutebol.class, JogoDTO.class)
                .setConverter(converterWithDestinationSupplier(JogoFutebolDTO::new));
    }

    private <S, D> Converter<S, D> converterWithDestinationSupplier(Supplier<? extends D> supplier) {
        return ctx -> ctx.getMappingEngine().map(ctx.create(ctx.getSource(), supplier.get()));
    }

    public Jogo mapTo(JogoDTO jogoDTO) {
        return modelMapper.map(jogoDTO, Jogo.class);
    }

    public JogoDTO mapTo(Jogo jogo) {
        return modelMapper.map(jogo, JogoDTO.class);
    }

}
