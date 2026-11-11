package com.cognitionbox.petra.stdlib.collections.arrays.verifications;

import com.cognitionbox.petra.ast.interp.PetraVerification;
import com.cognitionbox.petra.ast.interp.junit.tasks.PetraTask;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrayListVerification extends PetraVerification {
    public ArrayListVerification(PetraTask task) {
        super(task);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection tasks() {
        return verifyBase("com/cognitionbox/petra/stdlib/collections/", "ArrayListVerificationUnit", Language.JAVA);
    }
}
