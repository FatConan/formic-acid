package de.themonstrouscavalca.formicacid.validators.impl.datetime;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class RelativeTimesValidator implements IValidate<LocalTime>{
    private final Map<RelativeDateTimeComparator, String> DEFAULT_ERROR_MESSAGES = new HashMap<>();
    {
        DEFAULT_ERROR_MESSAGES.put(RelativeDateTimeComparator.EQUALS, "The provided times are not equivalent");
        DEFAULT_ERROR_MESSAGES.put(RelativeDateTimeComparator.NOT_EQUALS, "The provided times may not be the same");
        DEFAULT_ERROR_MESSAGES.put(RelativeDateTimeComparator.GREATER_THAN, "This must be later than the previous time");
        DEFAULT_ERROR_MESSAGES.put(RelativeDateTimeComparator.GREATER_THAN_OR_EQUAL, "This must be later than or equal to the previous time");
        DEFAULT_ERROR_MESSAGES.put(RelativeDateTimeComparator.LESS_THAN, "This must be earlier than the previous time");
        DEFAULT_ERROR_MESSAGES.put(RelativeDateTimeComparator.LESS_THAN_OR_EQUAL, "This must be earlier than or equal to the previous time");
    }
    private final Map<RelativeDateTimeComparator, String> overriddenErrorMessages;
    private final LocalTime time;
    private final RelativeDateTimeComparator comparator;

    public RelativeTimesValidator(LocalTime time, RelativeDateTimeComparator comparator){
        this(time, comparator, null);
    }

    public RelativeTimesValidator(LocalTime time, RelativeDateTimeComparator comparator, Map<RelativeDateTimeComparator, String> overriddenErrorMessages){
        this.overriddenErrorMessages = overriddenErrorMessages;
        this.time = time;
        this.comparator = comparator;
    }

    private String getError(RelativeDateTimeComparator key){
        if(overriddenErrorMessages != null && overriddenErrorMessages.containsKey(key)){
            return overriddenErrorMessages.get(key);
        }

        return DEFAULT_ERROR_MESSAGES.get(key);
    }

    private boolean isPresent(LocalTime value){
        return value != null;
    }

    @Override
    public IntermediateValidateOptional<LocalTime> getValidatedValue(LocalTime value){
        IntermediateValidateOptional<LocalTime> inter = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            if(this.isPresent(time)){
                switch(comparator){
                    case EQUALS:
                        if(!value.equals(time)){
                            inter.setValid(false);
                            inter.addError(this.getError(RelativeDateTimeComparator.EQUALS));
                        }
                        break;
                    case GREATER_THAN:
                        if(!value.isAfter(time)){
                            inter.setValid(false);
                            inter.addError(this.getError(RelativeDateTimeComparator.GREATER_THAN));
                        }
                        break;
                    case LESS_THAN:
                        if(!value.isBefore(time)){
                            inter.setValid(false);
                            inter.addError(this.getError(RelativeDateTimeComparator.LESS_THAN));
                        }
                        break;
                    case GREATER_THAN_OR_EQUAL:
                        if(!value.isAfter(time) && !value.equals(time)){
                            inter.setValid(false);
                            inter.addError(this.getError(RelativeDateTimeComparator.GREATER_THAN_OR_EQUAL));
                        }
                        break;
                    case LESS_THAN_OR_EQUAL:
                        if(!value.isBefore(time) && !value.equals(time)){
                            inter.setValid(false);
                            inter.addError(this.getError(RelativeDateTimeComparator.LESS_THAN_OR_EQUAL));
                        }
                        break;
                    case NOT_EQUALS:
                        if(value.equals(time)){
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
