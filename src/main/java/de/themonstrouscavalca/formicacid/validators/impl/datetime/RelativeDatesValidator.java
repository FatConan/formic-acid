package de.themonstrouscavalca.formicacid.validators.impl.datetime;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.datetime.RelativeDateTimeComparator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RelativeDatesValidator implements IValidate<LocalDate>{
    private final Map<RelativeDateTimeComparator, String> DEFAULT_ERROR_MESSAGES = new HashMap<>();
    {
        DEFAULT_ERROR_MESSAGES.put(RelativeDateTimeComparator.EQUALS, "The provided dates are not equivalent");
        DEFAULT_ERROR_MESSAGES.put(RelativeDateTimeComparator.NOT_EQUALS, "The provided dates may not be the same");
        DEFAULT_ERROR_MESSAGES.put(RelativeDateTimeComparator.GREATER_THAN, "This must be later than the previous date");
        DEFAULT_ERROR_MESSAGES.put(RelativeDateTimeComparator.GREATER_THAN_OR_EQUAL, "This must be later than or equal to the previous date");
        DEFAULT_ERROR_MESSAGES.put(RelativeDateTimeComparator.LESS_THAN, "This must be earlier than the previous date");
        DEFAULT_ERROR_MESSAGES.put(RelativeDateTimeComparator.LESS_THAN_OR_EQUAL, "This must be earlier than or equal to the previous date");
    }
    private final Optional<Map<RelativeDateTimeComparator, String>> overriddenErrorMessages;
    private final Optional<LocalDate> date;
    private final RelativeDateTimeComparator comparator;

    public RelativeDatesValidator(Optional<LocalDate> date, RelativeDateTimeComparator comparator){
        this(date, comparator, null);
    }

    public RelativeDatesValidator(Optional<LocalDate> date, RelativeDateTimeComparator comparator, Map<RelativeDateTimeComparator, String> overriddenErrorMessages){
        this.overriddenErrorMessages = Optional.ofNullable(overriddenErrorMessages);
        this.date = date;
        this.comparator = comparator;
    }

    private String getError(RelativeDateTimeComparator key){
        if(overriddenErrorMessages.isPresent() && overriddenErrorMessages.get().containsKey(key)){
            return overriddenErrorMessages.get().get(key);
        }

        return DEFAULT_ERROR_MESSAGES.get(key);
    }

    @Override
    public IntermediateValidateOptional<LocalDate> getValidatedValue(Optional<LocalDate> value){
        IntermediateValidateOptional<LocalDate> inter = new IntermediateValidateOptional<>(value);
        if(value.isPresent()){
            if(date.isPresent()){
                switch(comparator){
                    case EQUALS:
                        if(!value.get().equals(date.get())){
                            inter.setValid(false);
                            inter.addError(this.getError(RelativeDateTimeComparator.EQUALS));
                        }
                        break;
                    case GREATER_THAN:
                        if(!value.get().isAfter(date.get())){
                            inter.setValid(false);
                            inter.addError(this.getError(RelativeDateTimeComparator.GREATER_THAN));
                        }
                        break;
                    case LESS_THAN:
                        if(!value.get().isBefore(date.get())){
                            inter.setValid(false);
                            inter.addError(this.getError(RelativeDateTimeComparator.LESS_THAN));
                        }
                        break;
                    case GREATER_THAN_OR_EQUAL:
                        if(!value.get().isAfter(date.get()) && !value.get().equals(date.get())){
                            inter.setValid(false);
                            inter.addError(this.getError(RelativeDateTimeComparator.GREATER_THAN_OR_EQUAL));
                        }
                        break;
                    case LESS_THAN_OR_EQUAL:
                        if(!value.get().isBefore(date.get()) && !value.get().equals(date.get())){
                            inter.setValid(false);
                            inter.addError(this.getError(RelativeDateTimeComparator.LESS_THAN_OR_EQUAL));
                        }
                        break;
                    case NOT_EQUALS:
                        if(value.get().equals(date.get())){
                            inter.setValid(false);
                            inter.addError(this.getError(RelativeDateTimeComparator.NOT_EQUALS));
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return inter;
    }
}


