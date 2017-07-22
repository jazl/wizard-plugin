package com.azl.abstractwizard;

import com.azl.xwizard.StepAx;
import com.azl.xwizard.StepBx;
import com.azl.xwizard.StepCx;
import com.intellij.ide.wizard.AbstractWizard;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import com.intellij.ide.wizard.CommitStepException;
import com.intellij.ide.wizard.Step;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jazl on 6/29/2017.
 */
public class _AbstractWizard extends AbstractWizard<AbstractWizardStepEx> {
    private boolean canGoNextFlag = true;

    public _AbstractWizard(String title, @Nullable Project project) {
        super(title, project);

        addStep(new StepAx("Step Ax", null));
        addStep(new StepBx("Step Bx"));
        addStep(new StepCx("Step Cx"));

        centerRelativeToParent();

        init();
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        System.out.println("doValidate called from AbstractWizard<AbstractWizardStepEx>");

        return super.doValidate();
    }

    @Override
    protected JComponent createSouthPanel() {
        return super.createSouthPanel();
    }

    @Override
    public JPanel getContentComponent() {
        return super.getContentComponent();
    }

    @Override
    protected JComponent createCenterPanel() {
        return super.createCenterPanel();
    }

    @Override
    public int getCurrentStep() {
        return super.getCurrentStep();
    }

    @Override
    public int getStepCount() {
        return super.getStepCount();
    }

    @Override
    public AbstractWizardStepEx getCurrentStepObject() {
        return super.getCurrentStepObject();
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected String addStepComponent(Component component) {
        return super.addStepComponent(component);
    }

    @Override
    protected void doPreviousAction() {
        super.doPreviousAction();
        System.out.println("doPreviousAction...");
        //canGoNextFlag = false;
    }

    @Override
    protected void doNextAction() {
        super.doNextAction();
        System.out.println("doNextAction... ");
    }

    @Override
    protected int getNextStep(int step) {
        //System.out.println("getNextStep with arg = "+step);
        int nextStep = super.getNextStep(step);
        return nextStep;
    }

    @Override
    protected AbstractWizardStepEx getNextStepObject() {
        return super.getNextStepObject();
    }

    @Override
    protected int getPreviousStep(int step) {
        return super.getPreviousStep(step);
    }

    @Override
    protected void updateStep() {
        super.updateStep();
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return super.getPreferredFocusedComponent();
    }

    @Override
    protected boolean canGoNext() {
        super.canGoNext();
        return canGoNextFlag;
    }

    @Override
    protected boolean canFinish() {
        return super.canFinish();
    }

    @Override
    public void updateButtons(boolean lastStep, boolean canGoNext, boolean firstStep) {
        super.updateButtons(lastStep, canGoNext, firstStep);
    }

    @Override
    protected boolean isFirstStep() {
        return super.isFirstStep();
    }

    @Override
    protected boolean isLastStep() {
        return super.isLastStep();
    }

    @Override
    protected JButton getNextButton() {
        return super.getNextButton();
    }

    @Override
    protected JButton getPreviousButton() {
        return super.getPreviousButton();
    }

    @Override
    protected JButton getHelpButton() {
        return super.getHelpButton();
    }

    @Override
    public JButton getCancelButton() {
        return super.getCancelButton();
    }

    @Override
    public Component getCurrentStepComponent() {
        return super.getCurrentStepComponent();
    }

    @Override
    protected void helpAction() {
        super.helpAction();
    }

    @Override
    protected void doHelpAction() {
        super.doHelpAction();
    }

    @Override
    protected int getNumberOfSteps() {
        return super.getNumberOfSteps();
    }

    @Nullable
    @Override
    protected String getHelpID() {
        return null;
    }
}
