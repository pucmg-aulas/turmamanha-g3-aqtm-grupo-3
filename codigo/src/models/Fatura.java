package models;

public class Fatura {
    private Vaga vaga;
    private int minutos;

    public Fatura(Vaga vaga, int minutos) {
        this.vaga = vaga;
        this.minutos = minutos;
    }

    public double calcularValor() {
        return vaga.calcularPreco(minutos);
    }
}


// package models;

// public class Fatura {
//     private Vaga vaga;  // Agregação: Vaga já existe independentemente
//     private int minutos;
//     private Pagamento pagamento;  // Composição: Pagamento é criado e destruído junto com a Fatura

//     public Fatura(Vaga vaga, int minutos, String metodoPagamento) {
//         this.vaga = vaga;
//         this.minutos = minutos;
//         double valor = calcularValor();
//         this.pagamento = new Pagamento(valor, metodoPagamento);  // Composição: Fatura cria um Pagamento
//     }

//     public double calcularValor() {
//         return vaga.calcularPreco(minutos);
//     }

//     public Pagamento getPagamento() {
//         return pagamento;
//     }

//     @Override
//     public String toString() {
//         return "Fatura{" +
//                 "vaga=" + vaga.getId() +
//                 ", minutos=" + minutos +
//                 ", pagamento=" + pagamento +
//                 '}';
//     }

//     // Classe Pagamento usada para composição
//     public class Pagamento {
//         private double valorPago;
//         private String metodo;

//         public Pagamento(double valorPago, String metodo) {
//             this.valorPago = valorPago;
//             this.metodo = metodo;
//         }

//         public double getValorPago() {
//             return valorPago;
//         }

//         public String getMetodo() {
//             return metodo;
//         }

//         @Override
//         public String toString() {
//             return "Pagamento{" +
//                     "valorPago=" + valorPago +
//                     ", metodo='" + metodo + '\'' +
//                     '}';
//         }
//     }
// }