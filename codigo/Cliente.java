import java.util.Date;

public class Cliente {
    private String nome;
    private String endereco;
    private String telefone;
    private int cpf;
    private String email;
    private String identificador;
    private Veiculo[] veiculos;
    private RegistroVeiculo[] registros;
    private int quantidadeVeiculos;

    public Cliente(String nome, String endereco, String telefone, int cpf, String email, String identificador, int capacidadeVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.identificador = identificador;
        this.veiculos = new Veiculo[capacidadeVeiculos];
        this.registros = new RegistroVeiculo[capacidadeVeiculos];
        this.quantidadeVeiculos = 0;
    }

    public void incluirPessoa() {
    }

    public void editarPessoa() {
    }

    public void excluirPessoa() {
    }

    public void adicionarVeiculo(Veiculo veiculo, Date dataEntrada) {
    }

    public void excluirVeiculo(int indice) {
    }

    public Veiculo[] getVeiculos() {
        return veiculos;
    }

    public RegistroVeiculo[] getRegistros() {
        return registros;
    }
}
