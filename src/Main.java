import javax.swing.JFrame;

public class Main extends JFrame {
	LoginForm loginf;
	mainForm mainF;
	public Main() {
		loginf = new LoginForm();
		loginf.setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
		
	}

}
