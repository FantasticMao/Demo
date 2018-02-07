package priv.mm.thread.simulation;

import priv.mm.thread.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Producer and Consumer without BlockingQueue
 * 生产者与消费者问题分析
 * <ul>
 * <li>任何时刻只能有一个线程访问缓冲区</li>
 * <li>缓冲区为空时，消费者必须等待生产者</li>
 * <li>缓冲区为满时，生产者必须等待消费者</li>
 * </ul>
 *
 * @author maomao
 * @see Buffer 缓冲区
 * @see Producer 生产者
 * @see Consumer 消费者
 * @since 2016/11/10
 */
public class ProducerAndConsumer {

    private static class Buffer {
        private Semaphore mutex; // 互斥信号量
        private Semaphore fullBuffers; // 消费信号量
        private Semaphore emptyBuffers; // 生产信号量

        public Buffer(final int capacity) {
            this.mutex = new Semaphore(1);
            this.fullBuffers = new Semaphore(0);
            this.emptyBuffers = new Semaphore(capacity);
        }

        public void deposit() {
            emptyBuffers.p();
            mutex.p();
            System.out.println("produce ...");
            mutex.v();
            fullBuffers.v();
        }

        public void remove() {
            fullBuffers.p();
            mutex.p();
            System.out.println("consume ...");
            mutex.v();
            emptyBuffers.v();
        }
    }

    private static class Producer implements Runnable {
        private final Buffer buffer;

        Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                buffer.deposit();
            }
        }
    }

    private static class Consumer implements Runnable {
        private final Buffer buffer;

        Consumer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                buffer.remove();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Buffer buffer = new Buffer(2);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Producer(buffer));
        exec.execute(new Consumer(buffer));
        TimeUnit.MILLISECONDS.sleep(1);
        exec.shutdownNow();
    }
}
