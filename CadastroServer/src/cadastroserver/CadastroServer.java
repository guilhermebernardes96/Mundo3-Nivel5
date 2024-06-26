package cadastroserver;

import java.io.IOException;
import javax.swing.SwingUtilities;

public class CadastroServer {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            SaidaFrame frame = new SaidaFrame();
            ThreadClient client = new ThreadClient(frame.getTexto());
            client.start();
        });
    }
}
