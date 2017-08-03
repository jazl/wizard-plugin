package com.azl.gui;

import com.azl.custom.ContextMenuMouseListener;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GridVertLayoutTest extends JFrame {

    public GridVertLayoutTest() {
        add(new LoginPanel(null));
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        GridVertLayoutTest mainFrame = new GridVertLayoutTest();
        mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(600,700);
        mainFrame.setVisible(true);
    }
}