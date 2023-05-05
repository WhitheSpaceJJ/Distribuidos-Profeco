package datosinterfaces;

import entidades_profeco.Multa;
import java.util.List;

public interface IMulta {

    public List<Multa> listarTodasLasMultas();

    public boolean guardarMulta(Multa multa);

    public Multa obtenerMultaPorId(int id);

    public boolean actualizarMulta(Multa multa);

    public boolean eliminarMulta(int id);

}
