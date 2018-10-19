/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej.model;

import java.util.List;

public final class Interface implements WithVisibility, Named, WithFunctions {
    private Visibility visibility;
    private String name;
    private List<Function> functions;

    @Override
    public Visibility getVisibility() {
        return visibility;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Function> getFunctions() {
        return functions;
    }
}
