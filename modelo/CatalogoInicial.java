package modelo;

import java.util.ArrayList;
import java.util.List;

public final class CatalogoInicial {

    private CatalogoInicial() {
        // Constructor privado: esta clase solo ofrece métodos estáticos.
    }

    public static List<Widget> crear() {
        List<Widget> catalogo = new ArrayList<>();

        catalogo.add(new WeatherWidget(
            "W01",
            "Clima Guatemala",
            120,
            "Guatemala",
            24
        ));

        catalogo.add(new WeatherWidget(
            "W02",
            "Clima Quetzaltenango",
            110,
            "Quetzaltenango",
            17
        ));

        catalogo.add(new TaskWidget(
            "W03",
            "Tareas de oficina",
            90,
            "Preparar informe",
            "Revisar inventario"
        ));

        catalogo.add(new TaskWidget(
            "W04",
            "Tareas del proyecto",
            100,
            "Diseñar UML",
            "Implementar interfaces"
        ));

        catalogo.add(new AlertWidget(
            "W05",
            "Alerta de inventario",
            60,
            "Quedan pocas unidades del producto.",
            Prioridad.ALTA
        ));

        catalogo.add(new AlertWidget(
            "W06",
            "Alerta de reunión",
            55,
            "Reunión a las 10:00.",
            Prioridad.MEDIA
        ));

        catalogo.add(new ClockWidget(
            "W07",
            "Reloj Guatemala",
            35,
            "America/Guatemala"
        ));

        catalogo.add(new ClockWidget(
            "W08",
            "Reloj UTC",
            35,
            "UTC"
        ));

        catalogo.add(new QuoteWidget(
            "W09",
            "Frase de constancia",
            20,
            "Cada paso cuenta."
        ));

        catalogo.add(new QuoteWidget(
            "W10",
            "Frase de aprendizaje",
            25,
            "La práctica convierte las ideas en habilidades."
        ));

        return catalogo;
    }
}