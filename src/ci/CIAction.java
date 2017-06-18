package ci;
/**
 * Java interface for action
 */
public interface CIAction {
    CrInstrument instrument=null;
    public void startAction(CrInstrument instrument,String actionCode,String data);
}