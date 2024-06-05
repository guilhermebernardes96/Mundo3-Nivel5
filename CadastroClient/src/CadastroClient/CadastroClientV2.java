package cadastroclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import model.Produto;

public class CadastroClientV2 {
    public static void main(String[] args)throws IOException, ClassNotFoundException {
        Socket clientSocket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            clientSocket = new Socket(InetAddress.getByName("localhost"), 4321);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            System.out.println("Usuario: ");
            out.writeObject(reader.readLine());

            System.out.println("Senha: ");
            out.writeObject(reader.readLine());

            String result = (String) in.readObject();
            if (!"ok".equals(result)) {
                System.out.println("Erro de login");
                return;
            }
            System.out.println("Login com sucesso");

            String comando;
            do {
                System.out.println("L - Listar | X - Finalizar | E - Entrada | S - Saida");
                comando = reader.readLine();
                out.writeObject(comando);

                if ("l".equalsIgnoreCase(comando)) {

                    List<Produto> Produtos = (List<Produto>) in.readObject();

                    for (Produto produto : Produtos) {
                        System.out.println(produto.getNome());
                    }
                } else if ("e".equalsIgnoreCase(comando) || "s".equalsIgnoreCase(comando)) {
                    System.out.println("Id da Pessoa:");
                    String idPessoa = reader.readLine();
                    
                    System.out.println("Id do Produto");
                    String idProduto = reader.readLine();
                    
                    System.out.println("Quantidade");
                    String quantidade = reader.readLine();
                    
                    System.out.println("Valor Unitario");
                    String valor = reader.readLine();
                    
                    out.writeObject(idPessoa);
                    out.writeObject(idProduto);
                    out.writeObject(quantidade);
                    out.writeObject(valor);
                }

            } while (!"x".equalsIgnoreCase(comando));

        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
        }
    }
}