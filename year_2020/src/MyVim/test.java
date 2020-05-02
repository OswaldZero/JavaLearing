package MyVim;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class test extends JFrame {
    private JTextArea jTextArea=new JTextArea();
    public static void main(String[] args) {
        test test1=new test();
    }
    public test(){
        this.setTitle("wyh");
        this.add(BorderLayout.CENTER,jTextArea);
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
