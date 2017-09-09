package admission.utils;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Raja Kumar & Jay Kumar
 */
public class NumberDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attrs) throws BadLocationException {
        if(!text.isEmpty())
            if(Character.isDigit(text.charAt(0)))
                super.insertString(fb, offset, text, attrs);
            else Toolkit.getDefaultToolkit().beep();
        else super.insertString(fb, offset, text, attrs);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if(!text.isEmpty())
            if(Character.isDigit(text.charAt(0)))
                super.replace(fb, offset, length, text, attrs);
            else Toolkit.getDefaultToolkit().beep();
        else super.replace(fb, offset, length, text, attrs);
    }
}