package com.basesdedatos;

import javax.swing.SwingUtilities;

import com.basesdedatos.view.SwingApp;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingApp app = new SwingApp();
            app.setVisible(true);
        });
    }
}