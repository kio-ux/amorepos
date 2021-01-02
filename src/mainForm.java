import javax.swing.JFrame;

public class mainForm extends JFrame {

	public mainForm() {
		setExtendedState(MAXIMIZED_BOTH);;
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setTitle("Amore POS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
