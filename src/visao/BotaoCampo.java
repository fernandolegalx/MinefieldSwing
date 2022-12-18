package visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import modelo.Campo;
import modelo.CampoEvento;
import modelo.CampoObservador;
@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador,
MouseListener{
	
	private Campo campo;
	
	private final Color BG_PADRAO = new Color(184,184,184);
	private final Color BG_MARCADO = new Color(8,179,247);
	private final Color BG_EXPLOSAO = new Color(189,66,68);
	private final Color TEXTO_VERDE = new Color(0,100,0);

	
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBackground(BG_PADRAO);
		setOpaque(true);
		setBorder(BorderFactory.createBevelBorder(0));
		
		addMouseListener(this);
		campo.registrarObservador(this);
		
	}
	
	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch(evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		case MARCAR:
			aplicarEstiloMarcar();
			break;
		case EXPLODIR:
			aplicarEstiloExplodir();
		default:
			aplicarEstiloPadrão();
		}
		
		SwingUtilities.invokeLater(() -> {
			repaint();
			validate();
		});
	}

	private void aplicarEstiloPadrão() {
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");
	}

	private void aplicarEstiloExplodir() {
		setBackground(BG_EXPLOSAO);
		setText("X");
	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCADO);
		setForeground(Color.BLACK);
		setText("M");
	}

	private void aplicarEstiloAbrir() {
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		if(campo.isMinado()) {
			setBackground(BG_EXPLOSAO);
			return;
		}
		setBackground(BG_PADRAO);
		
		switch (campo.minasNaVizinhanca()) {
		case 1:
			setForeground(TEXTO_VERDE);
			break;
		case 2:
			setForeground(Color.BLUE);
		case 3:
			setForeground(Color.YELLOW);
		case 4:
		case 5:
		case 6:
			setForeground(Color.RED);
		default:
			setForeground(Color.pink);
			
		}
		
		String valor = !campo.vizinhacaSegura() ?
				campo.minasNaVizinhanca() + "" : "" ;
		setText(valor);
		
	}

	
	//Interface do evento do mouse
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {
			campo.abrir();
		}else {
			campo.alternarMarcacao();
		}
		
	}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}


	
	
	
}
