/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej;

public class Validation {
    private Validation() {
    }

    public static <T> void checkParameterIsNotNull(@Nullable T obj, @NotNull String paramName) {
        if (obj == null) {
            throw new NullPointerException("Parameter " + paramName + " must not be null !!!");
        }
    }
}
