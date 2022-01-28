/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ajedrez.Piezas;

import javax.swing.ImageIcon;

/**
 *
 * @author balaz
 */
public interface IConstantes {
    public final String[] PIEZAS = {"Peon.png","Torre.png","Caballo.png","Alfil.png","Reina.png","Rey.png"};
    public final String RUTA_BLANCAS = "Imagenes/Piezas Blancas/";
    public final String RUTA_NEGRAS = "Imagenes/Piezas Negras/"; 
    public final ImageIcon SELECCION = new ImageIcon("Imagenes/Seleccion.png"); 
}
