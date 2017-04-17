package parsersax;

/**
 *
 * @author 4ia
 */
public class Computer {
    protected int codice;
    protected String  keyboard, mouse, monitor;

    public Computer(int codice, String keyboard, String mouse, String monitor) {
        this.codice = codice;
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.monitor = monitor;
    }
    
     public Computer() {
        this.codice = -1;
        this.keyboard = null;
        this.mouse = null;
        this.monitor = null;
    }
    

    public int getCodice() {
        return codice;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public String getMouse() {
        return mouse;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    @Override
    public String toString() {
        return "Computer{" + "codice=" + codice + ", keyboard=" + keyboard + ", mouse=" + mouse + ", monitor=" + monitor + '}';
    }
    
    
            
    
   
    
}
