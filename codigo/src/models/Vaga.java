// package models;

// public abstract class Vaga {
//     private String id;
//     private boolean ocupada;

//     public Vaga(String id) {
//         this.id = id;
//         this.ocupada = false;
//     }

//     public String getId() {
//         return id;
//     }

//     public boolean isOcupada() {
//         return ocupada;
//     }

//     public void ocupar() {
//         this.ocupada = true;
//     }

//     public void desocupar() {
//         this.ocupada = false;
//     }

//     public abstract double calcularPreco(int minutos);
// }

package models;

public abstract class Vaga {
    private String id;
    private boolean ocupada;
    private Veiculo veiculo;  // Agregação: Vaga tem uma relação com Veiculo, mas não controla seu ciclo de vida
    private HistoricoOcupacao historico;  // Composição: Vaga controla o ciclo de vida do HistoricoOcupacao

    public Vaga(String id) {
        this.id = id;
        this.ocupada = false;
        this.historico = new HistoricoOcupacao();  // Composição: Vaga cria o histórico no momento de sua criação
    }

    public String getId() {
        return id;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void ocupar(Veiculo veiculo) {
        this.ocupada = true;
        this.veiculo = veiculo;  // Relacionamento de agregação com o Veiculo
        historico.registrarEntrada(veiculo);  // Atualiza o histórico quando a vaga é ocupada
    }

    public void desocupar() {
        this.ocupada = false;
        historico.registrarSaida(veiculo);  // Atualiza o histórico quando a vaga é desocupada
        this.veiculo = null;  // Remove a referência ao veículo
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public HistoricoOcupacao getHistorico() {
        return historico;
    }

    public abstract double calcularPreco(int minutos);
}