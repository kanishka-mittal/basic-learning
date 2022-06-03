package se.uu.it.basiclearning;

import de.learnlib.api.algorithm.LearningAlgorithm;
import de.learnlib.api.oracle.EquivalenceOracle;
import de.learnlib.filter.statistic.Counter;
import net.automatalib.automata.Automaton;

public abstract class LearningSetup<I, D, M extends Automaton<?, I, ?>> {
	
	public abstract EquivalenceOracle<M, I, D> getEquivalenceOracle();
	
	public abstract LearningAlgorithm<M, I, D> getLearningAlgorithm();
	
	public abstract Counter getResetCounter();
	
	public abstract Counter getSymbolCounter();
}
