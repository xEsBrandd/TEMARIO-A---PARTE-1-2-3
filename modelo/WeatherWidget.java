package modelo;

import java.awt.BorderLayout;
import java.util.Locale;
import javax.swing.*;

public class WeatherWidget extends WidgetBase implements Interactivo {

    private final String ciudad;
    private final double temperaturaC;
    private boolean mostrarCelsius = true;

    private JPanel panel;
    private JLabel etiqueta;

    public WeatherWidget(String id, String nombre, int memoriaKB,
                         String ciudad, double temperaturaC) {
        super(id, nombre, memoriaKB);

        this.ciudad = validarTexto(ciudad, "Ciudad");

        if (!Double.isFinite(temperaturaC)) {
            throw new IllegalArgumentException(
                "Temperatura inválida."
            );
        }

        this.temperaturaC = temperaturaC;
    }

    @Override
    public String renderizar() {
        double temperatura = mostrarCelsius
            ? temperaturaC
            : temperaturaC * 9.0 / 5.0 + 32;

        String unidad = mostrarCelsius ? "°C" : "°F";

        return String.format(
            Locale.ROOT,
            "%s: %.1f %s",
            ciudad,
            temperatura,
            unidad
        );
    }

    @Override
    public void interactuar() {
        mostrarCelsius = !mostrarCelsius;

        if (etiqueta != null) {
            etiqueta.setText(renderizar());
        }
    }

    @Override
    public JComponent obtenerVista() {
        if (panel == null) {
            panel = crearPanel();

            etiqueta = new JLabel(
                renderizar(),
                SwingConstants.CENTER
            );

            JButton boton = new JButton("Cambiar unidad °C / °F");
            boton.addActionListener(evento -> interactuar());

            panel.add(etiqueta, BorderLayout.CENTER);
            panel.add(boton, BorderLayout.SOUTH);
        }

        return panel;
    }
}