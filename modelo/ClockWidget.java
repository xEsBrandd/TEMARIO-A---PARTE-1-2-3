package modelo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.HierarchyEvent;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class ClockWidget extends WidgetBase {

    private final ZoneId zona;

    private static final DateTimeFormatter FORMATO =
        DateTimeFormatter.ofPattern("HH:mm:ss");

    private JPanel panel;
    private JLabel etiqueta;
    private Timer temporizador;

    public ClockWidget(String id, String nombre, int memoriaKB,
                       String zonaHoraria) {
        super(id, nombre, memoriaKB);

        zona = ZoneId.of(
            validarTexto(zonaHoraria, "Zona horaria")
        );
    }

    @Override
    public String renderizar() {
        return "Hora: " + LocalTime.now(zona).format(FORMATO)
            + " | Zona: " + zona;
    }

    private void actualizarHora() {
        etiqueta.setText(
            LocalTime.now(zona).format(FORMATO)
        );
    }

    @Override
    public JComponent obtenerVista() {
        if (panel == null) {
            panel = crearPanel();

            etiqueta = new JLabel("", SwingConstants.CENTER);

            etiqueta.setFont(
                etiqueta.getFont().deriveFont(Font.BOLD, 32f)
            );

            actualizarHora();

            panel.add(etiqueta, BorderLayout.CENTER);

            panel.add(
                new JLabel(zona.toString(), SwingConstants.CENTER),
                BorderLayout.SOUTH
            );

            temporizador = new Timer(
                1000,
                evento -> actualizarHora()
            );

            panel.addHierarchyListener(evento -> {
                boolean cambioVisibilidad =
                    (evento.getChangeFlags()
                    & HierarchyEvent.SHOWING_CHANGED) != 0;

                if (cambioVisibilidad) {
                    if (panel.isShowing()) {
                        actualizarHora();
                        temporizador.start();
                    } else {
                        temporizador.stop();
                    }
                }
            });
        }

        return panel;
    }
}