import java.util.ArrayList;
import java.util.List;

public class TareaConcreta extends Tarea{

  Estimacion estimacion;
  TipoTarea tipoTarea;
  Persona personaAsignada;
  String urlImagen;

  public TareaConcreta() {
    super();
  }

  public TareaConcreta(String nombre, String descripcion, Estado estado,
      TipoTarea tipoTarea) {
    super(nombre,descripcion,Estado.PENDIENTE);
    this.tipoTarea = tipoTarea;
  }

  public void setEstado(Estado estado) {
    if(this.estado==Estado.PENDIENTE && !estaListaParaComenzar()) {
      throw new NoEstaListaParaComenzar();
    }
    this.estado = estado;
  }

  public List<Tarea> getTareas(){
    List<Tarea> tareas = new ArrayList<>();
    tareas.add(this);
    return tareas;
  }

  public void agregarEstimacion(int dias){
    if(descripcion!=null && !descripcion.isEmpty()){
      estimacion = new Estimacion(dias, EstadoEstimacion.ESPERANDO_APROBACION);
    }else{
      throw new NoPuedeEstimarseAlgoSinDescripcion();
    }
  }

  public Boolean estimacionRazonable(){
    return estimacion!=null && estimacion.estimacionDefinitiva();
  }

  public Boolean estaListaParaComenzar(){
    return this.estimacionRazonable() && this.estaCompleta();
  }


  public void cambiarEstadoEstimacion(EstadoEstimacion estadoEstimacion){
    this.estimacion.setEstadoEstimacion(estadoEstimacion);
  }


  public void asignarPersona(Persona personaAsignada, AdapterEmailSender adapterEmailSender){
    this.personaAsignada = personaAsignada;
    adapterEmailSender.notificarAsignacion(this);
  }

  public Persona getPersonaAsignada() {
    return personaAsignada;
  }

  public void setUrlImagen(String url){
    this.urlImagen = url;
  }

  public boolean listaHaceMasDeUnMes(){
    return estaListaParaComenzar() && this.estimacion.definitivaHaceMasDeUnMes();
  }

}
