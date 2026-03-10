import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    private static final String ARQUIVO = "dados_pessoas.txt";

    public static void salvar(List<Pessoa> pessoas) {

        try (FileWriter writer = new FileWriter(ARQUIVO)) {

            for (Pessoa p : pessoas) {

                writer.write(
                        p.getNome() + "," +
                                p.getAltura() + "," +
                                p.getPeso() + "\n"
                );

            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo.");
        }
    }

    public static List<Pessoa> carregar() {

        List<Pessoa> lista = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {

            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] dados = linha.split(",");

                String nome = dados[0];
                double altura = Double.parseDouble(dados[1]);
                double peso = Double.parseDouble(dados[2]);

                lista.add(new Pessoa(nome, altura, peso));
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo.");
        }

        return lista;
    }
}