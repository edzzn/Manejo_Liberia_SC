package gui;

import lib.*;

import javax.swing.*;
import java.awt.event.*;

public class PrestamoModificar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtCodigo;
    private JTextField txtId;
    private JTextField txtIsbn;
    private JTextField txtFecha;
    private JTextField txtHora;
    String codigo;

    public PrestamoModificar(String codigo) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.codigo = codigo;
        updateDataDisplay();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void updateDataDisplay(){
        this.txtCodigo.setText(codigo);
        txtCodigo.setEditable(false);

        RegistroPrestamo reg_pre = new RegistroPrestamo(Util.loadD("p"));
        Prestamo prestamo = reg_pre.getPrestamo(codigo);

        this.txtFecha.setText(prestamo.fechaPrestamo());
        this.txtHora.setText(prestamo.horaPrestamo());
        this.txtId.setText(prestamo.estudiante().cedula());
        this.txtIsbn.setText(prestamo.libro().isbn());
    }

    private void onOK() {
        // add your code here
        String codigo = txtCodigo.getText();
        String id = txtId.getText();
        String isbn = txtIsbn.getText();
        String fecha = txtFecha.getText();
        String hora = txtHora.getText();

        if(!codigo.isEmpty() && !id.isEmpty() && !isbn.isEmpty() && !fecha.isEmpty() && !hora.isEmpty()){
            RegistroEstudiante reg_est = new RegistroEstudiante(Util.loadD("e"));
            RegistroLibro reg_lib = new RegistroLibro(Util.loadD("l"));
            RegistroPrestamo reg_pre = new RegistroPrestamo(Util.loadD("p"));

            Estudiante estudiante = reg_est.getEstudiante(id);
            Libro libro = reg_lib.getLibro(isbn);

            if(estudiante==null){
                WindowUtil.mjsAlerta("Error, estudiante no existe");

            }else if (libro == null){
                WindowUtil.mjsAlerta("Error, libro no existe");
            }else{
                reg_pre.getPrestamo(codigo).edit(estudiante, libro, fecha, hora);
                Util.saveD("p",reg_pre);
                WindowUtil.mjsAlerta("Prestamo <b>Modificado</b>");
                dispose();
            }
        }else{
            WindowUtil.mjsAlerta("Datos erroneos");
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        PrestamoModificar dialog = new PrestamoModificar("");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
