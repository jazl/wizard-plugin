package com.azl.actions;

import com.azl.gui.ValidationDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class ValidationAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        ValidationDialog dialog = new ValidationDialog(null);
        dialog.show();
    }
}
