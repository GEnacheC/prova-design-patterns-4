public class AwsQueue implements CloudQueue {
    private String id;
    public AwsQueue(String id) { this.id = id; }
    public String id() { return id; }
    public double costPerMonth() { return 0.40; }
    public String provider() { return "Aws"; }
}
