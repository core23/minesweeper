package de.core23.minesweeper.gui;

import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HolzDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JPanel _jContentPane = null;

	private JButton _jButtonEinHolz = null;

	private JButton _jButtonZweiHolz = null;

	private JButton _jButtonDreiHolz = null;

	private JButton _jButtonNeuesSpiel = null;

	private JLabel _jLabelHolz = null;

	public HolzDialog(Window window) {
		super(window);
		initialize();
	}

	public void initialize() {
		this.setSize(340, 110);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("H�lzchenspiel");
	}

	public JPanel getJContentPane() {
		if (_jContentPane == null) {
			_jContentPane = new JPanel();
			_jContentPane.setLayout(null);
			_jContentPane.add(getJButtonEinHolz(), null);
			_jContentPane.add(getJButtonZweiHolz(), null);
			_jContentPane.add(getJButtonDreiHolz(), null);
			_jContentPane.add(getJButtonNeuesSpiel(), null);
			_jContentPane.add(getJLabelHolz(), null);
		}
		return _jContentPane;
	}

	public JLabel getJLabelHolz() {
		if (_jLabelHolz == null) {
			_jLabelHolz = new JLabel();
			_jLabelHolz.setBounds(new Rectangle(10, 50, 230, 25));
			_jLabelHolz.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			_jLabelHolz.setHorizontalAlignment(SwingConstants.CENTER);
			_jLabelHolz.setText(" | | | | ");
		}
		return _jLabelHolz;
	}

	public JButton getJButtonEinHolz() {
		if (_jButtonEinHolz == null) {
			_jButtonEinHolz = new JButton();
			_jButtonEinHolz.setBounds(new Rectangle(10, 10, 90, 30));
			_jButtonEinHolz.setText("Ein Holz");
		}
		return _jButtonEinHolz;
	}

	public JButton getJButtonZweiHolz() {
		if (_jButtonZweiHolz == null) {
			_jButtonZweiHolz = new JButton();
			_jButtonZweiHolz.setBounds(new Rectangle(120, 10, 90, 30));
			_jButtonZweiHolz.setText("Zwei Holz");
		}
		return _jButtonZweiHolz;
	}

	public JButton getJButtonDreiHolz() {
		if (_jButtonDreiHolz == null) {
			_jButtonDreiHolz = new JButton();
			_jButtonDreiHolz.setBounds(new Rectangle(230, 10, 90, 30));
			_jButtonDreiHolz.setText("Drei Holz");
		}
		return _jButtonDreiHolz;
	}

	public JButton getJButtonNeuesSpiel() {
		if (_jButtonNeuesSpiel == null) {
			_jButtonNeuesSpiel = new JButton();
			_jButtonNeuesSpiel.setBounds(new Rectangle(260, 50, 60, 25));
			_jButtonNeuesSpiel.setText("Neu");
		}
		return _jButtonNeuesSpiel;
	}
}
