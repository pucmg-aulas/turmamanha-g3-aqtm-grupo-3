package model;

public class ClienteRanking {
    private String idCliente;
    private double totalGasto;

    public ClienteRanking(String idCliente, double totalGasto) {
        this.idCliente = idCliente;
        this.totalGasto = totalGasto;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }

    @Override
    public String toString() {
        return "ID:" + idCliente + " Contribuição:" + totalGasto;
    }
}
