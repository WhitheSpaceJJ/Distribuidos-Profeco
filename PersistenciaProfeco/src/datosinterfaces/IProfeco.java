package datosinterfaces;

import entidades_profeco.Profeco;
import java.util.List;

public interface IProfeco {
    
    public List<Profeco> listarProfeco();

    public boolean guardarProfeco(Profeco profeco);

    public Profeco obtenerProfecoPorId(int id);

    public boolean actualizarProfeco(Profeco profeco);

    public boolean eliminarProfeco(int id);
    
}
