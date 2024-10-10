package utils;

import models.Vaga;
import models.VagaRegular;
import models.VagaVIP;
import models.VagaIdoso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    public static List<Vaga> lerVagasDeArquivo(String caminhoArquivo) throws IOException {
        List<Vaga> vagas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            // Leia cada linha do arquivo até chegar ao final (linha == null)
            while ((linha = reader.readLine()) != null) {
                // Evita processar linhas vazias
                if (linha.trim().isEmpty()) {
                    continue;  // Pula linhas vazias
                }

                String[] partes = linha.split(" "); // Espera-se que o formato seja: "TIPO_VAGA ID_VAGA"
                
                if (partes.length == 2) {
                    String tipoVaga = partes[0];
                    String idVaga = partes[1];

                    // Identifica o tipo de vaga e cria a instância correta
                    switch (tipoVaga.toUpperCase()) {
                        case "REGULAR":
                            vagas.add(new VagaRegular(idVaga));
                            break;
                        case "VIP":
                            vagas.add(new VagaVIP(idVaga));
                            break;
                        case "IDOSO":
                            vagas.add(new VagaIdoso(idVaga));
                            break;
                        default:
                            System.out.println("Tipo de vaga desconhecido: " + tipoVaga);
                    }
                } else {
                    System.out.println("Linha no formato inválido: " + linha);
                }
            }
        }

        return vagas;
    }
}
