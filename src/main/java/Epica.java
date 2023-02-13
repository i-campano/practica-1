import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Epica extends Tarea{
  private List<Tarea> tareas;

  public Epica() {
    this.tareas = new ArrayList<>();
  }


  public List<Tarea> getTareas(){
    return tareas.stream().flatMap(tarea -> tarea.getTareas().stream()).collect(Collectors.toList());
  }


  public int totalTareas(){
    return getTareas().size();
  }

  public void agregar(Tarea tarea){
    tareas.add(tarea);
  }
}
