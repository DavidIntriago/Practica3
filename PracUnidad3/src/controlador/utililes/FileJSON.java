/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.utililes;

import java.io.File;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

/**
 * Esta clase se encarga de guardar y cargar objetos en formato json
 *
 * @author daniel
 */
public class FileJSON {

    private static String DIRDATA = "data";
    private String fullpath;
    private String nombreArchivo;
    private File file;
    private Gson gson;

    /**
     * Constructor para la creacion del archivo .json en la carpeta data
     *
     * @param nombreArchivo Parametro que sera utilizado para crear el archivo
     */
    public FileJSON(String nombreArchivo) {
        fullpath = DIRDATA + File.separatorChar + nombreArchivo + ".json";
        file = new File(fullpath);
        gson = new Gson();
    }

    /**
     * Convierte a json el objeto pasado y lo guarda en un archivo
     *
     * @param <T> Indica el uso de genericos
     * @param objeto Objeto a guardar
     * @return Un booleano indicando si se realizó correctamente el proceso
     */
    public <T> boolean guardar(T objeto) {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            //gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
            gson = gsonBuilder.setPrettyPrinting().create();
            String json = gson.toJson(objeto);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(json);
            bufferedWriter.flush();
            bufferedWriter.close();
            System.out.println("ok");
            return true;
        } catch (Exception e) {
            System.out.println("xdjson: " + e.getMessage());
            return false;
        }
    }

    /**
     * Cargar el json mediante la clase especificada
     *
     * @param <T> Indica el uso de genericos
     * @param clazz Es la clase necesaria para cargar el json
     * @return Retorna el objeto que contendrá el objeto cargado o null, en caso
     * algo salga mal
     */
    public <T> T cargar(Class<T> clazz) {
        T objeto = null;
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
           // gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
            gson = gsonBuilder.setPrettyPrinting().create();

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            objeto = gson.fromJson(bufferedReader, clazz);
        } catch (Exception e) {
            System.out.println("xdjson: " + e.getMessage());
        }
        return objeto;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.fullpath = DIRDATA + File.separatorChar + this.nombreArchivo + ".json";
        this.file = new File(fullpath);
    }

}
