package se.uu.it.basiclearning.dfa;

import java.io.IOException;
import java.util.Collection;

import com.google.common.collect.ImmutableSet;

import de.learnlib.api.oracle.MembershipOracle.DFAMembershipOracle;
import se.uu.it.basiclearning.LearnerConfig;

public class ExampleDFAExperiment {
    /**
     * Example of how to call a learner in a simple way with this class. Learns the ExampleDFAOracle.
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {
        DFAMembershipOracle<String> sulOracle = new ExampleDFAOracle("(ab)*");

        // the input alphabet
        Collection<String> inputAlphabet = ImmutableSet.of("a", "b", "c");

        LearnerConfig config = new LearnerConfig();
        DFALearner<String> learner = new DFALearner<String>(config, sulOracle);
        learner.runExperiment(inputAlphabet);
    }
}
