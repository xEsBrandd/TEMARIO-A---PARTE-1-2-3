package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Dashboard {

    private final List<Widget> widgets = new ArrayList<>();

    public Dashboard() {
        init();
    }

    public void init() {
        widgets.clear();

        for (Widget widget : CatalogoInicial.crear()) {
            agregar(widget);
        }
    }

    public void agregar(Widget widget) {
        if (widget == null) {
            throw new IllegalArgumentException(
                "Widget obligatorio."
            );
        }

        if (buscarPorId(widget.getId()) != null) {
            throw new IllegalArgumentException(
                "Ya existe ese ID: " + widget.getId()
            );
        }

        widgets.add(widget);
    }

    public List<Widget> listar() {
        return Collections.unmodifiableList(
            new ArrayList<>(widgets)
        );
    }

    public Widget buscarPorId(String id) {
        if (id == null) {
            return null;
        }

        for (Widget widget : widgets) {
            if (widget.getId().equalsIgnoreCase(id.trim())) {
                return widget;
            }
        }

        return null;
    }

    public List<Widget> buscar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return listar();
        }

        String consulta = texto.trim().toLowerCase(Locale.ROOT);

        List<Widget> resultados = new ArrayList<>();

        for (Widget widget : widgets) {
            boolean coincideId =
                widget.getId().equalsIgnoreCase(consulta);

            boolean coincideNombre =
                widget.getNombre()
                    .toLowerCase(Locale.ROOT)
                    .contains(consulta);

            if (coincideId || coincideNombre) {
                resultados.add(widget);
            }
        }

        return resultados;
    }

    public String interactuar(String id) {
        Widget widget = buscarPorId(id);

        if (widget == null) {
            return "No existe un widget con ese ID.";
        }

        if (!(widget instanceof Interactivo)) {
            return "Este widget es informativo; no admite interacción.";
        }

        Interactivo interactivo = (Interactivo) widget;
        interactivo.interactuar();

        return widget.getNombre()
            + "\n"
            + widget.renderizar();
    }

    public void ordenarPorMemoria() {
        Collections.sort(widgets);
    }
}