package modelo;

import java.awt.BorderLayout;
import java.util.Objects;
import javax.swing.*;

public class AlertWidget extends WidgetBase implements Interactivo {

    private final String mensaje;
    private final Prioridad prioridad;
    private boolean leida;

    private JPanel panel;
    private JTextArea textoAlerta;
    private JButton boton;

    public AlertWidget(String id, String nombre, int memoriaKB,
                       String mensaje, Prioridad prioridad) {
        super(id, nombre, memoriaKB);

        this.mensaje = validarTexto(mensaje, "Mensaje");

        this.prioridad = Objects.requireNonNull(
            prioridad,
            "Prioridad obligatoria."
        );

        leida = false;
    }

    @Override
    public String renderizar() {
        return "Mensaje: " + mensaje
            + "\nPrioridad: " + prioridad
            + "\nEstado: " + (leida ? "Leída" : "Pendiente");
    }

    @Override
    public void interactuar() {
        leida = true;

        if (textoAlerta != null) {
            textoAlerta.setText(renderizar());
            boton.setEnabled(false);
        }
    }

    @Override
    public JComponent obtenerVista() {
        if (panel == null) {
            panel = crearPanel();

            textoAlerta = new JTextArea(renderizar());
            textoAlerta.setEditable(false);
            textoAlerta.setLineWrap(true);
            textoAlerta.setWrapStyleWord(true);

            boton = new JButton("Marcar como leída");
            boton.setEnabled(!leida);
            boton.addActionListener(evento -> interactuar());

            panel.add(
                new JScrollPane(textoAlerta),
                BorderLayout.CENTER
            );

            panel.add(boton, BorderLayout.SOUTH);
        }

        return panel;
    }
}