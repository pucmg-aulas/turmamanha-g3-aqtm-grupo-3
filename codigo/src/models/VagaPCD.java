package models;

public class VagaPCD extends Vaga {

    public VagaPCD(String id) {
        super(id);
    }

     // Atributos estáticos para o cálculo
     private static final double PRECO_POR_BLOCO = 3.00; // Preço base por cada bloco de 30 minutos
     private static final double DESCONTO = 0.13; // 13% de desconto
 
     @Override
     public double calcularPreco(int minutos) {
         // Cálculo: R$3,00 por cada 30 minutos com 13% de desconto
         int blocosDe30Minutos = (int) Math.ceil(minutos / 30.0);  // Calcula o número de blocos
         double precoBase = blocosDe30Minutos * PRECO_POR_BLOCO;   // Preço sem desconto
         return precoBase - (precoBase * DESCONTO);                // Subtrai 13%
     }
 }

