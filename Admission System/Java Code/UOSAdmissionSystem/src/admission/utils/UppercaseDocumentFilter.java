package admission.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class UppercaseDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attrs) throws BadLocationException {
        super.insertString(fb, offset, text.toUpperCase(), attrs);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if(text != null)
            super.replace(fb, offset, length, text.toUpperCase(), attrs);
    }
}
