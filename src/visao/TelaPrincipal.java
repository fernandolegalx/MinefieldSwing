package visao;

import javax.swing.JFrame;
import modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame{
	
	public TelaPrincipal() {
		Tabuleiro tabuleiro = new Tabuleiro(16,30, 50);
		add(new PainelTabuleiro(tabuleiro));
		
		
		setTitle("Campo Minado");
		setSize(690,438);
		setLocationRelativeTo(null); //Para manter centralizado
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //Para matar o processo 
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TelaPrincipal();
		}
	}

