/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo;

import controlador.grafo.exception.VerticeOfSizeException;

/**
 *
 * @author sebastian
 */
public class GrafoNoDirijidoEtiquetado<E> extends GrafoDirigidoEtiquetado<E>{

    public GrafoNoDirijidoEtiquetado(Integer numVer, Class clazz) {
        super(numVer, clazz);
    }

     @Override
    public void insertarArista(Integer o, Integer d, Double peso) throws Exception {
        if(o.intValue() <= getNumVertices() && d.intValue() <= getNumVertices()) {
                if(!existeArista(o, d)) {
                    setNumAristas(getNumAristas() + 1);
                    //numAristas++;
                    getListaAdycente()[o].insertar(new Adycencia(d, peso));
                    getListaAdycente()[d].insertar(new Adycencia(o, peso));
                }
            } else {
                throw new VerticeOfSizeException();
            }
    }
    
    
    
}
