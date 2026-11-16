import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Auditoria {
    private static final Auditoria instance = new Auditoria();
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final List<String> registros = Collections.synchronizedList(new ArrayList<>());

    private Auditoria() {}

    public static Auditoria getInstance() { return instance; }

    public void log(String envName, String provider, String resultado) {
        String ts = LocalDateTime.now().format(FORMATO);
        registros.add(ts + " - " + envName + " - " + provider + " - " + resultado);
    }

    public void printAll() {
        synchronized (registros) {
            System.out.println("--- HISTORICO ---");
            for (String r : registros) System.out.println(r);
        }
    }
}
