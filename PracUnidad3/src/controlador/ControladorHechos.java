/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.google.gson.Gson;
import controlador.grafo.Adycencia;
import controlador.grafo.GrafoNoDirijidoEtiquetado;
import controlador.listas.ListaEnlazada;
import java.util.HashMap;
import modelo.HechoHistorico;

/**
 *
 * @author Usuario
 */
public class ControladorHechos {
    private HechoHistorico hechoHistorico;
    private ListaEnlazada<HechoHistorico> listaHechos;
    private GrafoNoDirijidoEtiquetado<HechoHistorico> gnde;

    public HechoHistorico insertarHecho(String nombre, String descripcion, Integer anio_hecho){
        HechoHistorico hechoAux=new HechoHistorico(getListaHechos().getSize()+1, nombre, descripcion, anio_hecho);
        getListaHechos().insertar(hechoAux);
        return hechoAux;
    }
    
     public void crarGrafoHechos() throws  Exception{
        int numero=listaHechos.getSize();
        gnde=new GrafoNoDirijidoEtiquetado<>(numero, HechoHistorico.class);
        for (int i = 0; i < numero; i++) {
            gnde.etiquetarVertice((i+1), listaHechos.obtener(i));
        }
    }
     
     public void guardarGrafo() throws Exception {
        Gson gson = new Gson();            
        ListaEnlazada<HashMap> verticesE = new ListaEnlazada<>();
        HashMap<Integer, ObjetoGrafo> mapa = new HashMap<>();
        for(int i = 1; i <= getGnde().getNumVertices(); i++) {            
            ObjetoGrafo obj = new ObjetoGrafo();
            obj.setId_clase(getGnde().obtenerEtiqueta(i).getId_Hecho());
            obj.setClase(getGnde().obtenerEtiqueta(i).getClass().toString());
            obj.setListaAdycencias(getGnde().adycentes(i));
            mapa.put(i, obj);
        }
        verticesE.insertar(mapa);
        controlador.Utilidades.Utilidades.guardarArchivo(gson.toJson(verticesE), "datos/grafo.json");        
    }

    class ObjetoGrafo{
        String clase;
        Integer id_clase;
        ListaEnlazada<Adycencia>listaAdycencias = new ListaEnlazada<>();

        public String getClase() {
            return clase;
        }

        public void setClase(String clase) {
            this.clase = clase;
        }

        

        public Integer getId_clase() {
            return id_clase;
        }

        public void setId_clase(Integer id_clase) {
            this.id_clase = id_clase;
        }

        public ListaEnlazada<Adycencia> getListaAdycencias() {
            return listaAdycencias;
        }

        public void setListaAdycencias(ListaEnlazada<Adycencia> listaAdycencias) {
            this.listaAdycencias = listaAdycencias;
        }

        
        
    }
    
    public HechoHistorico getHechoHistorico() {
        return hechoHistorico;
    }

    public void setHechoHistorico(HechoHistorico hechoHistorico) {
        this.hechoHistorico = hechoHistorico;
    }

    public ListaEnlazada<HechoHistorico> getListaHechos() {
        if (listaHechos==null) {
            listaHechos=new ListaEnlazada<>();
        }
        return listaHechos;
    }

    public void setListaHechos(ListaEnlazada<HechoHistorico> listaHechos) {
        this.listaHechos = listaHechos;
    }

    public GrafoNoDirijidoEtiquetado<HechoHistorico> getGnde() throws Exception{
        if (gnde==null) {
            crarGrafoHechos();
        }
        return gnde;
    }

    public void setGnde(GrafoNoDirijidoEtiquetado<HechoHistorico> gnde) {
        this.gnde = gnde;
    }
    
    
}
