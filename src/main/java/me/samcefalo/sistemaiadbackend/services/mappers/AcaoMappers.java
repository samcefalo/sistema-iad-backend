package me.samcefalo.sistemaiadbackend.services.mappers;

import me.samcefalo.sistemaiadbackend.domain.*;
import me.samcefalo.sistemaiadbackend.domain.dto.*;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {JogoMappers.class})
public interface AcaoMappers {

    //Acao
    @Mapping(target = "equipe.id", source = "equipeId")
    @Mapping(target = "jogador.id", source = "jogadorId")
    @Mapping(target = "jogo.id", source = "jogoId")
    Drible dribleDtoToDrible(DribleDTO dribleDTO);

    @InheritInverseConfiguration(name = "dribleDtoToDrible")
    DribleDTO dribleToDribleDto(Drible drible);

    @Mapping(target = "equipe.id", source = "equipeId")
    @Mapping(target = "jogador.id", source = "jogadorId")
    @Mapping(target = "jogo.id", source = "jogoId")
    Desarme desarmeDtoToDesarme(DesarmeDTO desarmeDTO);

    @InheritInverseConfiguration(name = "desarmeDtoToDesarme")
    DesarmeDTO desarmeToDesarmeDto(Desarme desarme);

    @Mapping(target = "equipe.id", source = "equipeId")
    @Mapping(target = "jogador.id", source = "jogadorId")
    @Mapping(target = "jogo.id", source = "jogoId")
    Finalizacao finalizacaoDtoToFinalizacao(FinalizacaoDTO finalizacaoDTO);

    @InheritInverseConfiguration(name = "finalizacaoDtoToFinalizacao")
    FinalizacaoDTO finalizacaoToFinalizacaoDto(Finalizacao finalizacao);

    @Mapping(target = "equipe.id", source = "equipeId")
    @Mapping(target = "jogador.id", source = "jogadorId")
    @Mapping(target = "jogo.id", source = "jogoId")
    Passe passeDtoToPasse(PasseDTO passeDTO);

    @InheritInverseConfiguration(name = "passeDtoToPasse")
    PasseDTO passeToPasseDto(Passe passe);

    @ObjectFactory
    default AcaoDTO acaoToAcaoDto(Acao acao) {
        if (acao instanceof Finalizacao) {
            return finalizacaoToFinalizacaoDto((Finalizacao) acao);
        } else if (acao instanceof Desarme) {
            return desarmeToDesarmeDto((Desarme) acao);
        } else if (acao instanceof Drible) {
            return dribleToDribleDto((Drible) acao);
        } else {
            return passeToPasseDto((Passe) acao);
        }
    }

    @ObjectFactory
    default Acao acaoDtoToAcao(AcaoDTO acaoDTO) {
        if (acaoDTO instanceof FinalizacaoDTO) {
            return finalizacaoDtoToFinalizacao((FinalizacaoDTO) acaoDTO);
        } else if (acaoDTO instanceof DesarmeDTO) {
            return desarmeDtoToDesarme((DesarmeDTO) acaoDTO);
        } else if (acaoDTO instanceof DribleDTO) {
            return dribleDtoToDrible((DribleDTO) acaoDTO);
        } else {
            return passeDtoToPasse((PasseDTO) acaoDTO);
        }
    }

    default List<AcaoDTO> acoesToAcoesDto(List<Acao> acoes) {
        List<AcaoDTO> acoesDTOS = new ArrayList<>();
        acoesDTOS.addAll(acoes.stream().map(acao -> acaoToAcaoDto(acao))
                .collect(Collectors.toList()));
        return acoesDTOS;
    }


}
