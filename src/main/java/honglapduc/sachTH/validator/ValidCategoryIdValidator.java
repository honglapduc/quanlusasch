package honglapduc.sachTH.validator;

import honglapduc.sachTH.entity.Category;
import honglapduc.sachTH.validator.annotation.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context){
        return category !=null && category.getId() !=null;
    }
}
