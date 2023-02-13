import java.util.List;

public class CronTab {

  //Cron-tab
  public static void main(String[] args) {
    pasarAPorHacer();
  }

  private static void pasarAPorHacer() {
    List<Tarea> tareas = RepositorioTareas.getInstance().tareasQuePasaranAPorHacer();
    tareas.forEach(tarea->tarea.setEstado(Estado.POR_HACER));
  }

}
