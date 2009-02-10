package de.core23.minesweeper.gui;

import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.core23.minesweeper.component.JTextFieldFilter;

public class SettingsDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JPanel _jContentPane = null;

	private JButton _jButtonSpeichern = null;

	private JLabel _jLabelBreite = null;

	private JLabel _jLabelHoehe = null;

	private JLabel _jLabelMinen = null;

	private JTextField _jTextBreite = null;

	private JTextField _jTextHoehe = null;

	private JTextField _jTextMinen = null;

	private JLabel _jLabelInfo = null;

	private JComboBox _jComboBoxDifficult = null;

	public SettingsDialog(Window window) {
		super(window);
		initialize();
	}

	private void initialize() {
		this.setSize(210, 290);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Einstellungen");
	}

	public JPanel getJContentPane() {
		if (_jContentPane == null) {
			_jLabelMinen = new JLabel();
			_jLabelMinen.setBounds(new Rectangle(20, 140, 50, 20));
			_jLabelMinen.setText("Minen");
			_jLabelHoehe = new JLabel();
			_jLabelHoehe.setBounds(new Rectangle(20, 100, 50, 20));
			_jLabelHoehe.setText("H�he");
			_jLabelBreite = new JLabel();
			_jLabelBreite.setBounds(new Rectangle(20, 60, 50, 20));
			_jLabelBreite.setText("Breite");
			_jContentPane = new JPanel();
			_jContentPane.setLayout(null);
			_jContentPane.add(getJButtonSpeichern(), null);
			_jContentPane.add(_jLabelBreite, null);
			_jContentPane.add(_jLabelHoehe, null);
			_jContentPane.add(_jLabelMinen, null);
			_jContentPane.add(getJTextBreite(), null);
			_jContentPane.add(getJTextHoehe(), null);
			_jContentPane.add(getJTextMinen(), null);
			_jContentPane.add(getJLabelInfo(), null);
			_jContentPane.add(getJComboBoxDifficult(), null);
		}
		return _jContentPane;
	}

	public JLabel getJLabelInfo() {
		if (_jLabelInfo == null) {
			_jLabelInfo = new JLabel();
			_jLabelInfo.setBounds(new Rectangle(20, 180, 160, 20));
			_jLabelInfo.setHorizontalTextPosition(SwingConstants.RIGHT);
			_jLabelInfo.setText("0% Abdeckung");
		}
		return _jLabelInfo;
	}

	public JButton getJButtonSpeichern() {
		if (_jButtonSpeichern == null) {
			_jButtonSpeichern = new JButton();
			_jButtonSpeichern.setBounds(new Rectangle(80, 220, 100, 25));
			_jButtonSpeichern.setText("Speichern");
		}
		return _jButtonSpeichern;
	}

	public JTextField getJTextBreite() {
		if (_jTextBreite == null) {
			_jTextBreite = new JTextField();
			_jTextBreite.setBounds(new Rectangle(120, 60, 60, 20));
			_jTextBreite.setText("10");
			_jTextBreite.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
		}
		return _jTextBreite;
	}

	public JTextField getJTextHoehe() {
		if (_jTextHoehe == null) {
			_jTextHoehe = new JTextField();
			_jTextHoehe.setBounds(new Rectangle(120, 100, 60, 20));
			_jTextHoehe.setText("10");
			_jTextHoehe.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
		}
		return _jTextHoehe;
	}

	public JTextField getJTextMinen() {
		if (_jTextMinen == null) {
			_jTextMinen = new JTextField();
			_jTextMinen.setBounds(new Rectangle(120, 140, 60, 20));
			_jTextMinen.setText("5");
			_jTextMinen.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
		}
		return _jTextMinen;
	}

	public JComboBox getJComboBoxDifficult() {
		if (_jComboBoxDifficult == null) {
			_jComboBoxDifficult = new JComboBox();
			_jComboBoxDifficult.setBounds(new Rectangle(20, 20, 160, 20));
		}
		return _jComboBoxDifficult;
	}
}
