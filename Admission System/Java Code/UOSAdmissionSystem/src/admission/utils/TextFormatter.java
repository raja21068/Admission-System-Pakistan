/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admission.utils;

/**
 *
 * @author Yougeshwar
 */
public class TextFormatter {
    public static String capitalizeEachWord(String text) {
//        if(text == null || text.isEmpty()) return text;
//        
//        text = text.toLowerCase();
//        String[] words = text.split(" ");
//        text = "";
//        for (String word : words) {
//            String upper = String.valueOf(word.charAt(0)).toUpperCase();
//            word = upper + word.substring(1);
//            
//            text += word + " ";
//        }
//        
//        return text.trim();
        char[] chars = text.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other chars here
                found = false;
            }
        }
        String[] words = String.valueOf(chars).split(" ");
        text = "";
        for (String word : words) {
            if(word.equalsIgnoreCase("of") || word.equalsIgnoreCase("in"))
                word = word.toLowerCase();
            
            text += word + " ";
        }
//        
        return text.trim();
//        return String.valueOf(chars);
    }
}
