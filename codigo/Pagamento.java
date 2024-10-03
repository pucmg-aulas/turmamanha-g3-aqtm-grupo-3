import java.util.Date;

public class Pagamento {
    private double valor;
    private Date diaPagamento;
    private String metodoPagamento;
    
    public static final double LimitePreco = 50.0;

    public Pagamento(double valor, Date diaPagamento, String metodoPagamento) {
        this.valor = valor;
        this.diaPagamento = diaPagamento;
        this.metodoPagamento = metodoPagamento;
    }

    public void gerarPagamento(Registro registro, Date diaPagamento, String metodoPagamento) {
        this.valor = registro.calcularValor();
        this.diaPagamento = diaPagamento;
        this.metodoPagamento = metodoPagamento;
    }

    public void gerarNotaFiscal() {
    }
}
