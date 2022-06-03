package se.uu.it.basiclearning.dfa;

import java.io.IOException;
import java.util.Collection;

import com.beust.jcommander.JCommander;
import com.google.common.collect.ImmutableSet;

import de.learnlib.api.oracle.MembershipOracle.DFAMembershipOracle;
import se.uu.it.basiclearning.LearnerConfig;

public class ExampleDFAExperiment {
    /**
     * Example of how to call a learner in a simple way with this class. Learns the ExampleDFAOracle.
     * Supply the option "-h" to show usage page. 
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {
        // DFAMembershipOracle<String> sulOracle = new ExampleDFAOracle("(ab)*");
        DFAMembershipOracle<String> sulOracle = new ExampleDFAOracle();
        // the input alphabet
        // Collection<String> inputAlphabet = ImmutableSet.of("a", "b", "c");
        Collection<String> inputAlphabet = ImmutableSet.of("r0", "r1", "w0","w1");

        LearnerConfig config = new LearnerConfig();
        JCommander commander = new JCommander(config);
        commander.parse(args);
        if (config.isHelp()) {
        	commander.usage();
        } else {
	        DFALearner<String> learner = new DFALearner<String>(config, sulOracle);
	        learner.runExperiment(inputAlphabet);
        }
        
    }
}
