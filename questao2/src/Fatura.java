import java.util.ArrayList;
import java.util.List;

public class Fatura {
    private final Lote lote;
    private final List<ItemFatura> itens = new ArrayList<>();

    public Fatura(Lote lote) {
        this.lote = lote;
    }

    public void adicionar(ItemFatura item) {
        itens.add(item);
    }

    public void imprimir() {
        System.out.println("--- FATURA ---");
        System.out.println("Lote: " + lote.getNome());
        double total = 0;
        for (ItemFatura item : itens) {
            System.out.println(item.descricao());
            total += item.custo();
        }
        System.out.println("Total a pagar: R$ " + String.format("%.2f", total));
    }
}
