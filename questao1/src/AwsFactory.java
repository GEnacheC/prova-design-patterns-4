public class AwsFactory implements CloudFactory {
    private static final java.util.concurrent.atomic.AtomicInteger vmSeq = new java.util.concurrent.atomic.AtomicInteger(1);
    private static final java.util.concurrent.atomic.AtomicInteger stSeq = new java.util.concurrent.atomic.AtomicInteger(1);
    private static final java.util.concurrent.atomic.AtomicInteger qSeq = new java.util.concurrent.atomic.AtomicInteger(1);

    public VM createVM(String id) { return new AwsVM(id); }
    public Storage createStorage(String id, int gb) { return new AwsStorage(id, gb); }
    public CloudQueue createQueue(String id) { return new AwsQueue(id); }
    public String providerName() { return "Aws"; }

    public String nextVmId() { return "i-" + vmSeq.getAndIncrement(); }
    public String nextStorageId() { return "s3://" + stSeq.getAndIncrement(); }
    public String nextQueueId() { return "sqs-" + qSeq.getAndIncrement(); }

    public double getVmCostPerHour() { return 0.10; }
    public double getStorageCostPerGB() { return 0.023; }
    public double getQueueCostPerMonth() { return 0.40; }
}
