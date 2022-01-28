/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ajedrez;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

/**
 *
 * @author balaz
 */
public class EmpezarJuego implements ActionListener{
        private boolean empezo;
        private final JMenuItem jugador;
        private JButton[][] casillas;
        private final JLabel[][] piezasPanelIzq;
        private final JLabel[][] piezasPanelDer;
        private final JLabel turno;
        public EmpezarJuego(JMenuItem jugador, JButton[][] casillas, JLabel[][] piezasPanelIzq, JLabel[][] piezasPanelDer, JLabel turno){
            empezo = false;
            this.jugador = jugador;
            this.casillas = casillas;
            this.piezasPanelIzq = piezasPanelIzq;
            this.piezasPanelDer = piezasPanelDer;
            this.turno = turno;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Movimiento movimiento = new Movimiento();
                for(int i = 0; i<8; i++){
                    for(int j = 0; j<8; j++){
                        casillas[i][j].addActionListener(movimiento);
                        casillas[i][j].setEnabled(true);
                        empezo = true;
                        turno.setText("Turno: Blanco");
                        jugador.setEnabled(false);
                    }
                }
        }
        //--------------------- METODO QUE ASIGNA SI LA PARTIDA YA EMPEZO -----------------
        public void setEmpezo(boolean empezo){
            this.empezo = empezo;
        }
        //--------------------- METODO QUE DEVUELVE SI EMPEZO LA PARTIDA -----------------------
        public boolean getEmpezo(){
            return empezo;
        }
    //-------------------------- CLASE INTERNA ------------------------------------------------
    private class Movimiento implements ActionListener{
        private String[] basura, nameID; //-- Se guardara el nombre la casilla
        private int row, column;//-- se convertira en entero para saber cual casilla es la pulsada
        Color colorSeleccion;//-- Creo el color para cuando se selccione una pieza
        Pieza pieza;//-- Creo la varible donde guardare el objeto que tiene el mover las piezas
        boolean empezo;
        //--------------------- CONSTRUCTOR ---------------------------------------------------------
        public Movimiento(){
            colorSeleccion = new Color(135, 206, 250);
            pieza = new Pieza();//-- Creo el objeto -- tiene metodos para el movimiento segun la pieza
            empezo = false;
        }
        //------------------- METODO QUE SE EJECUTA AL HACER ACCION EN LA PIEZA ----------------------
        @Override
        public void actionPerformed(ActionEvent e) {   
            basura = e.toString().split(";");//-- toma el nombre del evento y lo divide para obtener la casilla
            nameID = basura[1].split(",");//-- Divide la fila y columna
            row = Integer.parseInt(nameID[0]);//-- Convierte la fila en entero
            column = Integer.parseInt(nameID[1]);//-- Convierte la columna en entero
            casillas = pieza.movimiento(casillas, row, column, piezasPanelIzq, piezasPanelDer);//-- Ejecuto el metodo de movimiento del objeto "pieza" en caso de no haber un posible movimiento no pasa nada
            if(casillas[row][column].getIcon() != null && !pieza.getConfirmacioMovimiento()){//-- Verifico si la casilla pulsada tiene pieza
                casillas = pieza.posibleMovimiento(casillas[row][column].getIcon().toString(), casillas, row, column);//-- Ejecuto el metodo que regresa posible movimientos segun la pieza y no haya algun movimiento para que no genere mas
            }
            if(pieza.getTurno()){
                turno.setText("Turno: Blanco");
            }else{
                turno.setText("Turno: Negro");
            }
        }
    }
}
