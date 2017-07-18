package com.azl.common;

import com.azl.xwizard.StepIdentifiers;
import com.intellij.ide.wizard.AbstractWizardEx;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import org.jetbrains.annotations.Nullable;

/**
 * Created by jazl on 6/30/2017.
 */
public abstract class WizardStep extends AbstractWizardStepEx {

    protected static SharedObject sharedObject;
    protected AbstractWizardEx wizard;

    public WizardStep(@Nullable String title) {
        super(title);
    }

    public void setWizardRef(AbstractWizardEx wizardRef) {
        String step = this.getStepId().toString();
        this.wizard = wizardRef;
    }

    public void goPrevious() {
    }
    public void goNext() {
    }

}
