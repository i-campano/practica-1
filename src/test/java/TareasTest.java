
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class TareasTest {

  @Test
  public void testTareas(){
    TareaConcreta t1 = new TareaConcreta();
    TareaConcreta t2 = new TareaConcreta();
    TareaConcreta t3 = new TareaConcreta();

    Epica subEpica = new Epica();
    subEpica.agregar(t2);
    subEpica.agregar(t3);

    Epica epica = new Epica();
    epica.agregar(t1);
    epica.agregar(subEpica);


    Assert.assertEquals(3, epica.totalTareas());
  }

  @Test
  public void cambiarEstado(){
    TareaConcreta t1 = new TareaConcreta("tarea 1","esta es la desc", null, TipoTarea.HISTORIA);

    t1.agregarEstimacion(3);

    t1.cambiarEstadoEstimacion(EstadoEstimacion.DEFINITIVA);

    t1.setEstado(Estado.POR_HACER);

    Assert.assertEquals(t1.estado, Estado.POR_HACER);

  }

  @Test
  public void estimacionRechazada(){
    TareaConcreta t1 = new TareaConcreta("tarea 1","esta es la desc", null, TipoTarea.HISTORIA);

    t1.agregarEstimacion(3);

    t1.cambiarEstadoEstimacion(EstadoEstimacion.RECHAZADO);

    Assert.assertThrows(NoEstaListaParaComenzar.class, ()->{
      t1.setEstado(Estado.POR_HACER);
    });

    Assert.assertEquals(t1.estado, Estado.PENDIENTE);

  }

  @Test
  public void estimacionEsperaAprobacion(){
    TareaConcreta t1 = new TareaConcreta("tarea 1","esta es la desc", null, TipoTarea.HISTORIA);

    t1.agregarEstimacion(3);

    Assert.assertThrows(NoEstaListaParaComenzar.class, ()->{
      t1.setEstado(Estado.POR_HACER);
    });

    Assert.assertEquals(t1.estado, Estado.PENDIENTE);

  }

  @Test
  public void laTareaNoEstaCompletaPorFaltaDeDescripcion(){
    TareaConcreta t1 = new TareaConcreta("tarea 1",null, null, TipoTarea.HISTORIA);

    Assert.assertThrows(NoPuedeEstimarseAlgoSinDescripcion.class, ()->{
      t1.agregarEstimacion(3);
    });

    t1.editarDescripcion("ahora si tiene desc, se puede estimar");
    t1.agregarEstimacion(3);
    t1.cambiarEstadoEstimacion(EstadoEstimacion.DEFINITIVA);
    t1.setEstado(Estado.POR_HACER);

    Assert.assertEquals(t1.estado, Estado.POR_HACER);

  }

  @Test
  public void crearIteracion(){
    TareaConcreta t1 = new TareaConcreta();
    TareaConcreta t2 = new TareaConcreta();
    TareaConcreta t3 = new TareaConcreta();
    TareaConcreta t4 = new TareaConcreta();

    Epica subEpica = new Epica();
    subEpica.agregar(t2);
    subEpica.agregar(t3);

    Epica epica = new Epica();
    epica.agregar(t1);
    epica.agregar(subEpica);

    Iteracion iteracion = new Iteracion(LocalDate.of(1993,2,1), LocalDate.now());
    iteracion.agregarTareas(epica, t4);

    Assert.assertEquals(4, iteracion.getTareas().size());
  }


  @Test
  public void notificar(){
    TareaConcreta t1 = new TareaConcreta("tarea 1","desc",Estado.PENDIENTE,TipoTarea.HISTORIA);

    Notifier notifier = mock(Notifier.class);
    Persona persona = new Persona("Nombre", "email@gmail.com");
    t1.asignarPersona(persona,new AdapterNotifier(notifier));

    verify(notifier,times(1)).notify("email@gmail.com","Te asignaron esta tarea: " + t1.getNombre());

  }


}
