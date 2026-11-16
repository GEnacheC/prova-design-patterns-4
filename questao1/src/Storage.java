public interface Storage {
    String id();
    double costPerGB();
    int gb();
    String provider();
}
