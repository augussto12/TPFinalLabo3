package Exceptions;

public class PacienteInvalidoException extends RuntimeException {
    public PacienteInvalidoException(String message) {
        super(message);
    }
}
