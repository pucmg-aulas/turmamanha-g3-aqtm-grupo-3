package models;

public class VagaVIP extends Vaga {

    public VagaVIP(String id) {
        super(id);
    }
    
   // Atributo estático para o preço por bloco de 30 minutos da vaga VIP
   private static final double PRECO_POR_BLOCO_VIP = 6.00;

   @Override
   public double calcularPreco(int minutos) {
       // Cálculo: R$6,00 por cada 30 minutos para vaga VIP
       int blocosDe30Minutos = (int) Math.ceil(minutos / 30.0); // Arredonda para cima
       return blocosDe30Minutos * PRECO_POR_BLOCO_VIP; // VIP paga R$6,00 por cada 30 minutos
   }
}

