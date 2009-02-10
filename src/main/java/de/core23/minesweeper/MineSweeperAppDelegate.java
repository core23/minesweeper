package de.core23.minesweeper;

import javax.swing.SwingUtilities;

import de.core23.minesweeper.controller.MineSweeperController;

public class MineSweeperAppDelegate {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MineSweeperController().show(null);
			}
		});
	}
}
