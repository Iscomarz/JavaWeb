
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import models.UsuarioModel;
import objetos.Usuario;
import static encript.HexDigest.hexDigest;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sesiones.Sesion;
import bean.IdleMonitor;
//import sesiones.Sesion;

/**
 *
 * @author Blueweb
 */
@ManagedBean
@ViewScoped
public class LoginBean {

    UsuarioModel usM = new UsuarioModel();
    IdleMonitor idlMon = new IdleMonitor();
    Usuario usu = new Usuario();
    private String usuario;
    private String password;
    int resp;

    public String validarLogin()  {
        // password = hexDigest(password);
        usu.setUsuario(usuario);
        usu.setPassword(hexDigest(password));
        resp = usM.validaUsuario(usu);

        if (resp == 1) {
            System.out.println("Inicio sesion");
            return "/index";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Usuario o contraseña incorrecto!",
                    "Porvafor intenta de nuevo!"));
            return "/login";
        }
        //Sesion.abrirSesion();
    }

    public void logOut()  {

        HttpSession session = Sesion.getSession();
        if (session != null) {
            session.invalidate();  
            System.out.println("Cerro sesion");
            
            idlMon.logoutListener();
        }   
    }

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    public String getUsuarios() {
        return getUsuario();
    }

    public void setUsuarios(String usuario) {
        this.setUsuario(usuario);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the usM
     */
    public UsuarioModel getUsM() {
        return usM;
    }

    /**
     * @param usM the usM to set
     */
    public void setUsM(UsuarioModel usM) {
        this.usM = usM;
    }

    /**
     * @return the u
     */
    public Usuario getU() {
        return usu;
    }

    /**
     * @param u the u to set
     */
    public void setU(Usuario u) {
        this.usu = u;
    }
//</editor-fold>

    /**
     * @return the nombre
     */
    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
