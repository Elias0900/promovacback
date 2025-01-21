

@Component
public class ObjectValidator<T> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private final Validator validator = factory.getValidator();


    public void validate(T objectToValidate){
        Set<ConstraintViolation<T>> violation = validator.validate(objectToValidate);
        if (!violation.isEmpty()){
            Set<String> errorMessages = violation.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjectValidationException(errorMessages, objectToValidate.getClass().getName());
        }
    }

}