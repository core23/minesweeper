package de.core23.minesweeper.controller;

import java.awt.Window;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

import de.core23.minesweeper.gui.SettingsDialog;
import de.core23.minesweeper.model.GameDifficulty;

public class SettingsController {
	private SettingsDialog _frame;

	private int _height = 10;

	private int _width = 10;

	private int _mines = 5;

	public SettingsController(MineSweeperController mineSweeperController) {
		_height = mineSweeperController.getGameHeight();
		_width = mineSweeperController.getGameWidth();
		_mines = mineSweeperController.getGameMines();
	}

	public void show(Window window) {
		_frame = new SettingsDialog(window);
		_frame.setModal(true);
		_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		_frame.setLocationRelativeTo(window);

		_frame.getJButtonSpeichern().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				close();
			}
		});
		_frame.getJTextBreite().addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent e) {
				try {
					setWidth(Integer.parseInt(_frame.getJTextBreite().getText()));
				} catch (NumberFormatException ex) {
				}
				_frame.getJComboBoxDifficult().setSelectedIndex(0);
				updateInfo();
			}
		});
		_frame.getJTextHoehe().addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent e) {
				try {
					setHeight(Integer.parseInt(_frame.getJTextHoehe().getText()));
				} catch (NumberFormatException ex) {
				}
				_frame.getJComboBoxDifficult().setSelectedIndex(0);
				updateInfo();
			}
		});
		_frame.getJTextMinen().addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent e) {
				try {
					setMines(Integer.parseInt(_frame.getJTextMinen().getText()));
				} catch (NumberFormatException ex) {
				}
				_frame.getJComboBoxDifficult().setSelectedIndex(0);
				updateInfo();
			}
		});

		DefaultComboBoxModel difficultyModel = new DefaultComboBoxModel();
		difficultyModel.addElement(new GameDifficulty("Benutzerdefiniert"));
		difficultyModel.addElement(new GameDifficulty("Leicht", 9, 9, 10));
		difficultyModel.addElement(new GameDifficulty("Mittel", 16, 16, 40));
		difficultyModel.addElement(new GameDifficulty("Schwer", 23, 23, 99));
		difficultyModel.addElement(new GameDifficulty("Profi", 16, 16, 99));

		_frame.getJComboBoxDifficult().setModel(difficultyModel);

		for (int i = 0; i < difficultyModel.getSize(); i++) {
			GameDifficulty difficulty = (GameDifficulty) difficultyModel.getElementAt(i);

			if (difficulty.getWidth() == _width && difficulty.getHeight() == _height && difficulty.getMines() == _mines)
				_frame.getJComboBoxDifficult().setSelectedItem(difficulty);
		}

		_frame.getJComboBoxDifficult().addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent e) {
				GameDifficulty difficulty = (GameDifficulty) _frame.getJComboBoxDifficult().getSelectedItem();

				if (difficulty.getMines() != 0) {
					setWidth(difficulty.getWidth());
					setHeight(difficulty.getHeight());
					setMines(difficulty.getMines());
				}

				updateInfo();
			}
		});

		updateInfo();

		_frame.setVisible(true);
	}

	private void close() {
		_frame.dispose();
	}

	private void updateInfo() {
		_frame.getJTextBreite().setText(String.valueOf(_width));
		_frame.getJTextHoehe().setText(String.valueOf(_height));
		_frame.getJTextMinen().setText(String.valueOf(_mines));

		float abd = (float) _mines / (_width * _height) * 100;
		NumberFormat formatter = new DecimalFormat("##0.0");
		_frame.getJLabelInfo().setText(formatter.format(abd) + "% Abdeckung");
	}

	public int getHeight() {
		return _height;
	}

	public void setHeight(int height) {
		if (height < 5)
			height = 5;
		else if (height > 23)
			height = 23;

		_height = height;
	}

	public int getWidth() {
		return _width;
	}

	public void setWidth(int width) {
		if (width < 5)
			width = 5;
		else if (width > 23)
			width = 23;

		_width = width;
	}

	public int getMines() {
		return _mines;
	}

	public void setMines(int mines) {
		if (mines < 1)
			mines = 1;
		else if (mines > _width * _height - 5)
			mines = _width * _height - 5;

		_mines = mines;
	}
}
