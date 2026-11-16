public class SeguroTransporte implements ItemFatura {
    private final double valorVenda;

    public SeguroTransporte(double valorVenda) { this.valorVenda = valorVenda; }

    private SeguroTransporte(SeguroTransporte outro) { this.valorVenda = outro.valorVenda; }

    @Override
    public ItemFatura clonar() { return new SeguroTransporte(this); }

    @Override
    public String descricao() {
        return "Seguro de transporte (1,5%) - R$ " + String.format("%.2f", custo());
    }

    @Override
    public double custo() { return valorVenda * 0.015; }
}
