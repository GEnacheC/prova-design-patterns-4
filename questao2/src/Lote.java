import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lote {
    private final String nome;
    private final String categoria;
    private RegraComissao regra;
    private final double precoInicial;
    private final double precoReserva;
    private Lance melhor;
    private boolean aberto = true;
    private boolean encerrado = false;
    private boolean vendido = false;
    private double comissaoCobrada = 0;
    private final List<Lance> lances = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    public Lote(String nome, String categoria, RegraComissao regra, double precoInicial, double precoReserva) {
        this.nome = nome;
        this.categoria = categoria;
        this.regra = regra;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
    }

    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }

    public synchronized void addObserver(Observer o) { observers.add(o); }
    public synchronized void removeObserver(Observer o) { observers.remove(o); }

    public boolean darLance(Observer autor, double valor) {
        Lance aceito = null;
        synchronized (this) {
            if (!aberto) {
                System.out.println("Lote fechado. Lance recusado.");
                return false;
            }
            double atual = (melhor == null) ? precoInicial : melhor.valor;
            if (!cobreMinimo(valor, atual)) {
                System.out.println("Lance recusado: deve ser >= 5% acima do atual. Valor atual: R$ " + String.format("%.2f", atual));
                return false;
            }
            aceito = new Lance(autor, valor);
            lances.add(aceito);
            melhor = aceito;
        }
        notificar(aceito);
        return true;
    }

    private static boolean cobreMinimo(double valor, double atual) {
        long valorCentavos = Math.round(valor * 100);
        long minimoCentavos = Math.round(atual * 1.05 * 100);
        return valorCentavos >= minimoCentavos;
    }

    private void notificar(Lance lance) {
        List<Observer> snapshot;
        synchronized (this) {
            snapshot = new ArrayList<>(observers);
        }
        for (Observer o : snapshot) {
            if (o == lance.autor) continue;
            o.update(this, lance);
        }
    }

    public synchronized void encerrar() {
        if (encerrado) return;
        encerrado = true;
        aberto = false;
        vendido = melhor != null && melhor.valor >= precoReserva;
        if (vendido) {
            comissaoCobrada = regra.calcular(melhor.valor);
            System.out.println("Lote " + nome + " (" + categoria + ") vendido por R$ " + String.format("%.2f", melhor.valor));
        } else {
            System.out.println("Lote " + nome + " (" + categoria + ") nao vendido");
        }
    }

    public synchronized void setRegraComissao(RegraComissao regra) {
        if (!aberto) throw new IllegalStateException("Nao e possivel trocar a comissao de um lote encerrado");
        this.regra = regra;
    }

    public synchronized RegraComissao getRegraComissao() { return regra; }
    public synchronized Lance getMelhor() { return melhor; }
    public synchronized boolean isVendido() { return vendido; }
    public synchronized double getValorVenda() { return vendido ? melhor.valor : 0; }
    public synchronized double getComissaoCobrada() { return comissaoCobrada; }
    public synchronized List<Lance> getLances() { return Collections.unmodifiableList(new ArrayList<>(lances)); }
}
