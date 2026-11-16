import java.util.ArrayList;
import java.util.List;

public class Provisionador {
    public Environment build(String name, CloudFactory factory, int vmCount, int gbStorage) {
        String region = Config.getInstance().getRegion();
        double budget = Config.getInstance().getBudget();

        double total = vmCount * factory.getVmCostPerHour() * 730
                + gbStorage * factory.getStorageCostPerGB()
                + factory.getQueueCostPerMonth();

        if (vmCount < 1) {
            throw new IllegalArgumentException("Informe ao menos uma maquina virtual");
        }

        if (total > budget) {
            Auditoria.getInstance().log(name, factory.providerName(),
                    "RECUSADO: custo estimado R$ " + String.format("%.2f", total)
                            + " excede o orcamento de R$ " + String.format("%.2f", budget));
            return null;
        }

        List<VM> vms = new ArrayList<>();
        for (int i = 0; i < vmCount; i++) vms.add(factory.createVM(factory.nextVmId()));
        Storage storage = factory.createStorage(factory.nextStorageId(), gbStorage);
        CloudQueue queue = factory.createQueue(factory.nextQueueId());

        Environment env = new Environment(name, factory.providerName(), region, vms, storage, queue, total);
        env.printSummary();
        Auditoria.getInstance().log(name, factory.providerName(), "ACEITO");
        return env;
    }
}
