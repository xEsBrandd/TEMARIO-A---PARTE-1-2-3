package Controlador;

import modelo.Dashboard;
import Vista.DashboardView;

public class DashboardController {

    private final Dashboard modelo;
    private final DashboardView vista;

    private String filtro = "";

    public DashboardController(Dashboard modelo,
                               DashboardView vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.alListar(evento -> {
            filtro = "";
            vista.limpiarBusqueda();
            actualizarVista();
        });

        vista.alBuscar(evento -> {
            filtro = vista.getBusqueda();
            actualizarVista();
        });

        vista.alOrdenar(evento -> {
            modelo.ordenarPorMemoria();
            actualizarVista();
        });

        vista.alInteractuar(evento -> {
            String id = vista.pedirId();

            if (id == null) {
                return;
            }

            if (id.trim().isEmpty()) {
                vista.informar("Debe ingresar un ID.");
                return;
            }

            String resultado = modelo.interactuar(id);

            actualizarVista();
            vista.informar(resultado);
        });
    }

    private void actualizarVista() {
        vista.mostrarWidgets(
            modelo.buscar(filtro)
        );
    }

    public void iniciar() {
        actualizarVista();
        vista.setVisible(true);
    }
}