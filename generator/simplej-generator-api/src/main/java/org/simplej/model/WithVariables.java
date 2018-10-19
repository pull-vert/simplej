/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej.model;

import java.util.List;

@FunctionalInterface
public interface WithVariables {
    List<Variable> getVariables();
}
