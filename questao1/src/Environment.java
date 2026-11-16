import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Environment {
    private static final Object SAIDA = new Object();

    private final String name;
    private final String provider;
    private final String region;
    private final List<VM> vms;
    private final Storage storage;
    private final CloudQueue queue;
    private final double estimatedCost;

    public Environment(String name, String provider, String region, List<VM> vms, Storage storage, CloudQueue queue, double cost) {
        if (vms == null || vms.isEmpty()) throw new IllegalArgumentException("Ambiente exige ao menos uma maquina virtual");
        if (storage == null) throw new IllegalArgumentException("Armazenamento obrigatorio");
        if (queue == null) throw new IllegalArgumentException("Fila obrigatoria");
        this.name = name;
        this.provider = provider;
        this.region = region;
        this.vms = Collections.unmodifiableList(new ArrayList<>(vms));
        this.storage = storage;
        this.queue = queue;
        this.estimatedCost = cost;
        validarMesmoProvedor();
    }

    private void validarMesmoProvedor() {
        for (VM vm : vms) {
            if (!provider.equals(vm.provider())) {
                throw new IllegalArgumentException("VM de outro provedor: " + vm.id());
            }
        }
        if (!provider.equals(storage.provider())) {
            throw new IllegalArgumentException("Armazenamento de outro provedor: " + storage.id());
        }
        if (!provider.equals(queue.provider())) {
            throw new IllegalArgumentException("Fila de outro provedor: " + queue.id());
        }
    }

    public void printSummary() {
        synchronized (SAIDA) {
            System.out.println("Ambiente: " + name);
            System.out.println("Provedor: " + provider);
            System.out.println("Regiao: " + region);
            for (VM vm : vms) System.out.println("VM: " + vm.id());
            System.out.println("Armazenamento: " + storage.id());
            System.out.println("Fila: " + queue.id());
            System.out.println("Custo mensal estimado: R$ " + String.format("%.2f", estimatedCost));
        }
    }
}
