package com.cognitionbox.petra.stdlib.ast.interp;

import com.cognitionbox.petra.stdlib.ast.interp.junit.tasks.PetraTask;

import java.util.Collection;

public class PetraVerification {
    public PetraVerification(PetraTask task) {}

    protected static Collection verifyBase(String s, String intArrayVerificationTest, Language language) {return null;}

    protected enum Language {
        JAVA,
        CPP
    }

    protected enum Mode {
        VERIFY_COORD_BASE,
        ASSUME_COORD_BASE;
    }
}
