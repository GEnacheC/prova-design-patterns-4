public class Main {
    public static void main(String[] args) {
        Participante p1 = new Participante("Alice");
        Participante p2 = new Participante("Bruno");
        Participante p3 = new Participante("Carla");
        Painel painel = new Painel();
        Auditor auditor = new Auditor();

        Lote lote1 = new Lote("Quadro Monet", "Arte", new ArteComissao(), 1000.0, 1100.0);
        Lote lote2 = new Lote("Carro Antigo", "Veiculos", new VeiculosComissao(), 5000.0, 8000.0);

        lote1.addObserver(p1);
        lote1.addObserver(p2);
        lote1.addObserver(p3);
        lote1.addObserver(painel);
        lote1.addObserver(auditor);
        lote2.addObserver(p1);
        lote2.addObserver(painel);
        lote2.addObserver(auditor);

        lote1.darLance(p1, 1000.0);
        lote1.darLance(p1, 1050.0);
        lote1.darLance(p2, 1105.0);
        lote1.removeObserver(p2);
        lote1.darLance(p3, 1200.0);

        lote1.setRegraComissao(new EletronicosComissao());
        lote1.encerrar();

        lote2.darLance(p1, 5000.0);
        lote2.darLance(p2, 5300.0);
        lote2.darLance(p3, 5600.0);
        lote2.encerrar();

        if (lote1.isVendido()) {
            double venda = lote1.getValorVenda();
            CatalogoServicos catalogo = new CatalogoServicos();
            catalogo.registrar("venda", new Venda(venda));
            catalogo.registrar("seguro", new SeguroTransporte(venda));
            catalogo.registrar("certificado", new Certificado());
            catalogo.registrar("embalagem", new Embalagem());
            catalogo.registrar("entrega", new EntregaExpressa());

            Fatura fatura = new Fatura(lote1);
            fatura.adicionar(catalogo.criar("venda"));
            fatura.adicionar(catalogo.criar("seguro"));
            fatura.adicionar(catalogo.criar("certificado"));
            fatura.adicionar(catalogo.criar("embalagem"));
            fatura.adicionar(catalogo.criar("certificado"));
            fatura.imprimir();

            System.out.println("--- ACERTO DO VENDEDOR ---");
            System.out.println("Valor de venda: R$ " + String.format("%.2f", venda));
            System.out.println("Comissao (" + lote1.getRegraComissao().nome() + "): R$ "
                    + String.format("%.2f", lote1.getComissaoCobrada()));
            System.out.println("Valor liquido a repassar: R$ "
                    + String.format("%.2f", venda - lote1.getComissaoCobrada()));
        }
    }
}
