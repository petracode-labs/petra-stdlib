package com.cognitionbox.petra.stdlib.ast.interp.util;

import com.cognitionbox.petra.stdlib.ast.interp.util.reactive.EntryPoint;
import com.cognitionbox.petra.stdlib.ast.interp.util.reactive.Reactive;
import com.cognitionbox.petra.stdlib.ast.interp.util.reactive.Updateable;

public final class Program {

    public static void sep(Runnable... runnables){
        boolean parallelize = true;
        if (parallelize){
            par(runnables);
        } else {
            seq(runnables);
        }
    }

    public static void seq(Runnable... runnables){
        for (int i=0;i<runnables.length;i++){
            runnables[i].run();
        }
    }

    public static void par(Runnable... runnables){
        if (runnables.length==1){
            runnables[0].run();
            return;
        }
        Thread[] threads = new Thread[runnables.length];
        for (int i=0;i<runnables.length;i++){
            threads[i] = new Thread(runnables[i]);
            threads[i].start();
        }
        for (int i=0;i<threads.length;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void start(Runnable entryPoint){
        entryPoint.run();
    }

    public static void startReactive(long iterationPauseInMilliseconds, Runnable entryPoint, Runnable... runnables){
        startReactive(Long.MAX_VALUE,iterationPauseInMilliseconds,entryPoint,runnables);
    }

    private static int count = 0;
    public static void startReactive(long iterations, long iterationPauseInMilliseconds, Runnable entryPoint, Runnable... runnables) {
        while(count<iterations) {
            entryPoint.run();
            for(int i = 0; i < runnables.length; ++i) {
                Runnable r = runnables[i];
                r.run();
            }

            if (iterationPauseInMilliseconds > 0L) {
                try {
                    Thread.sleep(iterationPauseInMilliseconds);
                } catch (InterruptedException e) {}
            }
            count++;
        }
    }

    public static void startReactive(Reactive program, long iterationPauseInMilliseconds) {
        while(program.updateable()) {
            program.update();
            program.main();
            if (iterationPauseInMilliseconds > 0L) {
                try {
                    Thread.sleep(iterationPauseInMilliseconds);
                } catch (InterruptedException e) {}
            }
            count++;
        }
    }

    public static void startReactive(Reactive program){
        startReactive(program,0);
    }

    public static void startReactive(EntryPoint program, long iterationPauseInMilliseconds, Updateable... updateable) {
        while(true) {
            boolean update = true;
            for (Updateable u : updateable){
                update = update && u.updateable();
            }
            if (!update){
                return;
            }
            for (Updateable u : updateable){
                u.update();
            }
            program.main();
            if (iterationPauseInMilliseconds > 0L) {
                try {
                    Thread.sleep(iterationPauseInMilliseconds);
                } catch (InterruptedException e) {}
            }
            count++;
        }
    }

    public static void startReactive(EntryPoint program, Updateable... updateable){
        startReactive(program,0,updateable);
    }

}
