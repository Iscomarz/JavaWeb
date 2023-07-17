package bean;

import models.CiudadModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import models.CrudModel;
import objetos.Ciudad;
import objetos.Respuesta;

/**
 *
 * @author Blueweb
 */
@ManagedBean
@ViewScoped
public class CiudadBean {

    private List<Ciudad> ciudades;
    private List<Ciudad> ciudadesFiltradas;
    CrudModel crud = new CrudModel();
    Ciudad city = new Ciudad();
    Respuesta resError = new Respuesta();
    private String descripcion;
    private String codigo;
    private int lada = 0;
    private int id;
    private int respuesta;

    @PostConstruct
    public void init() {
        ciudades = new ArrayList<Ciudad>();
        ciudades = CiudadModel.traerCiudades();
        // Inizalizar objteto
    }

    public void agregarCiudad() {
        city.setCodigo(getCodigo());
        city.setDescripcion(getDescripcion());
        city.setLada(getLada());
        respuesta = crud.agregarRegistro(city);

        if (respuesta == 0) {
            resError.evaluarRespuesta(respuesta);
            System.out.println("Se AGREGO con exito");
            descripcion = "";
            codigo = "";
            lada = 0;

        } else if (respuesta == 1) {
            resError.evaluarRespuesta(respuesta);
            System.out.println("Hubo un error");
        } else {
            resError.evaluarRespuesta(respuesta);
            System.out.println("La operacion fallo");
        }
        ciudades = CiudadModel.traerCiudades();
    }

    public void editarCiudad(int id, String codigo, String descripcion, int lada) {
        city.setId(id);
        city.setCodigo(codigo);
        city.setDescripcion(descripcion);
        city.setLada(lada);
        respuesta = crud.editarRegistro(city);
        if (respuesta == 0) {
            resError.evaluarRespuesta(respuesta);
            System.out.println("Se edito con exito");

        } else if (respuesta == 1) {
            resError.evaluarRespuesta(respuesta);
        } else {
            resError.evaluarRespuesta(respuesta);
        }
    }

    public void borrarCiudad(int id) {

        respuesta = crud.borrarRegistro(id);
        if (respuesta == 0) {
            resError.evaluarRespuesta(respuesta);
            System.out.println("Se borro con exito");

        } else if (respuesta == 1) {
            resError.evaluarRespuesta(respuesta);
            System.out.println("Hubo un error");
        } else {
            resError.evaluarRespuesta(respuesta);
            System.out.println("La operacion fallo");
        }
        
        ciudades = CiudadModel.traerCiudades();
    }

    //<editor-fold defaultstate="collapsed" desc="gets y sets">
    /**
     * @return the ciudades
     */
    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    /**
     * @param ciudades the ciudades to set
     */
    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    /**
     * @return the ciudadesFiltradas
     */
    public List<Ciudad> getCiudadesFiltradas() {
        return ciudadesFiltradas;
    }

    /**
     * @param ciudadesFiltradas the ciudadesFiltradas to set
     */
    public void setCiudadesFiltradas(List<Ciudad> ciudadesFiltradas) {
        this.ciudadesFiltradas = ciudadesFiltradas;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the lada
     */
    public int getLada() {
        return lada;
    }

    /**
     * @param lada the lada to set
     */
    public void setLada(int lada) {
        this.lada = lada;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
//</editor-fold>

}
