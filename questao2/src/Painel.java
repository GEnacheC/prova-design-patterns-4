import java.util.HashMap;
import java.util.Map;

public class Painel implements Observer {
    private final Map<String, Double> maiorPorLote = new HashMap<>();
    private final Map<String, Integer> totalPorLote = new HashMap<>();

    @Override
    public void update(Lote lote, Lance lance) {
        String nome = lote.getNome();
        double maior = Math.max(maiorPorLote.getOrDefault(nome, 0.0), lance.valor);
        int total = totalPorLote.getOrDefault(nome, 0) + 1;
        maiorPorLote.put(nome, maior);
        totalPorLote.put(nome, total);
        System.out.println("Painel: lote " + nome + " maior lance R$ " + String.format("%.2f", maior)
                + " | total de lances: " + total);
    }
}
