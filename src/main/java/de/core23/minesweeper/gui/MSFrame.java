package de.core23.minesweeper.gui;

import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class MSFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel _jContentPane = null;

	private MSPanel _msPanel = null;

	private JMenuBar _jMenuBar = null;

	private JMenu _jMenuFile = null;

	private JMenu _jMenuHelp = null;

	private JMenuItem _jMenuItemNewGame = null;

	private JMenuItem _jMenuItemAbout = null;

	private JMenuItem _jMenuItemSettings = null;

	private JMenuItem _jMenuItemExit = null;

	private JLabel _jLabelTimeText = null;

	private JLabel _jLabelTime = null;

	private JButton _jButtonNewGame = null;

	private JLabel _jLabelMineText = null;

	private JLabel _jLabelMine = null;

	public MSFrame() {
		super();
		initialize();
	}

	private void initialize() {
		this.setResizable(false);
		this.setSize(500, 580);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(getJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("MineSweeper");
	}

	public JPanel getJContentPane() {
		if (_jContentPane == null) {
			_jLabelMineText = new JLabel();
			_jLabelMineText.setBounds(new Rectangle(15, 10, 40, 25));
			_jLabelMineText.setText("Minen:");
			_jLabelTimeText = new JLabel();
			_jLabelTimeText.setBounds(new Rectangle(390, 10, 40, 25));
			_jLabelTimeText.setText("Zeit:");
			_jContentPane = new JPanel();
			_jContentPane.setLayout(null);
			_jContentPane.add(getMSPanel(), null);
			_jContentPane.add(getJLabelTime(), null);
			_jContentPane.add(_jLabelTimeText, null);
			_jContentPane.add(getJButtonNewGame(), null);
			_jContentPane.add(_jLabelMineText, null);
			_jContentPane.add(getJLabelMine(), null);
		}
		return _jContentPane;
	}

	public JLabel getJLabelTime() {
		if (_jLabelTime == null) {
			_jLabelTime = new JLabel();
			_jLabelTime.setBounds(new Rectangle(430, 10, 50, 25));
			_jLabelTime.setHorizontalAlignment(SwingConstants.RIGHT);
			_jLabelTime.setHorizontalTextPosition(SwingConstants.RIGHT);
			_jLabelTime.setText("0:00");
		}
		return _jLabelTime;
	}

	public JLabel getJLabelMine() {
		if (_jLabelMine == null) {
			_jLabelMine = new JLabel();
			_jLabelMine.setBounds(new Rectangle(55, 10, 50, 25));
			_jLabelMine.setHorizontalTextPosition(SwingConstants.RIGHT);
			_jLabelMine.setText("0/5");
			_jLabelMine.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return _jLabelMine;
	}

	public JButton getJButtonNewGame() {
		if (_jButtonNewGame == null) {
			_jButtonNewGame = new JButton();
			_jButtonNewGame.setBounds(new Rectangle(187, 10, 120, 25));
			_jButtonNewGame.setText("Neues Spiel");
			_jButtonNewGame.setFocusable(false);
		}
		return _jButtonNewGame;
	}

	public MSPanel getMSPanel() {
		if (_msPanel == null) {
			_msPanel = new MSPanel();
			_msPanel.setLayout(new GridBagLayout());
			_msPanel.setBounds(new Rectangle(10, 40, 480, 480));
		}
		return _msPanel;
	}

	public JMenuBar getJMenuBar() {
		if (_jMenuBar == null) {
			_jMenuBar = new JMenuBar();
			_jMenuBar.add(getJMenuFile());
			_jMenuBar.add(getJMenuHelp());
		}
		return _jMenuBar;
	}

	public JMenu getJMenuFile() {
		if (_jMenuFile == null) {
			_jMenuFile = new JMenu();
			_jMenuFile.setText("Datei");
			_jMenuFile.add(getJMenuItemNewGame());
			_jMenuFile.add(getJMenuItemSettings());
			_jMenuFile.addSeparator();
			_jMenuFile.add(getJMenuItemExit());
			_jMenuFile.setMnemonic('D');
		}
		return _jMenuFile;
	}

	public JMenu getJMenuHelp() {
		if (_jMenuHelp == null) {
			_jMenuHelp = new JMenu();
			_jMenuHelp.setText("?");
			_jMenuHelp.add(getJMenuItemAbout());
			_jMenuHelp.setMnemonic('?');
		}
		return _jMenuHelp;
	}

	public JMenuItem getJMenuItemSettings() {
		if (_jMenuItemSettings == null) {
			_jMenuItemSettings = new JMenuItem();
			_jMenuItemSettings.setText("Einstellungen");
			_jMenuItemSettings.setMnemonic('S');
		}
		return _jMenuItemSettings;
	}

	public JMenuItem getJMenuItemNewGame() {
		if (_jMenuItemNewGame == null) {
			_jMenuItemNewGame = new JMenuItem();
			_jMenuItemNewGame.setText("Neues Spiel");
			_jMenuItemNewGame.setMnemonic('N');
		}
		return _jMenuItemNewGame;
	}

	public JMenuItem getJMenuItemAbout() {
		if (_jMenuItemAbout == null) {
			_jMenuItemAbout = new JMenuItem();
			_jMenuItemAbout.setText("Info");
			_jMenuItemAbout.setMnemonic('I');
		}
		return _jMenuItemAbout;
	}

	public JMenuItem getJMenuItemExit() {
		if (_jMenuItemExit == null) {
			_jMenuItemExit = new JMenuItem();
			_jMenuItemExit.setText("Ende");
			_jMenuItemExit.setMnemonic('E');
		}
		return _jMenuItemExit;
	}
}
