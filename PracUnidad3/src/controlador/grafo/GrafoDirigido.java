/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo;

import controlador.grafo.exception.VerticeOfSizeException;
import controlador.listas.ListaEnlazada;

/**
 *
 * @author sebastian
 */
public class GrafoDirigido extends Grafo {

    private Integer numVertices;
    private Integer numAristas;
    private ListaEnlazada<Adycencia> listaAdycente[];

    public GrafoDirigido(Integer numVertices) {
        this.numVertices = numVertices;
        numAristas = 0;
        listaAdycente = new ListaEnlazada[numVertices + 1];
        for (int i = 1; i <= this.numVertices; i++) {
            listaAdycente[i] = new ListaEnlazada<>();
        }
    }

    @Override
    public Integer numVertices() {
        return numVertices;
    }

    @Override
    public Integer numAristas() {
        return numAristas;
    }

    @Override
    public Boolean existeArista(Integer o, Integer d) throws Exception {
        Boolean existe = false;
        if (o.intValue() <= numVertices && d.intValue() <= numVertices) {
            ListaEnlazada<Adycencia> lista = listaAdycente[o];
            for (int i = 0; i < lista.getSize(); i++) {
                Adycencia a = lista.obtener(i);
                if (a.getDestino().intValue() == d.intValue()) {
                    existe = true;
                    break;
                }
            }
        } else {
            //TODO Exception VerticeOfSize}    
            throw new VerticeOfSizeException();
        }
        return existe;
    }

    @Override
    public Double pesoArista(Integer o, Integer d) {
        Double peso = Double.NaN;
        try {
            if (existeArista(o, d)) {
                ListaEnlazada<Adycencia> adycentes = listaAdycente[o];
                for (int i = 0; i < adycentes.getSize(); i++) {
                    Adycencia a = adycentes.obtener(i);
                    if (a.getDestino().intValue() == d.intValue()) {
                        peso = a.getPeso();
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }
        return peso;
    }

    @Override
    public void insertarArista(Integer o, Integer d, Double peso) throws Exception {

        if (o.intValue() <= numVertices && d.intValue() <= numVertices) {
            if (!existeArista(o, d)) {
                numAristas++;
                listaAdycente[o].insertar(new Adycencia(d, peso));
            }
        } else {
            throw new VerticeOfSizeException();
        }

    }

    @Override
    public void insertarArista(Integer o, Integer d) throws Exception {
        insertarArista(o, d, Double.NaN);
    }

    public Integer[] busquedaAnchura(Integer origen) throws Exception {
        Integer vertices = this.numVertices();
        Integer[] visitados = new Integer[vertices];
        visitados[0] = origen;
        Integer i = 0;
        Integer contador = i+1;
        boolean encontro = false;
        while (contador < vertices) {
            for (int j = 0; j < listaAdycente[origen].getSize(); j++) {
                for (int k = 0; k < visitados.length; k++) {
                    if (visitados[k] == listaAdycente[origen].obtener(j).getDestino()) {
                        encontro = true;
                        break;
                    } else {
                        encontro = false;
                    }
                }
                if (!encontro) {
                    visitados[contador] = listaAdycente[origen].obtener(j).getDestino();
                    for (int n = 0; n < visitados.length; j++) {
                        System.out.println(visitados[n]);
                    }
                    contador++;
                }
            }

            if (origen == vertices) {
                origen = 0;
            }
            if (contador == vertices) {
                break;
            }
            origen++;
            if (i > vertices) {
                break;
            }
            i++;
        }
        for (int j = 0; j < visitados.length; j++) {
            System.out.println(visitados[j]);
        }
        return visitados;

    }

    public Integer[] busquedaProfundidad(Integer origen) throws Exception {
        int vertices = this.numVertices();

        Integer[] visitados = new Integer[vertices];
        visitados[0] = origen;
        Integer contador = 1;
        int i = 0;
        boolean band = false;
        while (contador < vertices) {
            i++;
            for (int j = 0; j < listaAdycente[origen].getSize(); j++) {
                for (int k = 0; k < visitados.length; k++) {
//                    if (visitados[k] == listaAdycente[origen].obtener(j).getDestino()) {
//                        band = true;
//                        break;
//                    } else {
//                        band = false;
//                    }
                }
                if (!band) {
                    visitados[contador] = listaAdycente[origen].obtener(j).getDestino();
                    contador++;
                }
            }

            if (origen == vertices) {
                origen = 0;
            }
            if (contador == vertices) {
                break;
            }
            origen++;
            if (i > vertices) {
                break;
            }
        }
        for (int j = 0; j < visitados.length; j++) {
            System.out.println(visitados[j]);
        }
        return visitados;
    }

    @Override
    public ListaEnlazada<Adycencia> adycentes(Integer v) {
        return listaAdycente[v];
    }

    public Integer getNumVertices() {
        return numVertices;
    }

    public Integer getNumAristas() {
        return numAristas;
    }

    public void setNumAristas(Integer numAristas) {
        this.numAristas = numAristas;
    }

    public ListaEnlazada<Adycencia>[] getListaAdycente() {
        return listaAdycente;
    }

}
