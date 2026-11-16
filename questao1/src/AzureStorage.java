public class AzureStorage implements Storage {
    private String id; private int gb;
    public AzureStorage(String id, int gb) { this.id = id; this.gb = gb; }
    public String id() { return id; }
    public double costPerGB() { return 0.02; }
    public int gb() { return gb; }
    public String provider() { return "Azure"; }
}
