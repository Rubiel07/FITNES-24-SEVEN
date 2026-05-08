package fitness24seven;
/*
Conexion entre las interfazes
Importaciones -> Swing Grafica -> javax.swing.SwingUtilities;
*/
import javax.swing.SwingUtilities;
public class FITNESS24SEVEN {
    public static void main(String[] args) {
        SwingUtilities.invokeLater( ()->{
            new Login().setVisible(true);
        });
    }
    
}
