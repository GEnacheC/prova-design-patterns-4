public class AzureFactory implements CloudFactory {
    private static final java.util.concurrent.atomic.AtomicInteger vmSeq = new java.util.concurrent.atomic.AtomicInteger(1);
    private static final java.util.concurrent.atomic.AtomicInteger stSeq = new java.util.concurrent.atomic.AtomicInteger(1);
    private static final java.util.concurrent.atomic.AtomicInteger qSeq = new java.util.concurrent.atomic.AtomicInteger(1);

    public VM createVM(String id) { return new AzureVM(id); }
    public Storage createStorage(String id, int gb) { return new AzureStorage(id, gb); }
    public CloudQueue createQueue(String id) { return new AzureQueue(id); }
    public String providerName() { return "Azure"; }

    public String nextVmId() { return "vm-" + vmSeq.getAndIncrement(); }
    public String nextStorageId() { return "blob://" + stSeq.getAndIncrement(); }
    public String nextQueueId() { return "sb-" + qSeq.getAndIncrement(); }

    public double getVmCostPerHour() { return 0.12; }
    public double getStorageCostPerGB() { return 0.02; }
    public double getQueueCostPerMonth() { return 0.50; }
}
