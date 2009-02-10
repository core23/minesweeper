package de.core23.minesweeper.controller;

import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import de.core23.minesweeper.config.MineSweeperConfig;
import de.core23.minesweeper.gui.MSFrame;
import de.core23.minesweeper.model.Map;

public class MineSweeperController {
	private MSFrame _frame;

	private boolean _start;

	private long _tmrStart;

	private Timer _timer;

	private int _eeCounter = 0;

	private Map _map;

	public MineSweeperController() {
		_timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tick();
			}
		});
		_start = false;
	}

	public void show(Window window) {
		_frame = new MSFrame();
		_frame.setLocationRelativeTo(window);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		_frame.getJButtonNewGame().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				newGame();
			}
		});
		_frame.getMSPanel().addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				_eeCounter = 0;

				if (!_start)
					return;

				Point p = _frame.getMSPanel().translatePoint(e.getPoint());

				if (p == null)
					return;

				// Linkte Maustaste
				if (SwingUtilities.isLeftMouseButton(e)) {
					showField(p.x, p.y);

					// Rechte Maustaste
				} else if (SwingUtilities.isRightMouseButton(e)) {
					markField(p.x, p.y);
				}

			}
		});
		_frame.getJMenuItemSettings().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				newGameDialog();
			}
		});
		_frame.getJMenuItemNewGame().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				newGame();
			}
		});
		_frame.getJMenuItemAbout().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				showInfo();
			}
		});
		_frame.getJMenuItemExit().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				exit();
			}
		});

		newGame(MineSweeperConfig.WIDTH, MineSweeperConfig.HEIGHT, MineSweeperConfig.MINES);

		_frame.setVisible(true);
	}

	private void showField(int x, int y) {
		if (!_map.showField(x, y))
			return;

		_frame.getMSPanel().setField(x, y, _map.getField(x, y));

		if (_map.isMine(x, y)) {
			_timer.stop();
			_start = false;
			JOptionPane.showMessageDialog(_frame, "Game Over!", "MineSweeper", JOptionPane.ERROR_MESSAGE);

		} else if (_map.isFinished()) {
			_timer.stop();
			_start = false;
			JOptionPane.showMessageDialog(_frame, "Sie haben gewonnen!", "MineSweeper", JOptionPane.INFORMATION_MESSAGE);
		}

		_frame.getMSPanel().repaint();
	}

	private void markField(int x, int y) {
		_map.markField(x, y);

		_frame.getMSPanel().setMark(x, y, _map.isMark(x, y));

		_frame.getJLabelMine().setText(Integer.toString(_map.getMinesFound()) + "/" + Integer.toString(_map.getMines()));

		_frame.getMSPanel().repaint();
	}

	public void showInfo() {
		JOptionPane.showMessageDialog(_frame, "Version 1.04\r\n\r\nCopyright 2009 Christian Gripp\r\n\r\nhttps://core23.de", "MineSweeper",
			JOptionPane.INFORMATION_MESSAGE);
	}

	public void newGameDialog() {
		SettingsController ctrl = new SettingsController(this);
		ctrl.show(_frame);

		newGame(ctrl.getWidth(), ctrl.getHeight(), ctrl.getMines());
	}

	public void exit() {
		System.exit(0);
	}

	private void tick() {
		int sec = (int) (System.currentTimeMillis() - _tmrStart) / 1000;
		int min = sec / 60;
		sec %= 60;

		String s = String.valueOf(min) + ":";
		if (sec < 10)
			s += "0";
		s += String.valueOf(sec);
		_frame.getJLabelTime().setText(s);
	}

	public void newGame() {
		_eeCounter++;

		if (_eeCounter == 10) {
			HolzController ctrl = new HolzController();
			ctrl.show(_frame);

			_eeCounter = 0;
		}

		newGame(_map.getWidth(), _map.getHeight(), _map.getMines());
	}

	private void newGame(int width, int height, int mines) {
		_map = new Map(width, height, mines);

		_frame.getMSPanel().setMapSize(width, height);
		_frame.repaint();

		_frame.getJLabelMine().setText("0/" + Integer.toString(_map.getMines()));
		_frame.getJLabelTime().setText("0:00");
		_tmrStart = System.currentTimeMillis();

		_timer.restart();
		_start = true;

		_eeCounter = 0;
	}

	public int getGameWidth() {
		return _map.getWidth();
	}

	public int getGameHeight() {
		return _map.getHeight();
	}

	public int getGameMines() {
		return _map.getMines();
	}
}
