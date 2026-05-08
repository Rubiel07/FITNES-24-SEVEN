/*
 * VentanaPrincipal – FITNESS 24 SEVEN
 * Registra: Nombre, Apellido, Edad, Meses 
 */
package fitness24seven;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.awt.Desktop;
import java.net.URI;

public class VentanaPrincipal extends JFrame {

    private static final Color NEGRO    = new Color(20, 20, 20);
    private static final Color AMARILLO = new Color(245, 200, 0);
    private static final Color GRIS     = new Color(45, 45, 45);
    private static final Color GRIS2    = new Color(35, 35, 35);
    private static final Color BLANCO   = new Color(230, 230, 230);
    private static final Color ROJO     = new Color(220, 60, 60);

    private JTextField    txtNombre, txtApellido, txtEdad, txtMeses;
    private JButton       btnRegistrar, btnBorrar, btnYoutube, btnWeb;
    private JTable        tabla;
    private DefaultTableModel modelo;
    private HashMap<String, Integer> miembros;
    private final int precioMes = 89000;

    //labels de error debajo de cada campo
    private JLabel lblErrNombre, lblErrApellido, lblErrEdad, lblErrMeses;

    public VentanaPrincipal() {
        setTitle("FITNESS 24 SEVEN - Registro de Membresias");
        setSize(650, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(NEGRO);

        miembros = new HashMap<>();

        //titulo
        JLabel lblTitulo = new JLabel("FITNESS 24-SEVEN", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 16));
        lblTitulo.setForeground(AMARILLO);
        lblTitulo.setBounds(0, 8, 650, 24);
        add(lblTitulo);

        JLabel lblSub = new JLabel("Registro de Membresias  -  $89.000 / mes", SwingConstants.CENTER);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSub.setForeground(new Color(140, 140, 140));
        lblSub.setBounds(0, 32, 650, 16);
        add(lblSub);

        //separador amarillo
        JSeparator sep = new JSeparator();
        sep.setForeground(AMARILLO);
        sep.setBackground(AMARILLO);
        sep.setBounds(20, 52, 610, 2);
        add(sep);

        //nombre
        JLabel lNombre = crearLabel("Nombre:");
        lNombre.setBounds(20, 60, 80, 20);
        add(lNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(20, 80, 130, 26);
        estilizarInput(txtNombre);
        add(txtNombre);

        lblErrNombre = crearLabelError();
        lblErrNombre.setBounds(20, 107, 145, 14);
        add(lblErrNombre);

        //apellido
        JLabel lApellido = crearLabel("Apellido:");
        lApellido.setBounds(165, 60, 80, 20);
        add(lApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(165, 80, 130, 26);
        estilizarInput(txtApellido);
        add(txtApellido);

        lblErrApellido = crearLabelError();
        lblErrApellido.setBounds(165, 107, 145, 14);
        add(lblErrApellido);

        //edad
        JLabel lEdad = crearLabel("Edad:");
        lEdad.setBounds(310, 60, 60, 20);
        add(lEdad);

        txtEdad = new JTextField();
        txtEdad.setBounds(310, 80, 70, 26);
        estilizarInput(txtEdad);
        add(txtEdad);

        lblErrEdad = crearLabelError();
        lblErrEdad.setBounds(310, 107, 90, 14);
        add(lblErrEdad);

        //meses
        JLabel lMeses = crearLabel("Meses:");
        lMeses.setBounds(395, 60, 70, 20);
        add(lMeses);

        txtMeses = new JTextField();
        txtMeses.setBounds(395, 80, 70, 26);
        estilizarInput(txtMeses);
        add(txtMeses);

        lblErrMeses = crearLabelError();
        lblErrMeses.setBounds(395, 107, 90, 14);
        add(lblErrMeses);

        //botones de accion 
        btnRegistrar = crearBoton("Registrar", AMARILLO, NEGRO);
        btnRegistrar.setBounds(485, 65, 100, 26);
        add(btnRegistrar);

        btnBorrar = crearBoton("Limpiar", GRIS, BLANCO);
        btnBorrar.setBounds(485, 95, 100, 22);
        add(btnBorrar);

        //botones de webs
        btnYoutube = crearBoton("YouTube", new Color(160, 0, 0), BLANCO);
        btnYoutube.setBounds(20, 128, 100, 22);
        add(btnYoutube);

        btnWeb = crearBoton("Sitio Web", new Color(30, 90, 180), BLANCO);
        btnWeb.setBounds(130, 128, 100, 22);
        add(btnWeb);

        //tabla
        modelo = new DefaultTableModel(
            new Object[]{"Nombre", "Apellido", "Edad", "Meses", "Total ($)"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tabla = new JTable(modelo);
        tabla.setBackground(GRIS2);
        tabla.setForeground(BLANCO);
        tabla.setFont(new Font("Arial", Font.PLAIN, 12));
        tabla.setRowHeight(24);
        tabla.setGridColor(new Color(55, 55, 55));
        tabla.setSelectionBackground(new Color(60, 50, 0));
        tabla.setSelectionForeground(AMARILLO);

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(NEGRO);
        header.setForeground(AMARILLO);
        header.setFont(new Font("Arial Black", Font.BOLD, 11));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 160, 610, 240);
        scroll.getViewport().setBackground(GRIS2);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60)));
        add(scroll);

        //informacion del precio
        JLabel lblPrecio = new JLabel("* Precio mensual real: $89.000 COP (Plan mes a mes Fitness 24 Seven)");
        lblPrecio.setFont(new Font("Arial", Font.ITALIC, 10));
        lblPrecio.setForeground(new Color(120, 120, 120));
        lblPrecio.setBounds(20, 408, 500, 18);
        add(lblPrecio);

        //escuchadores
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarErrores();

                String nombre   = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                String edadStr  = txtEdad.getText().trim();
                String mesesStr = txtMeses.getText().trim();
                boolean hayError = false;
                int edad = 0, meses = 0;

                //para nombre vacio
                if (nombre.isEmpty()) {
                    mostrarError(lblErrNombre, txtNombre, "Campo obligatorio.");
                    hayError = true;
                //para nombre solo letras y espacios
                } else if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {//->la ia me ayudo con esto ([a-zA-Z0-9]+)
                    mostrarError(lblErrNombre, txtNombre, "Solo letras.");
                    hayError = true;
                //para nombre muy corto
                } else if (nombre.length() < 2) {
                    mostrarError(lblErrNombre, txtNombre, "Minimo 2 letras.");
                    hayError = true;
                }

                //para apellido vacio
                if (apellido.isEmpty()) {
                    mostrarError(lblErrApellido, txtApellido, "Campo obligatorio.");
                    hayError = true;
                //para apellido solo letras
                } else if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                    mostrarError(lblErrApellido, txtApellido, "Solo letras.");
                    hayError = true;
                //para apellido muy corto
                } else if (apellido.length() < 2) {
                    mostrarError(lblErrApellido, txtApellido, "Minimo 2 letras.");
                    hayError = true;
                }

