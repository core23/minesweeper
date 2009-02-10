package de.core23.minesweeper.component;

import javax.swing.text.*;

public class JTextFieldFilter extends PlainDocument {
	private static final long serialVersionUID = 1L;

	public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";

	public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String ALPHA = LOWERCASE + UPPERCASE;

	public static final String NUMERIC = "0123456789";

	public static final String FLOAT = NUMERIC + ".";

	public static final String ALPHA_NUMERIC = ALPHA + NUMERIC;

	protected String _acceptedChars = null;

	protected boolean _negativeAccepted = false;

	public JTextFieldFilter() {
		this(ALPHA_NUMERIC);
	}

	public JTextFieldFilter(String acceptedchars) {
		_acceptedChars = acceptedchars;
	}

	public void setNegativeAccepted(boolean negativeaccepted) {
		if (_acceptedChars.equals(NUMERIC) || _acceptedChars.equals(FLOAT) || _acceptedChars.equals(ALPHA_NUMERIC)) {
			_negativeAccepted = negativeaccepted;
			_acceptedChars += "-";
		}
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;

		if (_acceptedChars.equals(UPPERCASE))
			str = str.toUpperCase();
		else if (_acceptedChars.equals(LOWERCASE))
			str = str.toLowerCase();

		for (int i = 0; i < str.length(); i++) {
			if (_acceptedChars.indexOf(String.valueOf(str.charAt(i))) == -1)
				return;
		}

		if (_acceptedChars.equals(FLOAT) || (_acceptedChars.equals(FLOAT + "-") && _negativeAccepted)) {
			if (str.indexOf(".") != -1) {
				if (getText(0, getLength()).indexOf(".") != -1)
					return;
			}
		}

		if (_negativeAccepted && str.indexOf("-") != -1) {
			if (str.indexOf("-") != 0 || offset != 0)
				return;
		}

		super.insertString(offset, str, attr);
	}
}