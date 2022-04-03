package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.*;
import me.samcefalo.sistemaiadbackend.models.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class AcaoMapper {

    private ModelMapper modelMapper;

    public AcaoMapper() {
        modelMapper = new ModelMapper();
        configure();
        createPropertyMapper();
    }

    private void configure() {
        //models
        modelMapper.typeMap(DesarmeDTO.class, Acao.class)
                .setConverter(converterWithDestinationSupplier(Desarme::new));
        modelMapper.typeMap(PasseDTO.class, Acao.class)
                .setConverter(converterWithDestinationSupplier(Passe::new));
        modelMapper.typeMap(DribleDTO.class, Acao.class)
                .setConverter(converterWithDestinationSupplier(Drible::new));
        modelMapper.typeMap(FinalizacaoDTO.class, Acao.class)
                .setConverter(converterWithDestinationSupplier(Finalizacao::new));

        //dtos
        modelMapper.typeMap(Desarme.class, AcaoDTO.class)
                .setConverter(converterWithDestinationSupplier(DesarmeDTO::new));
        modelMapper.typeMap(Passe.class, AcaoDTO.class)
                .setConverter(converterWithDestinationSupplier(PasseDTO::new));
        modelMapper.typeMap(Drible.class, AcaoDTO.class)
                .setConverter(converterWithDestinationSupplier(DribleDTO::new));
        modelMapper.typeMap(Finalizacao.class, AcaoDTO.class)
                .setConverter(converterWithDestinationSupplier(FinalizacaoDTO::new));
    }

    private <S, D> Converter<S, D> converterWithDestinationSupplier(Supplier<? extends D> supplier) {
        return ctx -> ctx.getMappingEngine().map(ctx.create(ctx.getSource(), supplier.get()));
    }

    private void createPropertyMapper() {
        TypeMap<Acao, AcaoDTO> propertyMapper = modelMapper.createTypeMap(Acao.class, AcaoDTO.class);

        propertyMapper.addMappings(
                mapper -> mapper.map(src -> src.getEquipe().getId(), AcaoDTO::setEquipeId)
        );

        propertyMapper.addMappings(
                mapper -> mapper.map(src -> src.getJogador().getId(), AcaoDTO::setJogadorId)
        );

    }

    public Acao mapTo(AcaoDTO acaoDTO) {
        return modelMapper.map(acaoDTO, Acao.class);
    }

    public AcaoDTO mapTo(Acao acao) {
        return this.modelMapper.map(acao, AcaoDTO.class);
    }

}
