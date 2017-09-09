package admission.utils;


import javax.swing.filechooser.FileFilter;

public class ExtensionsFilter extends FileFilter implements java.io.FileFilter{
        
    private String message;
    private String[] extensions;
        
    public ExtensionsFilter(String message, String... extensions) {
        this.message = message;
        this.extensions = extensions;            
    }
        
    @Override
    public boolean accept(java.io.File f) {
        if (f.isDirectory()) {
            return true;
        } else {
            String sFileName = f.getName();
            int ipos = sFileName.lastIndexOf('.');
            if (ipos >= 0) {
                String sExt = sFileName.substring(ipos + 1);
                for(String s : extensions) {
                    if (s.equalsIgnoreCase(sExt)) {
                        return true;
                    }
                }
            }                        
            return false;
        }   
    }
        
    @Override
    public String getDescription() {
        return message;
    }      
}