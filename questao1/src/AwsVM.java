public class AwsVM implements VM {
    private String id;
    public AwsVM(String id) { this.id = id; }
    public String id() { return id; }
    public double costPerHour() { return 0.10; }
    public String provider() { return "Aws"; }
}
