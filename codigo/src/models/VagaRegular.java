package models;

public class VagaRegular extends Vaga {

    public VagaRegular(String id) {
        super(id);
    }

    // Atributo estático para o preço por bloco de 30 minutos
    private static final double PRECO_POR_BLOCO = 3.00;

    @Override
    public double calcularPreco(int minutos) {
        // Cálculo: R$3,00 por cada 30 minutos
        int blocosDe30Minutos = (int) Math.ceil(minutos / 30.0); // Arredonda para cima
        return blocosDe30Minutos * PRECO_POR_BLOCO;
    }

    
}