                //para edad vacia
                if (edadStr.isEmpty()) {
                    mostrarError(lblErrEdad, txtEdad, "Obligatorio.");
                    hayError = true;
                } else {
                    try {
                        edad = Integer.parseInt(edadStr);
                        //para que edad no sea negativa
                        if (edad < 14 || edad > 100) {
                            mostrarError(lblErrEdad, txtEdad, "Entre 14 y 100.");
                            hayError = true;
                        }
                    } catch (NumberFormatException ex) {
                        //para edad solo numeros enteros
                        mostrarError(lblErrEdad, txtEdad, "Solo numeros.");
                        hayError = true;
                    }
                }

                //para meses vacios
                if (mesesStr.isEmpty()) {
                    mostrarError(lblErrMeses, txtMeses, "Obligatorio.");
                    hayError = true;
                } else {
                    try {
                        meses = Integer.parseInt(mesesStr);
                        //para mes minimo
                        if (meses < 1) {
                            mostrarError(lblErrMeses, txtMeses, "Minimo 1 mes.");
                            hayError = true;
                        //para mes maximo
                        } else if (meses > 24) {
                            mostrarError(lblErrMeses, txtMeses, "Maximo 24 meses.");
                            hayError = true;
                        }
                    } catch (NumberFormatException ex) {
                        //para mes solo numero entero
                        mostrarError(lblErrMeses, txtMeses, "Solo numeros.");
                        hayError = true;
                    }
                }

