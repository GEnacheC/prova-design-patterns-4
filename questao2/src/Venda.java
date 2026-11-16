public class Venda implements ItemFatura {
    private final double valor;

    public Venda(double valor) { this.valor = valor; }

    private Venda(Venda outro) { this.valor = outro.valor; }

    @Override
    public ItemFatura clonar() { return new Venda(this); }

    @Override
    public String descricao() {
        return "Valor de venda: R$ " + String.format("%.2f", valor);
    }

    @Override
    public double custo() { return valor; }
}
