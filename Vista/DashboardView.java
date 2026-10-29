package Vista;

import modelo.Widget;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class DashboardView extends JFrame {

    private final JPanel contenedor =
        new JPanel(new GridLayout(0, 2, 12, 12));

    private final JTextField busqueda = new JTextField(20);

    private final JButton listar = new JButton("Listar todos");
    private final JButton buscar = new JButton("Buscar");

    private final JButton interactuar =
        new JButton("Interactuar por ID");

    private final JButton ordenar =
        new JButton("Ordenar por memoria");

    private final JLabel estado = new JLabel(" ");

    public DashboardView() {
        super("Dashboard modular — Ejercicio 6");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1080, 760);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Dashboard de widgets");

        titulo.setFont(
            titulo.getFont().deriveFont(Font.BOLD, 25f)
        );

        JPanel barra = new JPanel(
            new FlowLayout(FlowLayout.LEFT)
        );

        barra.add(listar);
        barra.add(new JLabel("ID o nombre:"));
        barra.add(busqueda);
        barra.add(buscar);
        barra.add(interactuar);
        barra.add(ordenar);

        JPanel superior = new JPanel(
            new BorderLayout(0, 10)
        );

        superior.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 10, 15)
        );

        superior.add(titulo, BorderLayout.NORTH);
        superior.add(barra, BorderLayout.CENTER);

        contenedor.setBorder(
            BorderFactory.createEmptyBorder(12, 12, 12, 12)
        );

        JPanel area = new JPanel(new BorderLayout());
        area.add(contenedor, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(area);
        scroll.getVerticalScrollBar().setUnitIncrement(18);

        estado.setBorder(
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        );

        add(superior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(estado, BorderLayout.SOUTH);
    }

    public String getBusqueda() {
        return busqueda.getText().trim();
    }

    public void limpiarBusqueda() {
        busqueda.setText("");
    }

    public void alListar(ActionListener accion) {
        listar.addActionListener(accion);
    }

    public void alBuscar(ActionListener accion) {
        buscar.addActionListener(accion);
        busqueda.addActionListener(accion);
    }

    public void alInteractuar(ActionListener accion) {
        interactuar.addActionListener(accion);
    }

    public void alOrdenar(ActionListener accion) {
        ordenar.addActionListener(accion);
    }

    public String pedirId() {
        return JOptionPane.showInputDialog(
            this,
            "Ingrese el ID del widget:"
        );
    }

    public void informar(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarWidgets(List<Widget> widgets) {
        contenedor.removeAll();

        for (Widget widget : widgets) {
            contenedor.add(widget.obtenerVista());
        }

        if (widgets.isEmpty()) {
            contenedor.add(
                new JLabel("No se encontraron widgets.")
            );
        }

        long memoria = 0;

        for (Widget widget : widgets) {
            memoria += widget.getMemoriaKB();
        }

        estado.setText(
            "Widgets mostrados: " + widgets.size()
            + " | Memoria estimada de la selección: "
            + memoria + " KB"
        );

        contenedor.revalidate();
        contenedor.repaint();
    }
}