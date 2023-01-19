/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseListenerUtil {
    public static void removeMouseListener(JPanel panel, JLabel label1, JLabel label2) {
        for (MouseListener ml : panel.getMouseListeners()) {
            panel.removeMouseListener(ml);
        }
        for (MouseListener ml : label1.getMouseListeners()) {
            label1.removeMouseListener(ml);
        }
        for (MouseListener ml : label2.getMouseListeners()) {
            label2.removeMouseListener(ml);
        }
    }
}
