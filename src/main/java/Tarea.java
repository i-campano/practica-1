import java.util.List;

public abstract class Tarea {
  String nombre;
  String descripcion;
  Estado estado;
  Proyecto proyecto;

  public Tarea(String nombre, String descripcion, Estado estado) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.estado = estado;
  }

  public Tarea() {

  }

  public abstract List<Tarea> getTareas();

  public void editarDescripcion(String descripcion){
     this.descripcion = descripcion;
  }


  public Boolean estaCompleta(){
    return descripcion != null;
  }


  public String getNombre() {
    return nombre;
  }

  public void setEstado(Estado estado){
    this.estado = estado;
  }
}
