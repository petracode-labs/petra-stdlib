package com.cognitionbox.petra.ast.interp;

public interface VerificationUnit<T> {
    T target();

    default void assertTrue(Runnable runnable){
        runnable.run();
    }

    default void assertFalse(Runnable runnable){
        try {
            runnable.run();
        } catch (AssertionError e){

        }
    }
}
