package modelo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public abstract class WidgetBase implements Widget {

    private final String id;
    private final String nombre;
    private final int memoriaKB;

    protected WidgetBase(String id, String nombre, int memoriaKB) {
        this.id = validarTexto(id, "ID");
        this.nombre = validarTexto(nombre, "Nombre");

        if (memoriaKB < 0) {
            throw new IllegalArgumentException(
                "La memoria no puede ser negativa."
            );
        }

        this.memoriaKB = memoriaKB;
    }

    protected static String validarTexto(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(
                campo + " es obligatorio."
            );
        }

        return texto.trim();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int getMemoriaKB() {
        return memoriaKB;
    }

    protected JPanel crearPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        panel.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    nombre + " | " + id + " | " + memoriaKB + " KB"
                ),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
            )
        );

        panel.setPreferredSize(new Dimension(390, 210));

        return panel;
    }
}