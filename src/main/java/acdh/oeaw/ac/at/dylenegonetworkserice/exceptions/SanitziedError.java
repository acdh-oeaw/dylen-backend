package acdh.oeaw.ac.at.dylenegonetworkserice.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.ExceptionWhileDataFetching;
import graphql.execution.ExecutionPath;
import graphql.language.SourceLocation;

public class SanitziedError extends ExceptionWhileDataFetching {


    public SanitziedError(ExecutionPath path, Throwable exception, SourceLocation sourceLocation) {
        super(path, exception, sourceLocation);
    }

    @Override
    @JsonIgnore
    public Throwable getException(){
        return super.getException();
    }
}
