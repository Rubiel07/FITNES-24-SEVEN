/*
 * GUI Login – FITNESS 24 SEVEN
 * Validaciones: campos vacíos, longitud mínima, formato usuario, credenciales
 */
package fitness24seven;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {

    private static final Color NEGRO    = new Color(20, 20, 20);
    private static final Color AMARILLO = new Color(245, 200, 0);
    private static final Color GRIS     = new Color(45, 45, 45);
    private static final Color BLANCO   = new Color(230, 230, 230);
    private static final Color ROJO     = new Color(220, 60, 60);

    private JTextField        txtUsuario;
    private JPasswordField    txtContraseña;
    private JComboBox<String> txtPais;
    private JButton           btnLogin;

    //labels de error debajo de cada campo
    private JLabel lblErrUsuario, lblErrContrasena;

    public Login() {
        setTitle("FITNESS 24-SEVEN | Login");
        setSize(340, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(NEGRO);

        //título
        JLabel lblTitulo = new JLabel("FITNESS 24-SEVEN", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 18));
        lblTitulo.setForeground(AMARILLO);
        lblTitulo.setBounds(0, 15, 340, 28);
        add(lblTitulo);

        JLabel lblSub = new JLabel("Iniciar sesión", SwingConstants.CENTER);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSub.setForeground(new Color(150, 150, 150));
        lblSub.setBounds(0, 45, 340, 18);
        add(lblSub);

        //usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 12));
        lblUsuario.setForeground(AMARILLO);
        lblUsuario.setBounds(50, 78, 100, 20);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(50, 98, 240, 28);
        estilizarInput(txtUsuario);
        add(txtUsuario);

        lblErrUsuario = new JLabel("");
        lblErrUsuario.setFont(new Font("Arial", Font.PLAIN, 10));
        lblErrUsuario.setForeground(ROJO);
        lblErrUsuario.setBounds(50, 128, 240, 16);
        add(lblErrUsuario);

        //contraseña 
        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("Arial", Font.BOLD, 12));
        lblPass.setForeground(AMARILLO);
        lblPass.setBounds(50, 148, 100, 20);
        add(lblPass);

        txtContraseña = new JPasswordField();
        txtContraseña.setBounds(50, 168, 240, 28);
        estilizarInput(txtContraseña);
        add(txtContraseña);

        lblErrContrasena = new JLabel("");
        lblErrContrasena.setFont(new Font("Arial", Font.PLAIN, 10));
        lblErrContrasena.setForeground(ROJO);
        lblErrContrasena.setBounds(50, 198, 240, 16);
        add(lblErrContrasena);

        //país
        JLabel lblPais = new JLabel("País:");
        lblPais.setFont(new Font("Arial", Font.BOLD, 12));
        lblPais.setForeground(AMARILLO);
        lblPais.setBounds(50, 218, 100, 20);
        add(lblPais);

        String[] paises = {"Colombia", "México", "Argentina", "Chile", "España"};
        txtPais = new JComboBox<>(paises);
        txtPais.setBounds(50, 238, 240, 28);
        txtPais.setBackground(GRIS);
        txtPais.setForeground(BLANCO);
        txtPais.setFont(new Font("Arial", Font.PLAIN, 13));
        add(txtPais);

        //botón Ingresar
        btnLogin = new JButton("INGRESAR");
        btnLogin.setBounds(50, 282, 240, 32);
        btnLogin.setBackground(AMARILLO);
        btnLogin.setForeground(NEGRO);
        btnLogin.setFont(new Font("Arial Black", Font.BOLD, 13));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(btnLogin);

        //evento Login con validaciones 
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //limpiar errores anteriores
                limpiarErrores();

                String usuario    = txtUsuario.getText().trim();
                String contraseña = new String(txtContraseña.getPassword());
                String pais       = (String) txtPais.getSelectedItem();
                boolean hayError  = false;

                //para usuario vacío
                if (usuario.isEmpty()) {
                    mostrarError(lblErrUsuario, txtUsuario, "El usuario no puede estar vacío.");
                    hayError = true;

                //para longitud mínima usuario
                } else if (usuario.length() < 3) {
                    mostrarError(lblErrUsuario, txtUsuario, "Mínimo 3 caracteres.");
                    hayError = true;

                //para usuario solo letras y números
                } else if (!usuario.matches("[a-zA-Z0-9]+")) {//->la ia me ayudo con esto([a-zA-Z0-9]+)
                    mostrarError(lblErrUsuario, txtUsuario, "Solo letras y números, sin espacios.");
                    hayError = true;
                }

                //para contraseña vacía
                if (contraseña.isEmpty()) {
                    mostrarError(lblErrContrasena, txtContraseña, "La contraseña no puede estar vacía.");
                    hayError = true;

                //para longitud mínima contraseña
                } else if (contraseña.length() < 4) {
                    mostrarError(lblErrContrasena, txtContraseña, "Mínimo 4 caracteres.");
                    hayError = true;
                }

                if (hayError) return;

                //para verificar credenciales
                if (usuario.equals("admin") && contraseña.equals("admin")
                        && pais.equals("Colombia")) {
                    JOptionPane.showMessageDialog(null, "¡Bienvenido, " + usuario + "!");
                    VentanaPrincipal vp = new VentanaPrincipal();
                    vp.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Usuario, contraseña o país incorrecto.",
                        "Acceso denegado", JOptionPane.ERROR_MESSAGE);
                    txtContraseña.setText("");
                    txtContraseña.requestFocus();
                }
            }
        });

        //limpiar errores del usuario al escribir
        txtUsuario.addKeyListener(new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) {
                limpiarCampo(lblErrUsuario, txtUsuario);
            }
        });

        //limpiar errores de contraseña al escribir
        txtContraseña.addKeyListener(new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) {
                limpiarCampo(lblErrContrasena, txtContraseña);
            }
        });

        setVisible(true);
    }

    //mostrar mensaje de error y poner un borde rojo en el campo
    private void mostrarError(JLabel lbl, JComponent campo, String msg) {
        lbl.setText(msg);
        campo.setBorder(BorderFactory.createLineBorder(ROJO, 1));
        campo.requestFocus();
    }

    //limpiar el error de un campo al escribir
    private void limpiarCampo(JLabel lbl, JComponent campo) {
        lbl.setText("");
        campo.setBorder(BorderFactory.createLineBorder(new Color(65, 65, 65)));
    }

    //limpiar todos los errores
    private void limpiarErrores() {
        lblErrUsuario.setText("");
        lblErrContrasena.setText("");
        txtUsuario.setBorder(BorderFactory.createLineBorder(new Color(65, 65, 65)));
        txtContraseña.setBorder(BorderFactory.createLineBorder(new Color(65, 65, 65)));
    }

    private void estilizarInput(JComponent comp) {
        comp.setBackground(GRIS);
        comp.setForeground(BLANCO);
        comp.setFont(new Font("Arial", Font.PLAIN, 13));
        comp.setBorder(BorderFactory.createLineBorder(new Color(65, 65, 65)));
    }
}