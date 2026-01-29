package org.example;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class BasicPubSub {

    /*
        2 Threads - one Producer and one Consumer.
        Producer can produce max of 5 events, and it should notify the consumer as soon as it
        produces an event and consumer should not consumer empty queue. So it should notify the
        producer as soon as it finishes consuming an event and making space for the new event
     */

    private final Logger loggerUtil =  Logger.getLogger("local log");
    final Queue<Integer> messageQueue = new LinkedList<>();
    int capacity = 5;
    int offSet = 0;
    boolean producerDone = false;



    public void run() {

        loggerUtil.info("runner started");

        Thread producer = new Thread(this::produceEvent);

        Thread consumer = new Thread(this::consumerEvent);


        //starting both components
        producer.start();
        consumer.start();
    }


    void produceEvent()  {

            while(true) {
                synchronized(messageQueue) {
                    while (messageQueue.size() == capacity) {
                        try {
                            messageQueue.wait();
                        } catch (Exception e) {
                            loggerUtil.info("exception from producer");
                        }
                    }
                        messageQueue.add(offSet++);
                        loggerUtil.info("produced event at offset: " + offSet);
                        messageQueue.notifyAll();
                    }

               try {
                   Thread.sleep(10);
               } catch (Exception e) {

               }

            }
    }

    void consumerEvent() {
        while (true) {
            synchronized (messageQueue) {
                while (messageQueue.isEmpty()) {
                    try {
                        messageQueue.wait();
                    } catch (Exception e) {
                        loggerUtil.info("exception from consumer");
                    }
                }
                    loggerUtil.info("consuming event at offset: "+ messageQueue.poll());
                    messageQueue.notifyAll();
            }
            try {
                Thread.sleep(300);
            } catch (Exception e) {

            }
        }
    }
}
