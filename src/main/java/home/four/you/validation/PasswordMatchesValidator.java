package home.four.you.validation;

import home.four.you.model.dto.UserDto;
import home.four.you.validation.annotation.PasswordMatches;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements
        ConstraintValidator<PasswordMatches, Object> {
    
   @Override
   public void initialize(PasswordMatches constraintAnnotation) {       
   }
   @Override
   public boolean isValid(Object obj, ConstraintValidatorContext context){
       UserDto user = (UserDto) obj;
       return user.getPassword().equals(user.getMatchingPassword());    
   }   
}
