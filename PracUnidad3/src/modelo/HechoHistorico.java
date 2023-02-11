/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class HechoHistorico {
    private Integer id_Hecho;
    private String nombre;
    private String descripcion;
    private Integer anio_hecho;

    public HechoHistorico(Integer id_Hecho, String nombre, String descripcion, Integer anio_hecho) {
        this.id_Hecho = id_Hecho;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.anio_hecho = anio_hecho;
    }

    
    
    public Integer getId_Hecho() {
        return id_Hecho;
    }

    public void setId_Hecho(Integer id_Hecho) {
        this.id_Hecho = id_Hecho;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getAnio_hecho() {
        return anio_hecho;
    }

    public void setAnio_hecho(Integer anio_hecho) {
        this.anio_hecho = anio_hecho;
    }

    @Override
    public String toString() {
        return "HechoHistorico{" + "id_Hecho=" + id_Hecho + ", nombre=" + nombre + ", descripcion=" + descripcion + ", anio_hecho=" + anio_hecho;
    }
    
    
    
    
}
