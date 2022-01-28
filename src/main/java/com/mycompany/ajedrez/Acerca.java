/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ajedrez;

import static com.mycompany.ajedrez.Piezas.IConstantes.RUTA_NEGRAS;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author balaz
 */
public final class Acerca extends JFrame{
    private JPanel panel;
    public Acerca(){
        this.setSize(450, 300);
        this.setTitle("Acerca de");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(RUTA_NEGRAS+"Peon.png").getImage());
        initComponents();
    }
    public void initComponents(){
        setPanel();
        setEtiqueta();
    }
    public void setPanel(){
        panel = new JPanel();
        panel.setSize(this.getSize());
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        this.add(panel);
    }
    public void setEtiqueta(){
        JLabel label = new JLabel();
        JLabel icono = new JLabel();
        icono.setIcon(new ImageIcon(RUTA_NEGRAS+"Peon.png"));
        icono.setBounds(190, 0, 92, 92);
        label.setBounds(75, 92, 300, 150);
        String cadena = "<html><center><p>Este es un juego de ajedrez fue creado por el estudiante "
                + "Francisco Joel Meza Velazquez estudiante de la ingenieria en tecnologias de la "
                + "informacion y comunicaciones desarrollado como proyecto de la materia de "
                + "organizacion y estructura de datos <p>"
                + "PARA INICIAR PARTIDA ESCOJA EL MODO EN LA ESQUINA SUPERIOR IZQUIERDA</html>";
        label.setText(cadena);
        panel.add(icono);
        panel.add(label);
    }
}
