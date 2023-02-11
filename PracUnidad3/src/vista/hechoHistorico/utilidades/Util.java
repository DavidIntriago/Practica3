/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.hechoHistorico.utilidades;

import controlador.listas.ListaEnlazada;
import javax.swing.JComboBox;
import modelo.HechoHistorico;

/**
 *
 * @author Usuario
 */
public class Util {
    public static void cargarComboActividades(JComboBox cbx, ListaEnlazada<HechoHistorico> lista) throws Exception{
        cbx.removeAllItems();
            for(int i = 0;i < lista.getSize();i++) {
                cbx.addItem(lista.obtener(i).getId_Hecho());
            }
    }
    public static Double calcularDiferencia(Integer anio1, Integer anio2){
        Double diferencia;
        if (anio1>anio2) {
            diferencia=Double.valueOf(anio1.toString())-Double.valueOf(anio2.toString());
        }else{
            diferencia=Double.valueOf(anio2.toString())-Double.valueOf(anio1.toString());
        }
        return redondear(diferencia);
    }
    
    public static Double redondear(Double dato) {
        return Math.round(dato * 100.0)/100.0;
    }
}
