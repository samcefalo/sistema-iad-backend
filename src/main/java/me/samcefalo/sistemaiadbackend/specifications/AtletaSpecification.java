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

        if (criteria.getAcao() != null) {
            Join<Jogo, Acao> join = root.join("acoes");
            predicateList.add(builder.equal(join.get("id"), criteria.getAcao().intValue()));
        }

        if (criteria.getJogo() != null) {
            Join<Jogo, Jogo> join = root.join("jogos");
            predicateList.add(builder.equal(join.get("id"), criteria.getJogo().intValue()));
        }

        if (criteria.getEquipe() != null) {
            predicateList.add(builder.equal(root.get("equipe"), criteria.getEquipe()));
        }

        if (criteria.getUser() != null) {
            predicateList.add(builder.equal(root.get("user"), criteria.getUser()));
        }

        if (!StringUtils.isEmpty(criteria.getNome()) && criteria.getNome() != null) {
            predicateList.add(builder.equal(root.get("nome"), criteria.getNome()));
        }

        if (criteria.getNumero() != null) {
            predicateList.add(builder.equal(root.get("numero"), criteria.getNumero()));
        }

        if (criteria.getSexo() != null && criteria.getSexo() > 0) {
            predicateList.add(builder.equal(root.get("sexo"), criteria.getSexo()));
        }

        return builder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }
}
