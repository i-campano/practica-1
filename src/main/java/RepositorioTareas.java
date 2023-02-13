import java.util.List;
import java.util.stream.Collectors;

public class RepositorioTareas {

  private static RepositorioTareas instance;

  public static RepositorioTareas getInstance() {
    if(instance==null){
      instance = new RepositorioTareas();
    }
    return instance;
  }

  private RepositorioTareas(){}

  List<TareaConcreta> todasLasTareas;

  List<Tarea> tareasQuePasaranAPorHacer(){
    return todasLasTareas.stream().filter(tarea->
        tarea.estado.equals(Estado.PENDIENTE) &&
        tarea.listaHaceMasDeUnMes()
    ).collect(Collectors.toList());
  }
}
