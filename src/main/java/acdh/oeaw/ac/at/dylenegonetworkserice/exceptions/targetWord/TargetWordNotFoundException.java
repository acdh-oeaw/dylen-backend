package acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.targetWord;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TargetWordNotFoundException extends RuntimeException implements GraphQLError {
    private String invalidField;

    public TargetWordNotFoundException(String message) {
        super(message);
    }

    public TargetWordNotFoundException(String message, String invaliField) {
        this(message);
        this.invalidField = invaliField;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("invalidField", invalidField);
    }
}
