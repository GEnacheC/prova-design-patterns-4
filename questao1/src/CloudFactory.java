public interface CloudFactory {
    VM createVM(String id);
    Storage createStorage(String id, int gb);
    CloudQueue createQueue(String id);
    String providerName();
    String nextVmId();
    String nextStorageId();
    String nextQueueId();
    double getVmCostPerHour();
    double getStorageCostPerGB();
    double getQueueCostPerMonth();
}
