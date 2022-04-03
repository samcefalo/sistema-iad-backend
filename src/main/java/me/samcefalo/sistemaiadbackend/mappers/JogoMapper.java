package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogoFutebolDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogoFutsalDTO;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.models.JogoFutebol;
import me.samcefalo.sistemaiadbackend.models.JogoFutsal;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class JogoMapper {

    public ModelMapper modelMapper;

    public JogoMapper() {
        modelMapper = new ModelMapper();
        configure();
        configureDefault();
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

    private void configureDefault() {
        Condition<Jogo, Jogo> isJogoFutebol = ctx -> ctx.getSource() != null && ctx.getSource() instanceof JogoFutebol;
        Condition<Jogo, Jogo> isJogoFutsal = ctx -> ctx.getSource() != null && ctx.getSource() instanceof JogoFutsal;
        Condition<JogoDTO, JogoDTO> isJogoFutebolDTO = ctx -> ctx.getSource() != null && ctx.getSource() instanceof JogoFutebolDTO;
        Condition<JogoDTO, JogoDTO> isJogoFutsalDTO = ctx -> ctx.getSource() != null && ctx.getSource() instanceof JogoFutsalDTO;

        //seta valor default de JogoDTO para Jogo
        modelMapper.typeMap(JogoDTO.class, Jogo.class)
                .setCondition(isJogoFutsal)
                .setConverter(converterWithDestinationSupplier(JogoFutsal::new))
                .setCondition(isJogoFutebol)
                .setConverter(converterWithDestinationSupplier(JogoFutebol::new));
        //seta valor default de Jogo para JogoDTO
        modelMapper.typeMap(Jogo.class, JogoDTO.class)
                .setCondition(isJogoFutebolDTO)
                .setConverter(converterWithDestinationSupplier(JogoFutebolDTO::new))
                .setCondition(isJogoFutsalDTO)
                .setConverter(converterWithDestinationSupplier(JogoFutsalDTO::new));
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
