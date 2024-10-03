public class Cliente {
    private String nome;
    private String endereco;
    private String telefone;
    private int cpf;
    private String email;
    private String identificador;
    private Veiculo[] veiculos; 
    private int quantidadeVeiculos;

    public Cliente(String nome, String endereco, String telefone, int cpf, String email, String identificador, int capacidadeVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.identificador = identificador;
        this.veiculos = new Veiculo[capacidadeVeiculos]; 
        this.quantidadeVeiculos = 0; 
    }

    public void incluirPessoa() {
       
    }

    public void editarPessoa() {
        
    }

    public void excluirPessoa() {
        
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        if (quantidadeVeiculos < veiculos.length) {
            veiculos[quantidadeVeiculos] = veiculo;
            quantidadeVeiculos++;
        } else {
            System.out.println("Não é possível adicionar mais veículos, capacidade máxima atingida.");
        }
    }

    public void excluirVeiculo(int indice) {
        if (indice >= 0 && indice < quantidadeVeiculos) {
            for (int i = indice; i < quantidadeVeiculos - 1; i++) {
                veiculos[i] = veiculos[i + 1];
            }
            veiculos[quantidadeVeiculos - 1] = null;
            quantidadeVeiculos--;
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public Veiculo[] getVeiculos() {
        return veiculos;
    }
}
