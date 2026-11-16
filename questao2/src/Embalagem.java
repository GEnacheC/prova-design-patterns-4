public class Embalagem implements ItemFatura {
    @Override
    public ItemFatura clonar() { return new Embalagem(); }

    @Override
    public String descricao() {
        return "Embalagem especial - R$ " + String.format("%.2f", custo());
    }

    @Override
    public double custo() { return 180.0; }
}
