package iticbcn.xifratge;

public class ClauNoSuportada extends Exception {
    public ClauNoSuportada() {
        super();
    }

    public ClauNoSuportada(String message) {
        super(message);
    }

    public ClauNoSuportada(String message, Throwable err) {
        super(message, err);
    }
    
}