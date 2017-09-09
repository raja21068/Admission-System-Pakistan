/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admission.helpers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import admission.utils.MessageBox;

/**
 *
 * @author Yougeshwar
 */
public class ReportHelper {

    public static void fillMapParameter(Map map) {
        try {
            BufferedImage image = ImageIO.read(ReportHelper.class.getClass().getResource("/images/org_logo.jpg"));
            map.put("LEFT_LOGO", image);
            image = ImageIO.read(ReportHelper.class.getClass().getResource("/images/right_logo.jpg"));
            map.put("RIGHT_LOGO", image);
        } catch (IOException ex) {
            MessageBox.error(null, ex);
            Logger.getLogger(ReportHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
