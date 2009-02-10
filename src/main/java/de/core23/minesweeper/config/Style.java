package de.core23.minesweeper.config;

import java.awt.Color;

public interface Style {
	public final Color PANEL_COLOR = Color.LIGHT_GRAY.brighter();

	public final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;

	public final Color BORDER_COLOR = Color.BLACK;

	public final Color MINE_COLOR = Color.BLACK;

	public final int BOX_SIZE = 20;

	public final int BOX_BORDER = 5;

	public final Color[] NUM_COLORS = {new Color(10, 10, 250), new Color(10, 250, 10), new Color(250, 10, 10), new Color(10, 10, 190), new Color(190, 10, 10),
		new Color(190, 10, 10), new Color(190, 10, 190), new Color(20, 20, 20)};

	public final Color MARK_COLOR = Color.DARK_GRAY;

	public final Color MARK_FLAG_COLOR = Color.RED;

}
