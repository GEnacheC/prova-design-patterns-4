public interface Observer {
    void update(Lote lote, Lance lance);
    default String getNome() { return ""; }
}