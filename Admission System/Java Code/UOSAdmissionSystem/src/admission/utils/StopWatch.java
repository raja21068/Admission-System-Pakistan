package admission.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Yougeshwar
 */
public class StopWatch implements ActionListener{
    private int sec;
    private int min;
    private int hour;
    private javax.swing.JLabel label;
    
    public StopWatch(javax.swing.JLabel label) {
        this.label = label;
        this.sec = 0;
        this.min = 0;
        this.hour = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sec++;
        if(sec == 59) {
            sec = 0;
            min++;
            if(min == 59) {
                min = 0;
                hour++;
            }
        }
        String s = ((hour > 9) ? hour + "" : "0" + hour) + ":" + ((min > 9) ? min + "" : "0" + min) + ":" + ((sec > 9) ? sec + "" : "0" + sec);
        label.setText(s);
    }
}
