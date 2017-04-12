package com.weblab.social;

import lombok.Getter;

/**
 * Created by amowel on 13.04.17.
 */
@Getter
public abstract class Social {

    protected Integer id;

    protected String name;

    protected boolean loginedIn;

    public void changeLoginedState() {
        loginedIn = !loginedIn;
    }
}
