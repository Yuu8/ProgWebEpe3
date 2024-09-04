package Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuarios implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuario_id;
    private String usuario_nombre;
    private String usuario_email;
    private String usuario_clave;

    public Usuarios() {
    }

    public Usuarios(int usuario_id, String usuario_nombre, String usuario_email, String usuario_clave) {
        this.usuario_id = usuario_id;
        this.usuario_nombre = usuario_nombre;
        this.usuario_email = usuario_email;
        this.usuario_clave = usuario_clave;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    public void setUsuario_nombre(String usuario_nombre) {
        this.usuario_nombre = usuario_nombre;
    }

    public String getUsuario_email() {
        return usuario_email;
    }

    public void setUsuario_email(String usuario_email) {
        this.usuario_email = usuario_email;
    }

    public String getUsuario_clave() {
        return usuario_clave;
    }

    public void setUsuario_clave(String usuario_clave) {
        this.usuario_clave = usuario_clave;
    }
    
}
