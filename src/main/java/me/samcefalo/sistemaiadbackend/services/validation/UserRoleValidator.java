package me.samcefalo.sistemaiadbackend.services.validation;

import lombok.NoArgsConstructor;
import me.samcefalo.sistemaiadbackend.models.enums.UserRole;
import me.samcefalo.sistemaiadbackend.resources.exceptions.objects.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.UserSecurityService;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.UserRoleId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
public class UserRoleValidator implements ConstraintValidator<UserRoleId, Set<Integer>> {

    @Autowired
    private UserSecurityService userSecurityService;

    @Override
    public void initialize(UserRoleId ann) {
    }

    @Override
    public boolean isValid(Set<Integer> userRoles, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (userRoles.contains(UserRole.ADMIN.getId())
                && !userSecurityService.isAdmin()) {
            list.add(new FieldMessage("userRoles", "Sem permissão para adicionar cargo ADMIN."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
