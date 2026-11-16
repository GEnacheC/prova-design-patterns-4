public class AzureVM implements VM {
    private String id;
    public AzureVM(String id) { this.id = id; }
    public String id() { return id; }
    public double costPerHour() { return 0.12; }
    public String provider() { return "Azure"; }
}
