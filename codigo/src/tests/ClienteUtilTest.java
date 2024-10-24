/*package test;

import models.Cliente;
import utils.ClienteUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ClienteUtilTest {

    private static final String CAMINHO_TESTE = "clientes_test.txt"; // Caminho do arquivo de teste

    @Test
    public void testLerClientesDeArquivo() throws IOException {
        // Criar um arquivo de teste com dados simulados
        String conteudo = "João;12345;ABC1234\nMaria;67890;XYZ5678\n";
        Files.write(Paths.get(CAMINHO_TESTE), conteudo.getBytes());

        // Ler os clientes do arquivo
        List<Cliente> clientes = ClienteUtil.lerClientesDeArquivo(CAMINHO_TESTE);

        // Verificar se a leitura foi correta
        Assertions.assertEquals(2, clientes.size());
        Assertions.assertEquals("João", clientes.get(0).getNome());
        Assertions.assertEquals("12345", clientes.get(0).getIdentificador());
        Assertions.assertEquals("Maria", clientes.get(1).getNome());
        Assertions.assertEquals("67890", clientes.get(1).getIdentificador());

        // Remover o arquivo de teste após o teste
        Files.deleteIfExists(Paths.get(CAMINHO_TESTE));
    }

    @Test
    public void testSalvarClientesEmArquivo() throws IOException {
        // Criar uma lista de clientes para salvar
        Cliente cliente1 = new Cliente("João", "12345");
        cliente1.adicionarVeiculo(new Veiculo("ABC1234", cliente1));

        Cliente cliente2 = new Cliente("Maria", "67890");
        cliente2.adicionarVeiculo(new Veiculo("XYZ5678", cliente2));

        List<Cliente> clientes = List.of(cliente1, cliente2);

        // Salvar os clientes no arquivo
        ClienteUtil.salvarClientesEmArquivo(CAMINHO_TESTE, clientes);

        // Ler os clientes de volta do arquivo e verificar
        List<Cliente> clientesLidos = ClienteUtil.lerClientesDeArquivo(CAMINHO_TESTE);
        Assertions.assertEquals(2, clientesLidos.size());

        // Remover o arquivo de teste após o teste
        Files.deleteIfExists(Paths.get(CAMINHO_TESTE));
    }
}
*/