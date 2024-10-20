package models;

public class VagaIdoso extends Vaga {

    public VagaIdoso(String id) {
        super(id);
    }

    @Override
    public double calcularPreco(int minutos) {
        // Cálculo: R$3,00 por cada 30 minutos com 15% de desconto
        int blocosDe30Minutos = (int) Math.ceil(minutos / 30.0);
        double precoBase = blocosDe30Minutos * 3;
        return precoBase - (precoBase * 0.15); // Subtrai 15%
    }
}
