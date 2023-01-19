/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarysystem;

import business.SystemController;
import java.util.regex.Pattern;
import java.util.List;
import java.util.regex.*;

/**
 *
 * @author GebreegziabherG
 */
public class ValidationUtil {
    
    public static boolean isValidZipCode(String zipCode) {
        Pattern p = Pattern.compile("^\\d{5}$");
        Matcher m = p.matcher(zipCode);
        return (m.matches());
    }

    public static boolean isValidPhone(String phone) {
        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher m = p.matcher(phone);
        return (m.matches());
    }

    public static boolean memberExists(String id) {
        SystemController sc = new SystemController();
        List<String> memberIds = sc.allMemberIds();
        for (int i = 0; i < memberIds.size(); i++)
            if (memberIds.get(i).equalsIgnoreCase(id))
                return true;
        return false;
    }

    public static boolean isValidNumber(String number) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(number);
        return (m.matches());
    }

    public static boolean isValidAlpha(String alphabet) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(alphabet);
        return (m.matches());
    }
}
