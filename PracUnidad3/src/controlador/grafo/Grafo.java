/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo;

import controlador.listas.ListaEnlazada;
import java.util.Objects;

/**
 *
 * @author sebastian
 */
public abstract class Grafo {

    //V   ---> A
    //N1 --------  N2
    /**
     * Es el numero de vertices del grafo
     *
     * @return
     */
    public abstract Integer numVertices();

    public abstract Integer numAristas();

    public abstract Boolean existeArista(Integer o, Integer d) throws Exception;

    public abstract Double pesoArista(Integer o, Integer d);

    public abstract void insertarArista(Integer o, Integer d) throws Exception;

    public abstract void insertarArista(Integer o, Integer d, Double peso) throws Exception;

    public abstract ListaEnlazada<Adycencia> adycentes(Integer v);

    @Override
    public String toString() {
        StringBuffer grafo = new StringBuffer("");
        try {
            for (int i = 1; i <= numVertices(); i++) {
                grafo.append("Vertice " + String.valueOf(i));
                ListaEnlazada<Adycencia> lista = adycentes(i);
                for (int j = 0; j < lista.getSize(); j++) {
                    Adycencia a = lista.obtener(j);

                    if (a.getPeso().toString().equalsIgnoreCase(String.valueOf(Double.NaN))) {
                        grafo.append("-- Vertice destino " + a.getDestino() + "  -- SP");
                    } else {
                        grafo.append("-- Vertice destino " + a.getDestino() + "  -- Peso " + a.getPeso());
                    }
                }
                grafo.append("\n");
            }
        } catch (Exception e) {
            grafo.append(e.getMessage());
        }
        return grafo.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public ListaEnlazada caminiMinimo(Integer origen, Integer destino) throws Exception {
        ListaEnlazada camino = new ListaEnlazada();
        if (estaConectado()) {
            ListaEnlazada pesos = new ListaEnlazada();
            Boolean finalizar = false;
            Integer inicial = origen;
            camino.insertar(inicial);
            while (!finalizar) {
                ListaEnlazada<Adycencia> adycencias = adycentes(inicial);
                Double peso = 10000000.0;
                int T = -1;
                for (int i = 0; i < adycencias.getSize(); i++) {
                    Adycencia ad = adycencias.obtener(i);
                    if(!estaEnCamino(camino, destino)) {
                        Double pesoArista = ad.getPeso();
                        if(destino.intValue() == ad.getDestino().intValue()) {
                            T = ad.getDestino();
                            peso = pesoArista;
                            break;
                        } else if(pesoArista < peso) {
                            T = ad.getDestino();
                            peso = pesoArista;
                        }
                    }
                }
                pesos.insertar(peso);
                camino.insertar(T);
                inicial = T;
                if(destino.intValue() == inicial.intValue()) {
                    finalizar = true;
                }
            }
        } else {
            throw new Exception("Gafo no conectado");
        }
        return camino;
    }

        public Double[][] matrizPesos(Grafo grafo) {
        int vertices = grafo.numVertices();
        Double matrizAdyacencia[][] = new Double[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                Double peso = grafo.pesoArista(i +1, j+1);
                if (peso != 0) {
                    matrizAdyacencia[i][j] = peso;
                } else {
                    matrizAdyacencia[i][j] = 10000000.0;
                    System.out.println(peso);
                }
            }
        }
            for (int i = 0; i < matrizAdyacencia.length; i++) {
                for (int j = 0; j < matrizAdyacencia.length; j++) {
                    System.out.println(matrizAdyacencia[i][j]);
                }
            }
        return matrizAdyacencia;

    }

