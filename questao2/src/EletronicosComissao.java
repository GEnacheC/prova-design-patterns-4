public class EletronicosComissao implements RegraComissao {
    public double calcular(double valor) { return Math.max(valor * 0.12, 50.0); }
    public String nome() { return "Eletronicos"; }
}