package com.azl.panels;

import com.azl.common.SharedObject;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jazl on 6/29/2017.
 */
public class PanelB extends JPanel {
    private SharedObject _sharedObject;
    public PanelB(SharedObject sharedObject) {
        this._sharedObject = sharedObject;
        setLayout(new FlowLayout());
        add(new Label("Panel B"));
        if(_sharedObject != null) {
            add(new Label("SharedObject.counter = "+_sharedObject.getCounter()));
            add(new Label("SharedObject.message = "+_sharedObject.getMessage()));
        }
        else{
            add(new Label("No SharedObject!"));
        }
        add(new Label("Panel B"));
        add(new JTextArea());
        add(new JButton("Press Me!"));
        add(new JButton("Me Too!"));
        setSize(new Dimension(500,500));
    }
}
