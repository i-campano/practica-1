import java.util.List;

public class CronTab {

  public static void main(String[] args) {

    //Cron-tab
    List<Tarea> tareas = RepositorioTareas.getInstance().tareasQuePasaranAPorHacer();
    tareas.forEach(tarea->tarea.setEstado(Estado.POR_HACER));
  }

}
