import java.time.LocalDate;

public class Estimacion {

  private int dias;
  private EstadoEstimacion estado;
  private LocalDate quedoDefinitivaDesde;

  public Estimacion(int dias, EstadoEstimacion estado) {
    this.dias = dias;
    this.estado = estado;
  }

  public Boolean estimacionDefinitiva(){
    return estado==EstadoEstimacion.DEFINITIVA;
  }

  public void setEstadoEstimacion(EstadoEstimacion estado) {
    this.estado = estado;
  }

  public LocalDate definitivaDesde() {
    return quedoDefinitivaDesde;
  }

  public void setDefinitiva(){
    setEstadoEstimacion(EstadoEstimacion.DEFINITIVA);
    quedoDefinitivaDesde = LocalDate.now();
  }

  public boolean definitivaHaceMasDeUnMes(){
    return definitivaDesde().isBefore(LocalDate.now().minusMonths(1));
  }
}
