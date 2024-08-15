package ss4_ClassAndObject.bai_tap.StopWatch;

import java.util.Random;

class StopWatch {
    private long startTime;
    private long endTime;

    public StopWatch() {
        this.startTime = System.currentTimeMillis();
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {
        this.endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return this.endTime - this.startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}

public class SelectionSortTimer {

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100000];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100000);
        }

        StopWatch sw = new StopWatch();

        sw.start();

        selectionSort(array);

        sw.stop();

        System.out.println("Star Time: " + sw.getStartTime() );
        System.out.println("End Time: " + sw.getEndTime() );
        System.out.println("Program execution time:  " + sw.getElapsedTime() + " milisecond");
    }
}
