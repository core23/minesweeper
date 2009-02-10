package de.core23.minesweeper.model;

import java.util.Random;

public class Map {
	private static final Random rnd = new Random();

	private boolean generated;

	public int _map[][];

	public boolean _show[][];

	public boolean _mark[][];

	private int _width;

	private int _height;

	private int _mines;

	private int _minesFound;

	private int _hidden;

	public Map(int width, int height, int mines) {
		_width = width;
		_height = height;
		_mines = mines;
		_hidden = _width * _height - _mines;

		_map = new int[_width][_height];
		_show = new boolean[_width][_height];
		_mark = new boolean[_width][_height];

		generated = false;
	}

	private void generate(int freeX, int freeY) {
		int mines = _mines;

		while (mines > 0) {
			int x = rnd.nextInt(_width);
			int y = rnd.nextInt(_height);

			if (_map[x][y] > -1 && !(freeX == x && freeY == y)) {
				_map[x][y] = -1;
				mines--;
			}
		}

		for (int x = 0; x < _width; x++) {
			for (int y = 0; y < _height; y++) {
				// Mine
				if (_map[x][y] == -1) {
					incFeld(x - 1, y - 1);
					incFeld(x, y - 1);
					incFeld(x + 1, y - 1);

					incFeld(x - 1, y);
					incFeld(x + 1, y);

					incFeld(x - 1, y + 1);
					incFeld(x, y + 1);
					incFeld(x + 1, y + 1);
				}
			}
		}

		generated = true;
	}

	public boolean showField(int x, int y) {
		if (!isValidField(x, y))
			return false;

		if (!generated)
			generate(x, y);

		if (_show[x][y] || _mark[x][y])
			return false;

		_hidden--;
		_show[x][y] = true;

		// Freies Feld
		if (_map[x][y] == 0)
			showNeighbors(x, y);

		return true;
	}

	private void showNeighbors(int x, int y) {
		if (isValidField(x - 1, y - 1))
			showField(x - 1, y - 1);
		if (isValidField(x - 1, y))
			showField(x - 1, y);
		if (isValidField(x - 1, y + 1))
			showField(x - 1, y + 1);

		if (isValidField(x, y - 1))
			showField(x, y - 1);
		if (isValidField(x, y + 1))
			showField(x, y + 1);

		if (isValidField(x + 1, y - 1))
			showField(x + 1, y - 1);
		if (isValidField(x + 1, y))
			showField(x + 1, y);
		if (isValidField(x + 1, y + 1))
			showField(x + 1, y + 1);
	}

	public void markField(int x, int y) {
		if (!isValidField(x, y))
			return;		
		if (_show[x][y])
			return;

		if (_mark[x][y])
			_minesFound--;
		else
			_minesFound++;

		_mark[x][y] = !_mark[x][y];
	}

	private void incFeld(int x, int y) {
		if (!isValidField(x, y))
			return;

		if (_map[x][y] != -1)
			_map[x][y]++;
	}

	private boolean isValidField(int x, int y) {
		return (x >= 0 && y >= 0 && x < _width && y < _height);
	}

	public boolean isFinished() {
		return (_hidden == 0);
	}

	public int getWidth() {
		return _width;
	}

	public int getHeight() {
		return _height;
	}

	public int getMines() {
		return _mines;
	}

	public boolean isMark(int x, int y) {
		if (isValidField(x, y))
			return false;
		return _mark[x][y];
	}

	public boolean isMine(int x, int y) {
		if (isValidField(x, y))
			return false;
		return _map[x][y] == -1;
	}

	public int getField(int x, int y) {
		if (isValidField(x, y))
			return -1;
		return _map[x][y];
	}

	public int getMinesFound() {
		return _minesFound;
	}
}
