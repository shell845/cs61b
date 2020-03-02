/**
 * @author YC 02/10/2020
 */

import es.datastructur.synthesizer.GuitarString;
import java.util.HashMap;

public class GuitarHero {
    private static final double BASE_FREQUENCY = 440.0;
//    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public HashMap<Character, Double> keyboardMap = new HashMap<Character, Double>();

    public GuitarHero() {
        char[] keyboardArray = keyboard.toCharArray();
        for (int i=0; i < keyboard.length(); i++) {
            keyboardMap.put(keyboardArray[i], BASE_FREQUENCY * Math.pow(2, (i - 24.0) / 12.0));
        }
    }


    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        double defaultFrequency = 400.0;
        GuitarString playGuitar = new GuitarString(defaultFrequency);
        GuitarHero GH = new GuitarHero();

        while (true) {

            StdDraw.setScale(-100, 100);
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (GH.keyboardMap.get(key) != null) {
                    playGuitar = new GuitarString(GH.keyboardMap.get(key));
                    playGuitar.pluck();
                    StdDraw.line(0, 0, (Math.random() - 0.5) * 100, (Math.random() - 0.5) * 100);
                }
            }

            /* compute the superposition of samples */
//            double sample = stringA.sample() + stringC.sample();
            double sample = playGuitar.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            playGuitar.tic();
        }
    }
}
