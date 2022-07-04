package me.samcefalo.sistemaiadbackend.specifications;

import lombok.Data;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.models.Atleta;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.specifications.criterias.AtletaCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class AtletaSpecification implements Specification<Atleta> {

    private AtletaCriteria criteria;

    public AtletaSpecification(AtletaCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Atleta> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicateList = new ArrayList<>();

        if (criteria.getAcaoId() != null) {
            Join<Jogo, Acao> join = root.join("acoes");
            predicateList.add(builder.equal(join.get("id"), criteria.getAcaoId().intValue()));
        }

        if (criteria.getJogoId() != null) {
            Join<Jogo, Jogo> join = root.join("jogos");
            predicateList.add(builder.equal(join.get("id"), criteria.getJogoId().intValue()));
        }

        if (criteria.getEquipeId() != null) {
            predicateList.add(builder.equal(root.get("equipe"), criteria.getEquipeId()));
        }

        if (criteria.getUserId() != null) {
            predicateList.add(builder.equal(root.get("user"), criteria.getUserId()));
        }

        if (!StringUtils.isEmpty(criteria.getNome()) && criteria.getNome() != null) {
            predicateList.add(builder.equal(root.get("nome"), criteria.getNome()));
        }

        if (criteria.getNumero() != null) {
            predicateList.add(builder.equal(root.get("numero"), criteria.getNumero()));
        }

        if (criteria.getSexo() > 0 && criteria.getSexo() != null) {
            predicateList.add(builder.equal(root.get("sexo"), criteria.getSexo()));
        }

        return builder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }
}
