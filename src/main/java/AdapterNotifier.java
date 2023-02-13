public class AdapterNotifier implements AdapterEmailSender {

  Notifier notifier;

  public AdapterNotifier(Notifier notifier) {
    this.notifier = notifier;
  }

  public void notificarAsignacion(TareaConcreta tareaConcreta){
    notifier.notify(tareaConcreta.getPersonaAsignada().getEmail(), "Te asignaron esta tarea: " + tareaConcreta.getNombre());
  }
}
