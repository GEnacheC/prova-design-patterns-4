public class EntregaExpressa implements ItemFatura {
    @Override
    public ItemFatura clonar() { return new EntregaExpressa(); }

    @Override
    public String descricao() {
        return "Entrega expressa - R$ " + String.format("%.2f", custo());
    }

    @Override
    public double custo() { return 450.0; }
}
