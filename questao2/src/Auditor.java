import java.time.format.DateTimeFormatter;

public class Auditor implements Observer {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void update(Lote lote, Lance lance) {
        System.out.println("Auditor: registro - " + FORMATO.format(lance.momento)
                + " - Lote " + lote.getNome()
                + " - Participante " + lance.autor.getNome()
                + " - Valor R$ " + String.format("%.2f", lance.valor));
    }
}
