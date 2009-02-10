package de.core23.minesweeper.controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import de.core23.minesweeper.config.HolzConfig;
import de.core23.minesweeper.gui.HolzDialog;

public class HolzController {
	private HolzDialog _frame;

	private int _holz = HolzConfig.HOLZ;

	private boolean _cpu = false;

	private Timer _timer;

	public HolzController() {
		_timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ziehHolz();
			}

		});
		newGame();
	}

	public void show(Window window) {
		_frame = new HolzDialog(window);
		_frame.setLocationRelativeTo(window);
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.setModal(true);

		_frame.getJButtonEinHolz().addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				ziehHolz(1);
			}
		});
		_frame.getJButtonZweiHolz().addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				ziehHolz(2);
			}
		});
		_frame.getJButtonDreiHolz().addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				ziehHolz(3);
			}
		});
		_frame.getJButtonNeuesSpiel().addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				newGame();
			}
		});
	}

	public void setTimer(boolean status) {
		if (status) {
			_timer.restart();
		} else {
			_timer.stop();
		}
	}

	public void newGame() {
		_holz = 15;
		_cpu = true;
		ziehHolz(0);
	}

	private void ziehHolz() {
		int zug = (_holz - HolzConfig.MIN) % (HolzConfig.MAX + 1);
		if (zug <= 0)
			zug = HolzConfig.MIN;
		ziehHolz(zug);
	}

	private void ziehHolz(int zug) {
		// Holz ziehen
		_holz -= zug;

		// Anzeige
		String s = " ";
		for (int i = 0; i < _holz; i++)
			s += " |";
		_frame.getJLabelHolz().setText(s);

		// Spieler wechseln
		_cpu = !_cpu;

		_frame.getJButtonEinHolz().setEnabled(!_cpu && _holz > 0);
		_frame.getJButtonZweiHolz().setEnabled(!_cpu && _holz > 1);
		_frame.getJButtonDreiHolz().setEnabled(!_cpu && _holz > 2);

		if (_holz <= 0) {
			setTimer(false);
			if (_cpu)
				JOptionPane.showMessageDialog(_frame, "Game Over!", "H\u00F6lzchenspiel", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(_frame, "Sie haben gewonnen!", "H\u00F6lzchenspiel", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		setTimer(_cpu);
	}
}
