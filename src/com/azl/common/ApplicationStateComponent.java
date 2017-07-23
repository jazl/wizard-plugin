package com.azl.common;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name="fod.upload.wizard.state", storages = {
    @Storage(id = "fod-other", file = "$APP_CONFIG$/configProvider.xml")
})
public class ApplicationStateComponent implements ApplicationComponent, PersistentStateComponent<ApplicationStateComponent.State> {
    private WizardState wizardState;

    public State state = new State();

    public static class State {
        public State() {}
        public String foo;
        public int appCnt;
    }

    public ApplicationStateComponent() {
        wizardState = new WizardState();
        //wizState = new WizState();
        System.out.println("ApplicationStateComponent: constructor");
    }

    public int getAppCnt() {
        return state.appCnt;
    }

    public void setAppCnt(int cnt) {
        state.appCnt = cnt;
    }

    public WizardState getWizardState() {
        return wizardState;
    }

    @Override
    public void initComponent() {
        // TODO: insert component initialization logic here
        System.out.println("ApplicationStateComponent: initComponent");
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
        System.out.println("ApplicationStateComponent: disposeComponent");
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "ApplicationStateComponent";
    }

    @Nullable
    @Override
    public State getState() {
        return state;
    }

    @Override
    public void loadState(State state) {
        this.state = state;
    }

}
