package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.domain.Desarme;
import me.samcefalo.sistemaiadbackend.domain.Drible;
import me.samcefalo.sistemaiadbackend.domain.Finalizacao;
import me.samcefalo.sistemaiadbackend.domain.Passe;
import me.samcefalo.sistemaiadbackend.domain.dto.DesarmeDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.DribleDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.FinalizacaoDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.PasseDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AcaoMappers {

    /*
    @SubclassMappings({
            @SubclassMapping(source = PasseDTO.class, target = Passe.class),
            @SubclassMapping(source = DesarmeDTO.class, target = Desarme.class),
            @SubclassMapping(source = DribleDTO.class, target = Drible.class),
            @SubclassMapping(source = FinalizacaoDTO.class, target = Finalizacao.class)
    })
    Acao map(AcaoDTO acaoDTO);*/

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

}
