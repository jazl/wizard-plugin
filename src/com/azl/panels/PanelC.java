package com.azl.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jazl on 6/29/2017.
 */
public class PanelC extends JPanel {
    Label textLabel;
    public PanelC() {
        String labelText = "Panel C";
        textLabel = new Label(labelText);
        add(textLabel);
        add(textLabel);
        add(textLabel);
        add(textLabel);
        setSize(new Dimension(500,500));
    }
    public void setLabelText(String text) {
        textLabel.setText(text);
    }
}
