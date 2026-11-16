public class AzureQueue implements CloudQueue {
    private String id;
    public AzureQueue(String id) { this.id = id; }
    public String id() { return id; }
    public double costPerMonth() { return 0.50; }
    public String provider() { return "Azure"; }
}
