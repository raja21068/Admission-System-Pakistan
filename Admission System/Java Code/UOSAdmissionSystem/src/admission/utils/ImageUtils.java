package admission.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * @author Yougeshwar
 * 
 */
public class ImageUtils {
    
    private static final char[] HEXCHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    
    /** Creates a new instance of ImageUtils */
    private ImageUtils() {
    }
    
    private static byte[] readStream(InputStream in) throws IOException {
        byte[] buffer = new byte[1024];
        byte[] resource = new byte[0];             
        int n;
        
        while ((n = in.read(buffer)) != -1) {
            byte[] b = new byte[resource.length + n];
            System.arraycopy(resource, 0, b, 0, resource.length);
            System.arraycopy(buffer, 0, b, resource.length, n);
            resource = b;
        }
        return resource;       
    }
    
    public static byte[] getBytesFromResource(String file) {
        
        InputStream in = ImageUtils.class.getResourceAsStream(file);
        
        if (in == null) {
            return null;
        } else {        
            try {
                return ImageUtils.readStream(in);
            } catch (IOException e) {
                return new byte[0];
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }
    
    public static BufferedImage readImageFromResource(String file) {
        return readImage(getBytesFromResource(file));
    }
    
    public static BufferedImage readImage(String url) {
        try {
            return readImage(new URL(url));
        } catch (MalformedURLException e) {
            return null;
        }
    }
    
    public static BufferedImage readImage(URL url) {
        
        InputStream in = null;
        
        try {
            URLConnection urlConnection = url.openConnection();
            in = urlConnection.getInputStream();
            return readImage(readStream(in));
        } catch (IOException e) {
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
    }
    
    public static BufferedImage readImage(byte[] b) {
        if (b == null) {
            return null;
        } else {
            try {
                return ImageIO.read(new ByteArrayInputStream(b));
            } catch(IOException e) {
                return null;
            }
        }
    }
    
    public static byte[] writeImage(BufferedImage img) {
        if (img == null) {
            return null;
        } else {        
            try {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                ImageIO.write(img, "png", b);
                b.flush();
                b.close();
                return b.toByteArray();
            } catch(IOException e) {
                return null;
            }
        }
    }
    
    public static Object readSerializable(byte[] b) {
        if (b == null) {
            return null;
        } else {        
            try {
                Object obj;
                try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(b))) {
                    obj = in.readObject();
                }
                return obj;
            } catch (    ClassNotFoundException | IOException eCNF) {
                //logger.error("Cannot create lists object", eCNF);    
                return null;
            }
        }
    }
    
    public static byte[] writeSerializable(Object o) {
        
        if (o == null) {
            return null;
        } else {            
            try {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                try (ObjectOutputStream out = new ObjectOutputStream(b)) {
                    out.writeObject(o);
                    out.flush();
                }
                return b.toByteArray();
            } catch (IOException eIO) {
                eIO.printStackTrace();
                return null;
            }
        }
    }

    public static Properties readProperties(byte b[]) {
        Properties prop = new Properties();
        try {
            if (b != null) {
                prop.loadFromXML(new ByteArrayInputStream(b));
            }
        } catch (IOException e) {
        }
        return prop;
    }
       
    public static String bytes2hex(byte[] binput) {
        
        StringBuilder s = new StringBuilder(binput.length *2);
        for (int i = 0; i < binput.length; i++) {
            byte b = binput[i];
            s.append(HEXCHARS[(b & 0xF0) >> 4]);
            s.append(HEXCHARS[b & 0x0F]);            
        }
        return s.toString();
    }

    public static BufferedImage resizeImage(BufferedImage image, int width, int height) { 
        if(image == null) return null;
        
        int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();  
        g.drawImage(image, 0, 0, width, height, null);  
        g.dispose();
	g.setComposite(AlphaComposite.Src);
 
	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
        
        return resizedImage;
    }
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
