/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.hechoHistorico.modeloTabla;

import controlador.grafo.GrafoNoDirijidoEtiquetado;
import javax.swing.table.AbstractTableModel;
import modelo.HechoHistorico;

/**
 *
 * @author Usuario
 */
public class ModeloTablaAdyacencia extends AbstractTableModel{
    private GrafoNoDirijidoEtiquetado<HechoHistorico> gnde;
    private String[] columnas;

    public GrafoNoDirijidoEtiquetado<HechoHistorico> getGnde() {
        return gnde;
    }

    public void setGnde(GrafoNoDirijidoEtiquetado<HechoHistorico> gnde) {
        this.gnde = gnde;
        generarColumnas();
    }

    public String[] getColumnas() {
        return columnas;
    }

    public void setColumnas(String[] columnas) {
        this.columnas = columnas;
    }
   
    @Override
    public int getColumnCount() {
        return gnde.getNumVertices() + 1;
    }
    @Override
    public int getRowCount() {
        return gnde.getNumVertices();
    }

    @Override
    public Object getValueAt(int i, int i1) {
         if(i1 == 0) {
            return columnas[i + 1];
        } else {
            try {
                if(gnde.existeArista((i+1), i1)) {
                    return gnde.pesoArista((i+1), i1);
                } else {
                    return "--";
                }
            } catch (Exception e) {
                System.out.println("Error en ver arista");
            }
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
       return columnas[column]; 

    }
    
      private String[] generarColumnas() {
        columnas = new String[gnde.numVertices() + 1];
        columnas[0] = "--V--";
        for(int i = 1; i < columnas.length;i++) {
            columnas[i] = gnde.obtenerEtiqueta(i).toString();
        }
        return columnas;
    }

}
