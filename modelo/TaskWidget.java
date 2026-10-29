package modelo;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

public class TaskWidget extends WidgetBase implements Interactivo {

    private final List<Tarea> tareas = new ArrayList<>();

    private JPanel panel;
    private JTextArea textoTareas;
    private JButton boton;

    public TaskWidget(String id, String nombre, int memoriaKB,
                      String... descripciones) {
        super(id, nombre, memoriaKB);

        Objects.requireNonNull(
            descripciones,
            "Lista de tareas obligatoria."
        );

        for (String descripcion : descripciones) {
            tareas.add(new Tarea(descripcion));
        }
    }

    @Override
    public String renderizar() {
        if (tareas.isEmpty()) {
            return "Sin tareas.";
        }

        StringBuilder texto = new StringBuilder();

        for (Tarea tarea : tareas) {
            texto.append(tarea).append("\n");
        }

        return texto.toString().trim();
    }

    private boolean hayPendientes() {
        for (Tarea tarea : tareas) {
            if (!tarea.estaCompletada()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void interactuar() {
        for (Tarea tarea : tareas) {
            if (!tarea.estaCompletada()) {
                tarea.completar();
                break;
            }
        }

        if (textoTareas != null) {
            textoTareas.setText(renderizar());
            boton.setEnabled(hayPendientes());
        }
    }

    @Override
    public JComponent obtenerVista() {
        if (panel == null) {
            panel = crearPanel();

            textoTareas = new JTextArea(renderizar());
            textoTareas.setEditable(false);
            textoTareas.setLineWrap(true);
            textoTareas.setWrapStyleWord(true);

            boton = new JButton("Completar siguiente");
            boton.setEnabled(hayPendientes());
            boton.addActionListener(evento -> interactuar());

            panel.add(
                new JScrollPane(textoTareas),
                BorderLayout.CENTER
            );

            panel.add(boton, BorderLayout.SOUTH);
        }

        return panel;
    }
}