                if (hayError) return;

                //para miembro ya registrado: preguntar si actualiza mes
                String clave = nombre + " " + apellido;
                if (miembros.containsKey(clave)) {
                    int opcion = JOptionPane.showConfirmDialog(null,
                        nombre + " " + apellido + " ya esta registrado.\n"
                        + "¿Desea agregar " + meses + " mes(es) adicional(es)?",
                        "Miembro existente", JOptionPane.YES_NO_OPTION);
                    if (opcion != JOptionPane.YES_OPTION) return;
                }

                //calculo y registro
                int totalMeses = miembros.getOrDefault(clave, 0) + meses;
                miembros.put(clave, totalMeses);
                int totalPagar = totalMeses * precioMes;

                actualizarTabla(nombre, apellido, edad, totalMeses, totalPagar);
                JOptionPane.showMessageDialog(null,
                    "Registro exitoso.\nTotal acumulado: $" 
                    + String.format("%,d", totalPagar) + " COP");
            }
        });

        //limpiar campos al presionar limpiar
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNombre.setText("");
                txtApellido.setText("");
                txtEdad.setText("");
                txtMeses.setText("");
                limpiarErrores();
                txtNombre.requestFocus();
            }
        });

        //limpiar errores al escribir en cada campo
        txtNombre.addKeyListener(new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) {
                limpiarCampo(lblErrNombre, txtNombre);
            }
        });
        txtApellido.addKeyListener(new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) {
                limpiarCampo(lblErrApellido, txtApellido);
            }
        });
        txtEdad.addKeyListener(new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) {
                limpiarCampo(lblErrEdad, txtEdad);
            }
        });
        txtMeses.addKeyListener(new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) {
                limpiarCampo(lblErrMeses, txtMeses);
            }
        });

        btnYoutube.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(
                        new URI("https://www.youtube.com/@fitness24sevencolombia92"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "No se pudo abrir YouTube", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnWeb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://co.fitness24seven.com"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "No se pudo abrir el sitio web", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    //metodos de respaldo o ayudantes

    private void actualizarTabla(String nombre, String apellido,int edad,int meses, int total) {
        String totalFmt = "$" + String.format("%,d", total);
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 0).equals(nombre)
             && modelo.getValueAt(i, 1).equals(apellido)) {
                modelo.setValueAt(meses,    i, 3);
                modelo.setValueAt(totalFmt, i, 4);
                return;
            }
        }
        modelo.addRow(new Object[]{nombre, apellido, edad, meses, totalFmt});
    }

    private void mostrarError(JLabel lbl, JTextField campo, String msg) {
        lbl.setText(msg);
        campo.setBorder(BorderFactory.createLineBorder(ROJO, 1));
        campo.requestFocus();
    }

    private void limpiarCampo(JLabel lbl, JTextField campo) {
        lbl.setText("");
        campo.setBorder(BorderFactory.createLineBorder(new Color(65, 65, 65)));
    }

    private void limpiarErrores() {
        limpiarCampo(lblErrNombre,   txtNombre);
        limpiarCampo(lblErrApellido, txtApellido);
        limpiarCampo(lblErrEdad,     txtEdad);
        limpiarCampo(lblErrMeses,    txtMeses);
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        lbl.setForeground(AMARILLO);
        return lbl;
    }

    private JLabel crearLabelError() {
        JLabel lbl = new JLabel("");
        lbl.setFont(new Font("Arial", Font.PLAIN, 10));
        lbl.setForeground(ROJO);
        return lbl;
    }

    private void estilizarInput(JTextField tf) {
        tf.setBackground(GRIS);
        tf.setForeground(BLANCO);
        tf.setFont(new Font("Arial", Font.PLAIN, 13));
        tf.setCaretColor(AMARILLO);
        tf.setBorder(BorderFactory.createLineBorder(new Color(65, 65, 65)));
    }

    private JButton crearBoton(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Arial Black", Font.BOLD, 11));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}