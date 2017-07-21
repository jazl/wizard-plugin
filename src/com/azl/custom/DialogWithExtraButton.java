package com.azl.custom;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DialogWithExtraButton extends DialogWrapper {
    public DialogWithExtraButton(@Nullable Project project) {
        super(project);
        init();
        setTitle("Dialog with custom actions");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.add(new JTextField());
        return panel;
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        Action[] defaultActions = super.createActions();
        Action[] customActions = new Action[3];

        try {
            customActions[0] = defaultActions[0];   // OK
            customActions[1] = new ResendAction("Resend", null, "Resend the token", 0);
            customActions[2] = defaultActions[1];   // Cancel
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        for(Action a:customActions){
            System.out.println("Action.NAME = "+a.getValue(Action.NAME));
        }
        return customActions;
    }

    class ResendAction extends AbstractAction {
        public ResendAction(String text, ImageIcon icon,
                          String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,"Resent!");
        }
    }
}