    public String algoritmoFloyd(Integer origen, Integer destino) throws Exception {
        int vertices = this.numVertices();
        Double matrizadyacencia[][] = matrizPesos(this);
        String caminos[][] = new String[vertices][vertices];
        String caminosAuxiliares[][] = new String[vertices][vertices];
        String caminoRecorrido = "", cadena = "", caminitos = "";
        int i, j, k;
        Double temporal1, temporal2, temporal3, temporal4, minimo;
        for (i = 0; i < vertices; i++) {
            for (j = 0; j < vertices; j++) {
                caminos[i][j] = "";
                caminosAuxiliares[i][j] = "";
            }
        }
        for (k = 0; k < vertices; k++) {
            for (i = 0; i < vertices; i++) {
                for (j = 0; j < vertices; j++) {
                    temporal1 = matrizadyacencia[i][j];
                    temporal2 = matrizadyacencia[i][k];
                    temporal3 = matrizadyacencia[k][j];
                    temporal4 = temporal2 + temporal3;
                    minimo = Math.min(temporal1, temporal4);
                    if (temporal1!=temporal4) {
                        if (minimo == temporal4) {
                            caminoRecorrido = "";
                            caminosAuxiliares[i][j] = k + "";
                            caminos[i][j] = caminosR(i, k, caminosAuxiliares, caminoRecorrido) + (k + 1);
                            System.out.println(caminos[i][j]);
                        }
                    }
                    matrizadyacencia[i][j] = minimo;
                }
            }
        }
        for (i = 0; i < vertices; i++) {
            for (j = 0; j < vertices; j++) {
                cadena = cadena + "[" + matrizadyacencia[i][j] + "]";
            }
            cadena = cadena + "\n";
        }
        for (i = 0; i < vertices; i++) {
            for (j = 0; j < vertices; j++) {
                if (matrizadyacencia[i][j] != Double.NaN) {
                    if (i != j) {
                        if (caminos[i][j].equals("")) {
                            caminitos += "De {" + (i + 1) + "---->" + (j + 1) + "} irse por...{" + (i + 1) + ",  " + (j + 1) + "}\n";
                        } else {
                            caminitos += "De {" + (i + 1) + "---->" + (j + 1) + "} irse por...{" + (i + 1) + ",  " + caminos[i][j] + ",  " + (j + 1) + "}\n";
                            System.out.println(caminitos);
                        }
                    }
                }
            }
        }

        return "La matriz de caminos mas cortos es: \n" + cadena + "\n los ditinotos caminos cortos son: \n" + caminitos;
    }

    public String caminosR(int i, int k, String[][] caminosAux, String caminoRecorrido) {
        if (caminosAux[i][k] == "") {
            return "";
        } else {
            caminoRecorrido += caminosR(i, Integer.parseInt(caminosAux[i][k]), caminosAux, caminoRecorrido) + (Integer.parseInt(caminosAux[i][k]) + 1) + ", ";
            return caminoRecorrido;
        }

    }
    private Double[][] pesosGrafo(Grafo grafo) throws Exception {
        Integer vertices = grafo.numVertices();
        Double[][] matriz = new Double[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
//                System.out.println((i+1)+" "+(j+1));
                Double peso = grafo.pesoArista(i + 1, j + 1);
                if (peso != 0) {
                    matriz[i][j] = peso;
                } else {
                    matriz[i][j] = 10000000.0;
                }
//                System.out.println(matriz[i][j]);
            }
        }
        return matriz;
    }
    public void caminoMinimoFloyd() throws Exception {
        ListaEnlazada caminoFloyd = new ListaEnlazada();
        Double[][] pesos = pesosGrafo(this);
        Integer n = this.numVertices();
        Integer[][] traza = new Integer[n][n];
        Double[][] d = new Double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = pesos[i][j];
                traza[i][j] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            d[i][i] = 0.0;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((d[i][k] + d[k][j]) < d[i][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                        traza[i][j] = k;
                    }
                }
            }
        }
        System.out.println("\t1\t2\t3\t4\t5\t6");
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1 + "\t");
            for (int j = 0; j < n; j++) {
                System.out.print(d[i][j] + "\t");
            }
            System.out.println("\n");
        }
