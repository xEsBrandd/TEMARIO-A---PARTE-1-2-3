package modelo;

public interface Widget extends Renderizable, Comparable<Widget> {

    String getId();
    String getNombre();
    int getMemoriaKB();

    @Override
    default int compareTo(Widget otro) {
        return Integer.compare(
            getMemoriaKB(),
            otro.getMemoriaKB()
        );
    }
}