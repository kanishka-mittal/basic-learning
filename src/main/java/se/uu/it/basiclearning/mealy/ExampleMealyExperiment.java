package se.uu.it.basiclearning.mealy;

import java.io.IOException;
import java.util.Collection;

import com.google.common.collect.ImmutableSet;

import de.learnlib.api.SUL;
import se.uu.it.basiclearning.LearnerConfig;

/**
 * Created by ramon on 13-12-16.
 */
public class ExampleMealyExperiment {
    /**
     * Example of how to call a learner in a simple way with this class. Learns the ExampleSUL.
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {
        // Load the actual SUL-class
        // For a SUL over a socket, use the SocketSUL-class
        // You can also program an own SUL-class if you extend SUL<String,String> (or SUL<S,T> in
        // general, with S and T the input and output types - but this class assumes strings)
        SUL<String,String> sul = new ExampleSUL();

        // the input alphabet
        Collection<String> inputAlphabet = ImmutableSet.of("a", "b", "c");

        try {
        	LearnerConfig config = new LearnerConfig();
        	MealyLearner<String, String> learner = new MealyLearner<String, String>(config, sul);
        	learner.runExperiment(inputAlphabet);
        } finally {
            if (sul instanceof AutoCloseable) {
                try {
                    ((AutoCloseable) sul).close();
                } catch (Exception exception) {
                    // should not happen
                }
            }
        }
    }
}
