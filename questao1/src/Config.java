import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Config instance = new Config();
    private final Properties props = new Properties();
    private boolean loaded = false;

    private Config() {}

    public static Config getInstance() { return instance; }

    public synchronized void load(String path) {
        if (loaded) return;
        try (InputStream in = abrir(path)) {
            props.load(in);
            loaded = true;
        } catch (IOException e) {
            throw new RuntimeException("Nao foi possivel ler config.properties", e);
        }
    }

    private InputStream abrir(String path) throws IOException {
        String nome = new File(path).getName();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) cl = Config.class.getClassLoader();
        InputStream recurso = cl.getResourceAsStream(nome);
        if (recurso != null) return recurso;

        File[] candidatos = {
            new File(path),
            new File("questao1/src/" + nome),
            new File("src/" + nome)
        };
        for (File arquivo : candidatos) {
            if (arquivo.isFile()) return new FileInputStream(arquivo);
        }
        throw new IOException("Arquivo nao encontrado: " + path);
    }

    public String getRegion() {
        exigirCarregado();
        return props.getProperty("region");
    }

    public double getBudget() {
        exigirCarregado();
        return Double.parseDouble(props.getProperty("budget"));
    }

    private void exigirCarregado() {
        if (!loaded) throw new IllegalStateException("Configuracao ainda nao foi lida");
    }
}
