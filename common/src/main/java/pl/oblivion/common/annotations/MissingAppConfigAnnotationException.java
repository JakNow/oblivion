package pl.oblivion.common.annotations;

public class MissingAppConfigAnnotationException extends RuntimeException {
    
    public MissingAppConfigAnnotationException() {
        super("Add @AppConfig annotation to main class");
    }
}
