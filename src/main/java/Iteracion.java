import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Iteracion {

  LocalDate fechaInicio;
  LocalDate fechaFin;

  List<Tarea> tareas;

  public Iteracion(LocalDate fechaInicio, LocalDate fechaFin) {
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.tareas = new ArrayList<>();
  }

  public void agregarTarea(Tarea tarea){
    tareas.add(tarea);
  }

  public void agregarTareas(Tarea... nuevasTareas){
    Collections.addAll(tareas,nuevasTareas);
  }

  public List<Tarea> getTareas(){
    return tareas.stream().flatMap(tarea -> tarea.getTareas().stream()).collect(Collectors.toList());
  }

}
