public class Participante implements Observer {
    private final String nome;

    public Participante(String nome) { this.nome = nome; }
    public String getNome() { return nome; }

    @Override
    public void update(Lote lote, Lance lance) {
        System.out.println("Participante " + nome + " recebeu atualizacao: Lote " + lote.getNome()
                + " novo valor R$ " + String.format("%.2f", lance.valor));
    }
}
