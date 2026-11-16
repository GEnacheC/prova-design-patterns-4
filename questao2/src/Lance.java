import java.time.*;

public class Lance {
    public final Observer autor;
    public final double valor;
    public final LocalDateTime momento;

    public Lance(Observer autor, double valor) {
        this.autor = autor;
        this.valor = valor;
        this.momento = LocalDateTime.now();
    }
}
