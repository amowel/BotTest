package com.weblab.configuration.vk;

/**
 * Created by amowel on 18.03.17.
 */

public class VkScopeBuilder {

    private StringBuilder scope;

    public VkScopeBuilder() {
        scope = new StringBuilder();
    }

    public VkScopeBuilder Notify() {
        scope.append("notify,");
        return this;
    }

    public VkScopeBuilder friends() {
        scope.append("friends,");
        return this;
    }

    public VkScopeBuilder audio() {
        scope.append("audio,");
        return this;
    }

    public VkScopeBuilder video() {
        scope.append("video,");
        return this;
    }

    public VkScopeBuilder pages() {
        scope.append("pages,");
        return this;
    }

    public VkScopeBuilder status() {
        scope.append("status,");
        return this;
    }

    public VkScopeBuilder notes() {
        scope.append("notes,");
        return this;
    }

    public VkScopeBuilder messages() {
        scope.append("messages,");
        return this;
    }

    public VkScopeBuilder wall() {
        scope.append("wall,");
        return this;
    }

    public VkScopeBuilder ads() {
        scope.append("ads,");
        return this;
    }

    public VkScopeBuilder offline() {
        scope.append("offline,");
        return this;
    }

    public VkScopeBuilder docs() {
        scope.append("docs,");
        return this;
    }

    public VkScopeBuilder groups() {
        scope.append("groups,");
        return this;
    }

    public VkScopeBuilder notifications() {
        scope.append("notifications,");
        return this;
    }

    public VkScopeBuilder stats() {
        scope.append("stats,");
        return this;
    }

    public VkScopeBuilder email() {
        scope.append("email,");
        return this;
    }

    public VkScopeBuilder market() {
        scope.append("market,");
        return this;
    }

    public VkScopeBuilder photos() {
        scope.append("photos,");
        return this;
    }

    public VkScopeBuilder manage() {
        scope.append("manage,");
        return this;
    }


    public String build() {
        return scope.deleteCharAt(scope.length() - 1).toString();
    }


}
