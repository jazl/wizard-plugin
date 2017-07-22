package com.azl.overriddenwizard;

import com.intellij.ide.wizard.CommitStepException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class Step1 extends OverriddenWizardStepBase {
    public Step1(@Nullable String title) {
        super(title);
        System.out.println(getStepIdString()+" initialized");
    }

    @NotNull
    @Override
    public Object getStepId() {
        return 1;
    }

    @Nullable
    @Override
    public Object getNextStepId() {
        return 2;
    }

    @Nullable
    @Override
    public Object getPreviousStepId() {
        return null;
    }

    @Override
    public boolean isComplete() {
        return isCompleteBase;
    }

    @Override
    public void commit(CommitType commitType) throws CommitStepException {
        System.out.println(getStepIdString() + " commit, commitType = "+commitType);
    }

    @Override
    public JComponent getComponent() {
        JPanel panel = new JPanel();
        panel.add(new JLabel(getStepIdString()));
        panel.add(new JButton(getStepIdString()));
        return panel;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return null;
    }

    private String getStepIdString() {
        return "Step "+getStepId();
    }
}
