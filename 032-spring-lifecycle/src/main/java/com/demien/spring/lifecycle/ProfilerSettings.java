package com.demien.spring.lifecycle;

/**
 * Created by demien on 01.10.17.
 */
public class ProfilerSettings implements ProfilerSettingsMBean {

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
