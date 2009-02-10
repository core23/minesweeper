package de.core23.minesweeper.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import de.core23.minesweeper.config.Style;

// TODO
public class MSPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private BufferedImage _bufferImage;

	private Graphics _bufferGraphics;

	private Font _font;

	private FontMetrics _fontMetrics;

	private int _width = 0;

	private int _height = 0;

	public boolean _show[][];

	public boolean _mark[][];

	public int _map[][];

	public MSPanel() {
		setMapSize(_width, _height);
	}

	public void setMapSize(int width, int height) {
		_width = width;
		_height = height;

		_map = new int[width][height];
		_show = new boolean[width][height];
		_mark = new boolean[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				_map[x][y] = 0;
				_show[x][y] = false;
				_mark[x][y] = false;
			}
		}

		initBuffer();

		drawGrid();

		repaint();
	}

	private void initBuffer() {
		int width = Style.BOX_BORDER + _width * (Style.BOX_SIZE + Style.BOX_BORDER);
		int height = Style.BOX_BORDER + _height * (Style.BOX_SIZE + Style.BOX_BORDER);

		_bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		_bufferGraphics = _bufferImage.getGraphics();

		setMinimumSize(new Dimension(width, height));

		_font = new Font("SansSerif", Font.BOLD, 14);
		_bufferGraphics.setFont(_font);
		_fontMetrics = _bufferGraphics.getFontMetrics();
	}

	public void setMark(int x, int y, boolean mark) {
		_mark[x][y] = mark;
		_show[x][y] = false;
		drawField(x, y);
	}

	public void setMine(int x, int y) {
		_mark[x][y] = false;
		_show[x][y] = true;
		_map[x][y] = -1;
		drawField(x, y);
	}

	public void setField(int x, int y, int value) {
		_mark[x][y] = false;
		_show[x][y] = true;
		_map[x][y] = value;

		drawField(x, y);
	}

	private void drawGrid() {
		int width = Style.BOX_BORDER + _width * (Style.BOX_SIZE + Style.BOX_BORDER);
		int height = Style.BOX_BORDER + _height * (Style.BOX_SIZE + Style.BOX_BORDER);

		_bufferGraphics.setColor(Style.BACKGROUND_COLOR);
		_bufferGraphics.fillRect(0, 0, width, height);

		_bufferGraphics.setColor(Style.BORDER_COLOR);
		for (int x = 0; x <= _width; x++)
			_bufferGraphics.fillRect(x * (Style.BOX_SIZE + Style.BOX_BORDER), 0, Style.BOX_BORDER, height);
		for (int y = 0; y <= _height; y++)
			_bufferGraphics.fillRect(0, y * (Style.BOX_SIZE + Style.BOX_BORDER), width, Style.BOX_BORDER);
	}

	private void drawField(int x, int y) {
		_bufferGraphics.setColor(Style.BACKGROUND_COLOR);
		_bufferGraphics.fillRect(x * (Style.BOX_SIZE + Style.BOX_BORDER) + Style.BOX_BORDER, y * (Style.BOX_SIZE + Style.BOX_BORDER) + Style.BOX_BORDER,
			Style.BOX_SIZE, Style.BOX_SIZE);

		double zoom = (double) Style.BOX_SIZE / 20d;

		// Verdecktes Feld
		if (!_show[x][y]) {
			// Markiertes Feld
			if (_mark[x][y]) {
				_bufferGraphics.setColor(Style.MARK_COLOR);
				_bufferGraphics.fillRect(x * (Style.BOX_SIZE + Style.BOX_BORDER) + 3, y * (Style.BOX_SIZE + Style.BOX_BORDER) + 16, 14, 3);
				_bufferGraphics.fillRect(x * (Style.BOX_SIZE + Style.BOX_BORDER) + 9, y * (Style.BOX_SIZE + Style.BOX_BORDER) + 6, 2, 10);

				_bufferGraphics.setColor(Style.MARK_FLAG_COLOR);
				Polygon poly = new Polygon();
				poly.addPoint(x * (Style.BOX_SIZE + Style.BOX_BORDER) + 11, y * (Style.BOX_SIZE + Style.BOX_BORDER) + 2);
				poly.addPoint(x * (Style.BOX_SIZE + Style.BOX_BORDER) + 3, y * (Style.BOX_SIZE + Style.BOX_BORDER) + 6);
				poly.addPoint(x * (Style.BOX_SIZE + Style.BOX_BORDER) + 11, y * (Style.BOX_SIZE + Style.BOX_BORDER) + 12);
				_bufferGraphics.fillPolygon(poly);
			}
			return;
		}

		switch (_map[x][y]) {
			// Freies Feld
			case 0:
				break;

			// Mine
			case -1:
				_bufferGraphics.setColor(Style.MINE_COLOR);
				_bufferGraphics.fillOval(x * (Style.BOX_SIZE + Style.BOX_BORDER) + Style.BOX_BORDER + (int) (4 * zoom), y * (Style.BOX_SIZE + Style.BOX_BORDER)
					+ Style.BOX_BORDER + (int) (4 * zoom), (int) (12 * zoom), (int) (12 * zoom));

				_bufferGraphics.fillRect(x * (Style.BOX_SIZE + Style.BOX_BORDER) + Style.BOX_BORDER + (int) (9 * zoom), y * (Style.BOX_SIZE + Style.BOX_BORDER)
					+ Style.BOX_BORDER + (int) (2 * zoom), (int) (2 * zoom), (int) (16 * zoom));
				_bufferGraphics.fillRect(x * (Style.BOX_SIZE + Style.BOX_BORDER) + Style.BOX_BORDER + (int) (2 * zoom), y * (Style.BOX_SIZE + Style.BOX_BORDER)
					+ Style.BOX_BORDER + (int) (9 * zoom), (int) (16 * zoom), (int) (2 * zoom));
				break;

			// Zahl
			default:
				_bufferGraphics.setFont(_font);
				_bufferGraphics.setColor(Style.NUM_COLORS[_map[x][y] - 1]);

				String s = String.valueOf(_map[x][y]);

				x *= Style.BOX_SIZE + (Style.BOX_SIZE - _fontMetrics.stringWidth(s)) / 2;
				y *= Style.BOX_SIZE + (Style.BOX_SIZE - _fontMetrics.getHeight()) / 2 + _fontMetrics.getAscent();

				_bufferGraphics.drawString(s, x, y);
				break;
		}
	}

	public void paint(Graphics g) {
		g.setColor(Style.PANEL_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (_bufferImage != null)
			g.drawImage(_bufferImage, (getWidth() - _bufferImage.getWidth()) / 2, (getHeight() - _bufferImage.getHeight()) / 2, this);
	}

	public Point translatePoint(Point p) {
		if (_bufferImage == null)
			return null;

		p.x = p.x - (getWidth() - _bufferImage.getWidth()) / 2;
		p.y = p.y - (getHeight() - _bufferImage.getHeight()) / 2;

		if (p.x % (Style.BOX_SIZE + Style.BOX_BORDER) <= Style.BOX_BORDER || p.y % (Style.BOX_SIZE + Style.BOX_BORDER) <= Style.BOX_BORDER)
			return null;
		else {
			p.x /= (Style.BOX_SIZE + Style.BOX_BORDER);
			p.y /= (Style.BOX_SIZE + Style.BOX_BORDER);
		}

		if (p.x < 0 || p.x >= _width || p.y < 0 || p.y >= _height)
			return null;
		return p;
	}
}
