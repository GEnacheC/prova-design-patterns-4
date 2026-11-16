public class Certificado implements ItemFatura {
    @Override
    public ItemFatura clonar() { return new Certificado(); }

    @Override
    public String descricao() {
        return "Certificado de autenticidade - R$ " + String.format("%.2f", custo());
    }

    @Override
    public double custo() { return 300.0; }
}
