import java.util.Date;

public class Pagamento {
    private double valor;
    private Date diaPagamento;
    private String metodoPagamento;
    private double limitePreco;

    public Pagamento(double valor, Date diaPagamento, String metodoPagamento, double limitePreco) {
        this.valor = valor;
        this.diaPagamento = diaPagamento;
        this.metodoPagamento = metodoPagamento;
        this.limitePreco = limitePreco;
    }

    public void gerarNotaFiscal() {

    }

}
