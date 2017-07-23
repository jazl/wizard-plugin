package com.azl.common;

public class WizardState {
    public String wizardState;
    public int wizardCount;

    public String getWizardState() {
        return wizardState;
    }

    public void setWizardState(String wizardState) {
        this.wizardState = wizardState;
    }

    public int getWizardCount() {
        return wizardCount;
    }

    public void setWizardCount(int wizardCount) {
        this.wizardCount = wizardCount;
    }

    public WizardState() {
        wizardState = "hello!";
        wizardCount = 0;
    }
}
