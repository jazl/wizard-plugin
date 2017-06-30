package com.azl.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jazl on 6/29/2017.
 */
public class PanelB extends JPanel {
    public PanelB() {
        setLayout(new FlowLayout());
        add(new Label("Panel B"));
        add(new JTextArea());
        add(new JButton("Press Me!"));
        add(new JButton("Me Too!"));
        setSize(new Dimension(500,500));
    }
}
