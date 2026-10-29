package modelo;

import java.awt.BorderLayout;
import javax.swing.*;

public class QuoteWidget extends WidgetBase {

    private final String frase;
    private JPanel panel;

    public QuoteWidget(String id, String nombre, int memoriaKB,
                       String frase) {
        super(id, nombre, memoriaKB);

        this.frase = validarTexto(frase, "Frase");
    }

    @Override
    public String renderizar() {
        return frase;
    }

    @Override
    public JComponent obtenerVista() {
        if (panel == null) {
            panel = crearPanel();

            JTextArea texto = new JTextArea(frase);
            texto.setEditable(false);
            texto.setLineWrap(true);
            texto.setWrapStyleWord(true);
            texto.setOpaque(false);

            panel.add(
                new JScrollPane(texto),
                BorderLayout.CENTER
            );
        }

        return panel;
    }
}