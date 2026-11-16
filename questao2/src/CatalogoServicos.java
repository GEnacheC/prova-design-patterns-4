import java.util.HashMap;
import java.util.Map;

public class CatalogoServicos {
    private final Map<String, ItemFatura> prototipos = new HashMap<>();

    public void registrar(String nome, ItemFatura prototipo) {
        prototipos.put(nome, prototipo);
    }

    public ItemFatura criar(String nome) {
        ItemFatura prototipo = prototipos.get(nome);
        if (prototipo == null) {
            throw new IllegalArgumentException("Servico nao registrado: " + nome);
        }
        return prototipo.clonar();
    }
}
