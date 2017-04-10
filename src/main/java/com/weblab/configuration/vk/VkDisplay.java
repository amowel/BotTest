package com.weblab.configuration.vk;

/**
 * Created by amowel on 18.03.17.
 */
public enum VkDisplay {
    POPUP("popup"),
    PAGE("page"),
    MOBILE("mobile");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    VkDisplay(String type) {
        this.value = type;
    }


    @Override
    public String toString() {
        return value;
    }
}

