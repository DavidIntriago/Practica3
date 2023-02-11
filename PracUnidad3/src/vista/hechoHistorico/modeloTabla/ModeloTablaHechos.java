/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.hechoHistorico.modeloTabla;

import controlador.listas.ListaEnlazada;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import modelo.HechoHistorico;

/**
 *
 * @author Usuario
 */
public class ModeloTablaHechos extends AbstractTableModel{
    ListaEnlazada<HechoHistorico> listaHechos=new ListaEnlazada<>();

    public ListaEnlazada<HechoHistorico> getListaHechos() {
        return listaHechos;
    }

    public void setListaHechos(ListaEnlazada<HechoHistorico> listaHechos) {
        this.listaHechos = listaHechos;
    }
    
    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return listaHechos.getSize();
    }

    @Override
    public String getColumnName(int i) {
        
        switch(i) {
            case 0: return "ID";
            case 1: return "Nombre";
            case 2: return "Descripcion";
            case 3: return "Año";
           
            default: return null;
        }
    }

    @Override
    public Object getValueAt(int i, int i1) {
       
        try {
            HechoHistorico e= listaHechos.obtener(i);
            switch(i1) {
                case 0: return (e != null) ? e.getId_Hecho() : "NO DEFINIDO";
                case 1: return (e != null) ? e.getNombre(): "NO DEFINIDO";
                case 2: return (e != null) ? e.getDescripcion() : "NO DEFINIDO";
                case 3: return (e != null) ? e.getAnio_hecho().toString() : "NO DEFINIDO";
                default: return null;
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }
 
  
}
