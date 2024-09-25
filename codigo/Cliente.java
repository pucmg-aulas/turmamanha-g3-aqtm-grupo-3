public class Cliente extends Pessoa {
    private String identificador;

    public Cliente(String nome, String endereco, String telefone, int cpf, String email, String identificador) {
        super(nome, endereco, telefone, cpf, email);
        this.identificador = identificador;
    }

    public void adicionarVeiculo() {

    }

    public void excluirVeiculo() {

    }

}
