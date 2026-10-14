package com.cognitionbox.petra.stdlib.collections;

import com.cognitionbox.petra.ast.interp.PetraVerification;
import com.cognitionbox.petra.ast.interp.junit.tasks.PetraTask;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

@RunWith(Parameterized.class)
public class IntArrayVerification extends PetraVerification {
    public IntArrayVerification(PetraTask task) {
        super(task);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection tasks() {
        return verifyJava("com/cognitionbox/petra/stdlib/collections/", "IntArray", true);
    }

}
