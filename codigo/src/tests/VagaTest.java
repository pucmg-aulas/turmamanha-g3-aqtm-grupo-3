//package tests;
//
//import models.VagaRegular;
//import models.VagaIdoso;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//class VagaTest {
//
//    @Test
//    void testVagaRegular() {
//        VagaRegular vaga = new VagaRegular("A01");
//        // Testa o cálculo do preço para 60 minutos (4 frações de 15 minutos)
//        assertEquals(16, vaga.calcularPreco(60));
//        // Testa o cálculo para um tempo que excede o limite de R$50
//        assertEquals(50, vaga.calcularPreco(240)); // 240 minutos = 16 frações, mas limitado a R$50
//    }
//
//    @Test
//    void testVagaIdoso() {
//        VagaIdoso vaga = new VagaIdoso("B01");
//        // Testa o cálculo do preço com desconto para 60 minutos
//        assertEquals(13.6, vaga.calcularPreco(60), 0.01); // 15% de desconto sobre R$16
//        // Testa o limite de R$50 mesmo com desconto
//        assertEquals(50, vaga.calcularPreco(240)); // Excede o limite, aplica R$50 máximo
//    }
//}
