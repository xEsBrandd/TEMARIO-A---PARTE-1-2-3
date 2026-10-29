package modelo;

public class Tarea {

    private final String descripcion;
    private boolean completada;

    public Tarea(String descripcion) {
        this.descripcion = WidgetBase.validarTexto(
            descripcion,
            "Descripción"
        );

        completada = false;
    }

    public boolean estaCompletada() {
        return completada;
    }

    public void completar() {
        completada = true;
    }

    @Override
    public String toString() {
        return (completada ? "[X] " : "[ ] ") + descripcion;
    }
}