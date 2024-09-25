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

    public double precoUso() {

        return tempoUso() * 0.05;
    }

}
