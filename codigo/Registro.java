import java.util.Date;

public class Registro {
    private Date dataEntrada;
    private Date dataSaida;

    public Registro(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public long tempoUso() {
        return dataSaida.getTime() - dataEntrada.getTime();
    }

    public double calcularValor() {
        long tempoMilissegundos = tempoUso();
        long minutos = tempoMilissegundos / (1000 * 60);
        long fracoes = (minutos + 14) / 15;
        double preco = fracoes * 4.0;
        return Math.min(preco, Pagamento.limitePreco);
    }
}
