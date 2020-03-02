package es.datastructur.synthesizer;

import java.util.Arrays;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        buffer = new ArrayRingBuffer<>((int) Math.round(SR/frequency));
        while (buffer.fillCount() < buffer.capacity()) {
            buffer.enqueue(0.0);
        }
        System.out.println(buffer.fillCount() + " " + buffer.capacity());
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other.
        while (buffer.fillCount() > 0) {
            buffer.dequeue();
        }
        System.out.println(buffer.fillCount() + " " + buffer.capacity());
        while (buffer.fillCount() < buffer.capacity()) {
            double r = Math.random() - 0.5;
//            boolean b = Arrays.asList(buffer).contains(r);
            if (Arrays.asList(buffer).contains(r)) {
                continue;
            }
            buffer.enqueue(r);
//            System.out.println("org " + r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        /*for (int i=0; i < buffer.capacity(); i++) {
            double temp = buffer.dequeue();
            double newDouble = (temp + buffer.peek())/2;
            buffer.enqueue(newDouble);
//            System.out.println("new " + newDouble);
        }*/

        double temp = buffer.dequeue();
        double newDouble = (temp + buffer.peek())/2;
        buffer.enqueue(newDouble);

    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.peek();
    }

    public static void main(String[] args) {
        GuitarString G = new GuitarString(11025);
        G.pluck();
        G.tic();
    }
}
    // TODO: Remove all comments that say TODO when you're done.
