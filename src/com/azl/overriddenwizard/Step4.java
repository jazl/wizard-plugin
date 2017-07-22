package com.azl.overriddenwizard;

import com.intellij.ide.wizard.CommitStepException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class Step4 extends OverriddenWizardStepBase {
    public Step4(@Nullable String title) {
        super(title);
        System.out.println(getStepIdString()+" initialized");
    }

    @NotNull
    @Override
    public Object getStepId() {
        return 4;
    }

    @Nullable
    @Override
    public Object getNextStepId() {
        return null;
    }

    @Nullable
    @Override
    public Object getPreviousStepId() {
        return 3;
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
