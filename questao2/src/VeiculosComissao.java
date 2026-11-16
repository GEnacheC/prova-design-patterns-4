public class VeiculosComissao implements RegraComissao {
    public double calcular(double valor) { return Math.min(valor * 0.08, 5000.0); }
    public String nome() { return "Veiculos"; }
}