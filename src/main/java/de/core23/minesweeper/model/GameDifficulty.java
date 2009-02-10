package de.core23.minesweeper.model;

public class GameDifficulty {
	private String _name;

	private int _width;

	private int _height;

	private int _mines;

	public GameDifficulty(String name) {
		_name = name;
		_width = 0;
		_height = 0;
		_mines = 0;
	}

	public GameDifficulty(String name, int width, int height, int mines) {
		_name = name;
		_width = width;
		_height = height;
		_mines = mines;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getWidth() {
		return _width;
	}

	public void setWidth(int width) {
		_width = width;
	}

	public int getHeight() {
		return _height;
	}

	public void setHeight(int height) {
		_height = height;
	}

	public int getMines() {
		return _mines;
	}

	public void setMines(int mines) {
		_mines = mines;
	}

	@Override
	public String toString() {
		return getName();
	}
}
