package com.azl.xwizard;

import com.azl.panels.PanelB;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import com.intellij.ide.wizard.CommitStepException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jazl on 6/29/2017.
 */
public class StepBx extends AbstractWizardStepEx {

    private JPanel panel = null;
    private boolean completeFlag = false;
    private boolean throwExceptionFlag = false;

    public StepBx(@Nullable String title) {
        super(title);
    }

    @NotNull
    @Override
    public Object getStepId() {
        return StepIdentifiers.STEP_B;
    }

    @Nullable
    @Override
    public Object getNextStepId() {
        return StepIdentifiers.STEP_C;
    }

    @Nullable
    @Override
    public Object getPreviousStepId() {
        return StepIdentifiers.STEP_A;
    }

    @Override
    public boolean isComplete() {
        return completeFlag;
    }

    @Override
    public void commit(CommitType commitType) throws CommitStepException {
        System.out.println(this.getStepId() + " commit "+commitType);
        if(throwExceptionFlag) {
            throw new CommitStepException("Stop it!");
        }
    }

    @Override
    public JComponent getComponent() {
        if(panel != null) return panel;

        panel = new PanelB();

        JCheckBox checkbox = new JCheckBox("Check me!");
        checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //completeFlag = checkbox.isSelected();
                throwExceptionFlag = checkbox.isSelected();
                fireStateChanged();
                //fireGoNext();
            }
        });
        panel.add(checkbox);
        return panel;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return null;
    }
}
