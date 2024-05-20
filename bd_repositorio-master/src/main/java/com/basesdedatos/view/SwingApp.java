package com.basesdedatos.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.basesdedatos.model.Pasajeros;
import com.basesdedatos.model.Rutas;
import com.basesdedatos.repository.PasajerosRepository;
import com.basesdedatos.repository.RutasRepository;
import com.basesdedatos.repository.HorariosRepository;

public class SwingApp extends JFrame {
    private final PasajerosRepository pasajerosRepository;
    private final RutasRepository rutasRepository;
    private final HorariosRepository horariosRepository;
    private final JTable resultTable;

    public SwingApp() {
        setTitle("Gestión Transporte Urbano");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        resultTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));

        addButton(buttonPanel, "Listar Horarios por Ruta", this::listarHorariosPorRuta);
        addButton(buttonPanel, "Buscar por Tipo de Sangre", this::buscarPorTipoDeSangre);
        addButton(buttonPanel, "Mostrar Rutas (Punto de Partida y Destino)", this::mostrarRutas);
        addButton(buttonPanel, "Contar Pasajeros en Ruta", this::contarPasajerosEnRuta);
        addButton(buttonPanel, "Listar Pasajeros", this::listarPasajeros);
        addButton(buttonPanel, "Actualizar Teléfono Pasajero", this::actualizarTelefonoPasajero);
        addButton(buttonPanel, "Eliminar Ruta", this::eliminarRuta);
        addButton(buttonPanel, "Insertar Ruta", this::insertarRuta);
        addButton(buttonPanel, "Buscar Rutas por Punto de Partida", this::buscarRutasPorPuntoDePartida);
        addButton(buttonPanel, "Consultar Horarios Ordenados por Fecha", this::consultarHorariosOrdenadosPorFecha);

        add(buttonPanel, BorderLayout.WEST);

        pasajerosRepository = new PasajerosRepository();
        rutasRepository = new RutasRepository();
        horariosRepository = new HorariosRepository();
    }

    private void addButton(JPanel panel, String title, Runnable action) {
        JButton button = new JButton(title);
        button.addActionListener(e -> action.run());
        panel.add(button);
    }

    private void listarHorariosPorRuta() {
        String horario = JOptionPane.showInputDialog(this, "Ingrese el horario:");
        if (horario != null) {
            try {
                List<Object[]> rutas = rutasRepository.findByHorario(Integer.parseInt(horario));
                mostrarResultados(new String[]{"ID Ruta", "Punto de Partida", "Destino", "Horario", "Trayectoria", "Cantidad de Pasajeros"}, rutas);
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    private void buscarPorTipoDeSangre() {
        String tipoSangre = JOptionPane.showInputDialog(this, "Ingrese el Tipo de Sangre:");
        if (tipoSangre != null) {
            try {
                List<Pasajeros> pasajeros = pasajerosRepository.findByTipoSangre(tipoSangre);
                mostrarResultados(new String[]{"ID", "Nombre", "Apellido", "Número Teléfono", "Tipo de Sangre"},
                        pasajeros.stream().map(p -> new Object[]{p.getPasajeros_id(), p.getNombre(), p.getApellidos(), p.getNumeroTelefono(), p.getTipoSangre()}).toList());
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    private void mostrarRutas() {
        try {
            List<Rutas> rutasList = rutasRepository.findAllRutas();
            List<Object[]> rutas = rutasList.stream()
                .map(r -> new Object[]{r.getId_rutas(), r.getPunto_partida(), r.getDestino()})
                .toList();
            mostrarResultados(new String[]{"ID Ruta", "Punto de Partida", "Destino"}, rutas);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    private void contarPasajerosEnRuta() {
        String destino = JOptionPane.showInputDialog(this, "Ingrese el Destino:");
        String puntoPartida = JOptionPane.showInputDialog(this, "Ingrese el Punto de Partida:");
        if (destino != null && puntoPartida != null) {
            try {
                int count = rutasRepository.countPasajerosEnRuta(puntoPartida, destino);
                JOptionPane.showMessageDialog(this, "Cantidad de pasajeros: " + count);
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    private void listarPasajeros() {
        try {
            List<Pasajeros> pasajeros = pasajerosRepository.findAll();
            mostrarResultados(new String[]{"ID", "Nombre", "Apellido", "Número Teléfono", "Tipo de Sangre"},
                    pasajeros.stream().map(p -> new Object[]{p.getPasajeros_id(), p.getNombre(), p.getApellidos(), p.getNumeroTelefono(), p.getTipoSangre()}).toList());
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    private void actualizarTelefonoPasajero() {
        String id = JOptionPane.showInputDialog(this, "Ingrese el ID del Pasajero:");
        String nuevoTelefono = JOptionPane.showInputDialog(this, "Ingrese el nuevo número de teléfono:");
        if (id != null && nuevoTelefono != null) {
            try {
                pasajerosRepository.updateTelefono(Integer.parseInt(id), nuevoTelefono);
                JOptionPane.showMessageDialog(this, "Número de teléfono actualizado correctamente");
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    private void eliminarRuta() {
        String idRuta = JOptionPane.showInputDialog(this, "Ingrese el ID de la Ruta:");
        if (idRuta != null) {
            try {
                rutasRepository.delete(Integer.parseInt(idRuta));
                JOptionPane.showMessageDialog(this, "Ruta eliminada correctamente");
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    private void insertarRuta() {
        JTextField idField = new JTextField();
        JTextField puntoPartidaField = new JTextField();
        JTextField destinoField = new JTextField();
        JTextField horarioField = new JTextField();
        JTextField trayectoriaField = new JTextField();
        JTextField cantidadPasajerosField = new JTextField();

        Object[] message = {
                "ID Ruta:", idField,
                "Punto de Partida:", puntoPartidaField,
                "Destino:", destinoField,
                "Horario:", horarioField,
                "Trayectoria:", trayectoriaField,
                "Cantidad de Pasajeros:", cantidadPasajerosField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Insertar Nueva Ruta", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                rutasRepository.insert(new Rutas(
                        Integer.parseInt(idField.getText()),
                        puntoPartidaField.getText(),
                        destinoField.getText(),
                        Integer.parseInt(horarioField.getText()),
                        trayectoriaField.getText(),
                        Integer.parseInt(cantidadPasajerosField.getText())
                ));
                JOptionPane.showMessageDialog(this, "Ruta insertada correctamente");
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    private void buscarRutasPorPuntoDePartida() {
        String puntoPartida = JOptionPane.showInputDialog(this, "Ingrese el Punto de Partida:");
        if (puntoPartida != null) {
            try {
                List<Object[]> rutas = rutasRepository.findByPuntoDePartida(puntoPartida);
                mostrarResultados(new String[]{"ID Ruta", "Punto de Partida", "Destino", "Horario", "Trayectoria", "Cantidad de Pasajeros"}, rutas);
            } catch (Exception e) {
                mostrarError(e);
            }
        }
    }

    private void consultarHorariosOrdenadosPorFecha() {
        try {
            List<Object[]> horarios = horariosRepository.getHorariosOrdenadosPorFecha();
            mostrarResultados(new String[]{"ID Horario", "Fecha", "Hora de Salida", "Hora de Llegada"}, horarios);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    private void mostrarResultados(String[] columnNames, List<Object[]> data) {
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
        resultTable.setModel(tableModel);
    }

    private void mostrarError(Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingApp app = new SwingApp();
            app.setVisible(true);
        });
    }
}