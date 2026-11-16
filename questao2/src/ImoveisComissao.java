public class ImoveisComissao implements RegraComissao {
    public double calcular(double valor) { return valor * 0.03 + 1500.0; }
    public String nome() { return "Imoveis"; }
}