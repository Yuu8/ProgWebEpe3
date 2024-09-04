package Persistencia;

import Modelo.Usuarios;
import java.util.List;

public class PersistenciaController {
    UsuariosJpaController usuJpa = new UsuariosJpaController();
    
    public List<Usuarios> obtenerUsuarios(){
        return usuJpa.findUsuariosEntities();
    }
}
