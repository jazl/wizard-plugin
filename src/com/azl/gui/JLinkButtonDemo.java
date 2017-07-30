package com.azl.gui;

import com.azl.custom.JLinkButton;
import com.intellij.util.ui.GridBag;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jazl on 7/30/17.
 */
public class JLinkButtonDemo extends JDialog {

    public JLinkButtonDemo() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("JLinkButton Demo");
        setSize(510, 300);
        add(createMainPanel());
    }

    public JPanel createMainPanel() {
        JPanel panel = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{450};
        panel.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;

        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        JLabel label1 = new JLabel("A label!");
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(label1, constraints);

        JTextField text1 = new JTextField(25);
        text1.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(text1, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.LINE_END;
        JLinkButton button1 = new JLinkButton("JLinkButton here");
        button1.setHorizontalAlignment(SwingConstants.RIGHT);
        //button1.setAlignmentX(25f);
        panel.add(button1, constraints);

        JLabel label2 = new JLabel("This is another label... on another line");
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(label2, constraints);

        constraints.fill = GridBagConstraints.NONE;
        JButton button2 = new JButton("Normal JButton");
        //button2.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(button2, constraints);

        //constraints.fill = GridBagConstraints.NONE;
        JCheckBox check1 = new JCheckBox("A Checkbox!");
        check1.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(check1, constraints);

        return panel;
    }
    public static void main(String[] args) {
        JLinkButtonDemo d = new JLinkButtonDemo();
        d.setVisible(true);
    }
}
