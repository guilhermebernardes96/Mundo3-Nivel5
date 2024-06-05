package cadastroserver;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class SaidaFrame extends JDialog {
    private JTextArea texto;
    
    public SaidaFrame() {
        texto = new JTextArea();
        this.add(texto);
        
        this.setBounds(0, 0, 300, 300);
        this.setVisible(true);
        this.setModal(false);
    }
    
    public JTextArea getTexto() {
        return texto;
    }
    
    public void setTexto(JTextArea texto) {
        this.texto = texto;
    }
}
