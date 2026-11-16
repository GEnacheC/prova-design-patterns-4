import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        Config.getInstance().load("config.properties");

        CloudFactory aws = new AwsFactory();
        CloudFactory azure = new AzureFactory();
        Provisionador provisionador = new Provisionador();

        ExecutorService ex = Executors.newFixedThreadPool(4);
        List<Callable<Void>> tasks = new ArrayList<>();
        tasks.add(() -> { provisionador.build("env-aws-1", aws, 2, 10); return null; });
        tasks.add(() -> { provisionador.build("env-aws-2", aws, 1, 5); return null; });
        tasks.add(() -> { provisionador.build("env-azure-1", azure, 3, 50); return null; });
        tasks.add(() -> { provisionador.build("env-expensive", aws, 100, 1000); return null; });

        ex.invokeAll(tasks);
        ex.shutdown();
        ex.awaitTermination(5, TimeUnit.SECONDS);

        Auditoria.getInstance().printAll();
    }
}
