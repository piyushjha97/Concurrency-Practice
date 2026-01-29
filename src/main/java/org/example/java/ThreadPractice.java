package org.example.java;

import java.util.concurrent.*;
import java.util.logging.Logger;



public class ThreadPractice {


    private static final Logger logger = Logger.getLogger("LocalLogger");

    private int counter = 0;

    ExecutorService executorService = Executors.newFixedThreadPool(2);


    public void  run(int ig) throws ExecutionException, InterruptedException {
        counter = 0;
        Callable<Integer> task1 = () -> {
          //  Thread.sleep(1000);
            counter+=10;
            return counter;
        };

        Callable<Integer> task2 = () -> {
          //  Thread.sleep(5000);
            counter-=3;
            return counter;
        };

        Future<Integer> result1 = executorService.submit(task1);
        Future<Integer> result2 = executorService.submit(task2);



        try {
            Integer i = result1.get();

           // result2.cancel(true);
          //  logger.info("output of adder: "+ i);

            Integer j = result2.get();
           // logger.info("output of subtractor: "+ j);


            if (i == 10 && j == -3) {
                logger.info(ig+": "+ "condition reached!!!!!");
            }
        } catch (Exception e) {
            logger.info("logging error ");
            throw e;
        } finally {
           // executorService.shutdown();
        }
    }

    public void perform() throws ExecutionException, InterruptedException {
        for (int i = 0;i<1000;i++) {
            run(i);
        }
    }
}
