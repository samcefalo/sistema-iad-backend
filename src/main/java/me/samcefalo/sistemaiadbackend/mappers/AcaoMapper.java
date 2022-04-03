package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.*;
import me.samcefalo.sistemaiadbackend.models.*;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class AcaoMapper extends JogoMapper {

    public AcaoMapper() {
        super();
        configure();
        configureDefault();
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

    private void configureDefault() {
        Condition<Acao, Acao> isPasse = ctx -> ctx.getSource() != null && ctx.getSource() instanceof Passe;
        Condition<Acao, Acao> isDesarme = ctx -> ctx.getSource() != null && ctx.getSource() instanceof Desarme;
        Condition<Acao, Acao> isDrible = ctx -> ctx.getSource() != null && ctx.getSource() instanceof Drible;
        Condition<Acao, Acao> isFinalizacao = ctx -> ctx.getSource() != null && ctx.getSource() instanceof Finalizacao;
        Condition<AcaoDTO, AcaoDTO> isPasseDTO = ctx -> ctx.getSource() != null && ctx.getSource() instanceof PasseDTO;
        Condition<AcaoDTO, AcaoDTO> isDesarmeDTO = ctx -> ctx.getSource() != null && ctx.getSource() instanceof DesarmeDTO;
        Condition<AcaoDTO, AcaoDTO> isDribleDTO = ctx -> ctx.getSource() != null && ctx.getSource() instanceof DribleDTO;
        Condition<AcaoDTO, AcaoDTO> isFinalizacaoDTO = ctx -> ctx.getSource() != null && ctx.getSource() instanceof FinalizacaoDTO;

        //seta valor default de AcaoDTO para Acao
        modelMapper.typeMap(AcaoDTO.class, Acao.class)
                .setCondition(isDesarme)
                .setConverter(converterWithDestinationSupplier(Desarme::new))
                .setCondition(isDrible)
                .setConverter(converterWithDestinationSupplier(Drible::new))
                .setCondition(isFinalizacao)
                .setConverter(converterWithDestinationSupplier(Finalizacao::new))
                .setCondition(isPasse)
                .setConverter(converterWithDestinationSupplier(Passe::new));

        //seta valor default de Acao para AcaoDTO
        modelMapper.typeMap(Acao.class, AcaoDTO.class)
                .setCondition(isDesarmeDTO)
                .setConverter(converterWithDestinationSupplier(DesarmeDTO::new))
                .setCondition(isDribleDTO)
                .setConverter(converterWithDestinationSupplier(DribleDTO::new))
                .setCondition(isFinalizacaoDTO)
                .setConverter(converterWithDestinationSupplier(FinalizacaoDTO::new))
                .setCondition(isPasseDTO)
                .setConverter(converterWithDestinationSupplier(PasseDTO::new));
    }

    private <S, D> Converter<S, D> converterWithDestinationSupplier(Supplier<? extends D> supplier) {
        return ctx -> ctx.getMappingEngine().map(ctx.create(ctx.getSource(), supplier.get()));
    }

    public Acao mapTo(AcaoDTO acaoDTO) {
        return modelMapper.map(acaoDTO, Acao.class);
    }

    public AcaoDTO mapTo(Acao acao) {
        return this.modelMapper.map(acao, AcaoDTO.class);
    }

}
