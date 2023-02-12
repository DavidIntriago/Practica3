/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pracunidad3;

import controlador.grafo.GrafoNoDirijidoEtiquetado;

/**
 *
 * @author Usuario
 */
public class PracUnidad3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GrafoNoDirijidoEtiquetado gde = new GrafoNoDirijidoEtiquetado(5, String.class);
        gde.etiquetarVertice(1, "Mayuri");//
        gde.etiquetarVertice(2, "Alice");
        gde.etiquetarVertice(3, "Vanessa");
        gde.etiquetarVertice(4, "Letty");//
        gde.etiquetarVertice(5, "Cobos");
        try {
            gde.insertarAristaE(gde.obtenerEtiqueta(5), gde.obtenerEtiqueta(2), 10.0);
            gde.insertarAristaE(gde.obtenerEtiqueta(1), gde.obtenerEtiqueta(2), 25.0);
            
            gde.insertarAristaE(gde.obtenerEtiqueta(3), gde.obtenerEtiqueta(5), 1.0);
            gde.insertarAristaE(gde.obtenerEtiqueta(3), gde.obtenerEtiqueta(4), -10.0);
            gde.insertarAristaE(gde.obtenerEtiqueta(2), gde.obtenerEtiqueta(3), 55.0);
            gde.insertarAristaE(gde.obtenerEtiqueta(1), gde.obtenerEtiqueta(4), 1000.0);
            //System.out.println(gde.caminiMinimo(1, 4));
          //  gde.caminiMinimo(5, 4).imprimir();
          //  gde.algoritmoDijkstra(5).imprimir();
//           gde.busquedaAnchura(5);
//            System.out.println("----------------");
//            gde.busquedaProfundidad(5);
            //System.out.println(gde.algoritmoFloyd(0, 0));
            //System.out.println(gde.toString());
            //new UbicacionController().listar().imprimir();
        } catch (Exception e) {
        }
    }
    }
    

