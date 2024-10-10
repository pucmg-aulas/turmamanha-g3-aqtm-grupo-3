package models;

public class VagaVIP extends Vaga {

    public VagaVIP(String id) {
        super(id);
    }

    @Override
    public double calcularPreco(int minutos) {
        // Cálculo: R$6,00 por cada 30 minutos
        int blocosDe30Minutos = (int) Math.ceil(minutos / 30.0); // Arredonda para cima
        return blocosDe30Minutos * 6; // VIP paga R$6,00 por cada 30 minutos
    }
}
