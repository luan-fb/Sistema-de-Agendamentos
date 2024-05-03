package exceções;

import javax.swing.JOptionPane;

public class NuloException extends Exception{

	private static final long serialVersionUID = 1L;

	public NuloException(String msg) {
	super(msg);
	}
}