//        return caminoFloyd;
    }
    
    public ListaEnlazada algoritmoDijkstra(Integer origen) throws Exception {

        ListaEnlazada caminoDijkstra = new ListaEnlazada();

        origen -= 1;
        Integer s = origen;
        Integer vertices = this.numVertices();
        Integer ultimo[] = new Integer[vertices];//ultmo vertice antes de llegar al destino

        Boolean visitados[] = new Boolean[vertices];// vertices visitados

        Double minimos[] = new Double[vertices];
        Double pesos[][] = pesosGrafo(this); 

        for (int i = 0; i < vertices; i++) {
            visitados[i] = false;
            minimos[i] = pesos[s][i];
            ultimo[i] = s;
        }

        visitados[s] = true;
        minimos[s] = 0.0;
        for (int i = 0; i < vertices; i++) {
            Integer v = minimo(vertices, visitados, minimos);
            visitados[v] = true;

            for (int w = 0; w < vertices; w++) {
                if (!visitados[w] && ((minimos[v] + pesos[v][w]) < minimos[w])) {
                    minimos[w] = minimos[v] + pesos[v][w];
                    ultimo[w] = v;
                }
            }
            caminoDijkstra.insertar(minimos[i]);
        }
        return caminoDijkstra;
    }

    private Integer minimo(Integer n, Boolean[] F, Double[] D) {
        Double maximo = 10000000.0;
        Integer v = 1;
        for (int j = 0; j < n; j++) {
            if (!F[j] && (D[j] <= maximo)) {
                maximo = D[j];
                v = j;
            }
        }
        return v;
    }
    
    
    public ListaEnlazada<Integer> caminoFloyd(Integer origen, Integer destino) throws Exception{
        ListaEnlazada camino = new ListaEnlazada();
        int vertices = this.numVertices();
        Integer inicial=origen;
        Double[][] matriz=new Double[vertices][vertices];
        Integer[][] caminos=new Integer[vertices][vertices];
        Integer[][] caminosAux=new Integer[vertices][vertices];

        for (int i = 1; i < vertices; i++) {
            for (int j = 1; j < vertices; j++) {
                caminosAux[i][j]=j;
                caminos[i][j]=i;
                if (i==j) {
                   matriz[i][j]=0d; 
                   caminos[i][j]=0;
                   caminosAux[i][j]=0;
                }else{
                    if (!this.existeArista(i, j)) {
                        matriz[i][j]=100000000.0;
                    }else{
                        matriz[i][j]=this.pesoArista(i, j);
                    }
                }
            }
        }
        for (int k = 1; k < vertices; k++) {
            for (int i = 1; i < vertices; i++) {
                for (int j = 1; j < vertices; j++) {
                    if (matriz[i][k]+matriz[k][j]<matriz[i][j]) {
                        matriz[i][j]=matriz[i][k]+matriz[k][j];
                        caminosAux[i][j]=caminos[k][j];
                        System.out.println(caminosAux[i][j]);
                    }
                }
            }
        }
        while (inicial!=destino) {            
            camino.insertarCabecera(inicial);
            inicial=caminosAux[inicial][destino];
            
        }
        camino.insertarCabecera(inicial);
        camino.imprimir();
        return camino;
    }
    
    
    public Boolean estaConectado() {
        Boolean band = true;
        for (int i = 1; i <= numVertices(); i++) {
            ListaEnlazada<Adycencia> lista = adycentes(i);
            if (lista.estaVacia() || lista.getSize() == 0) {
                band = false;
                break;
            }
        }
        return band;
    }
    
    public Boolean estaEnCamino(ListaEnlazada<Integer> lista, Integer vertice) throws Exception {
        Boolean band = false;
        for(int i = 0; i < lista.getSize(); i++) {
            if(lista.obtener(i).intValue() == vertice.intValue()) {
                band = true;
                break;
            }
        }
        return band;        
    }
    
       

}
