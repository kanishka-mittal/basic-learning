package se.uu.it.basiclearning.dfa;

import com.google.common.collect.Lists;

import de.learnlib.acex.analyzers.AcexAnalyzers;
import de.learnlib.algorithms.kv.dfa.KearnsVaziraniDFA;
import de.learnlib.algorithms.lstar.ce.ObservationTableCEXHandlers;
import de.learnlib.algorithms.lstar.closing.ClosingStrategies;
import de.learnlib.algorithms.lstar.dfa.ExtensibleLStarDFA;
import de.learnlib.algorithms.ttt.dfa.TTTLearnerDFA;
import de.learnlib.api.algorithm.LearningAlgorithm;
import de.learnlib.api.oracle.EquivalenceOracle;
import de.learnlib.api.oracle.MembershipOracle.DFAMembershipOracle;
import de.learnlib.filter.cache.dfa.DFAHashCacheOracle;
import de.learnlib.filter.statistic.Counter;
import de.learnlib.filter.statistic.oracle.DFACounterOracle;
import de.learnlib.oracle.equivalence.DFAWMethodEQOracle;
import de.learnlib.oracle.equivalence.DFAWpMethodEQOracle;
import de.learnlib.oracle.equivalence.RandomWpMethodEQOracle;
import net.automatalib.automata.fsa.DFA;
import net.automatalib.words.Alphabet;
import net.automatalib.words.Word;
import se.uu.it.basiclearning.BasicLearner;
import se.uu.it.basiclearning.LearnerConfig;
import se.uu.it.basiclearning.LearningSetup;

public class DFALearner<I> extends BasicLearner<I, Boolean, DFA<?, I>> {

	private DFAMembershipOracle<I> sulOracle;

	public DFALearner(LearnerConfig config, DFAMembershipOracle<I> sulOracle) {
		super(config);
		this.sulOracle = sulOracle;
	}
	

	private LearningAlgorithm<DFA<?, I>, I, Boolean> loadLearner(DFAMembershipOracle<I> sulOracle,
			Alphabet<I> alphabet) {
		switch (config.getLearningMethod()) {
		case LStar:
			return new ExtensibleLStarDFA<I>(alphabet, sulOracle, Lists.<Word<I>>newArrayList(),
					ObservationTableCEXHandlers.CLASSIC_LSTAR, ClosingStrategies.CLOSE_SHORTEST);
		case RivestSchapire:
			return new ExtensibleLStarDFA<I>(alphabet, sulOracle, Lists.<Word<I>>newArrayList(),
					ObservationTableCEXHandlers.RIVEST_SCHAPIRE, ClosingStrategies.CLOSE_SHORTEST);
		case TTT:
			return new TTTLearnerDFA<I>(alphabet, sulOracle, AcexAnalyzers.LINEAR_FWD);
		case KearnsVazirani:
			return new KearnsVaziraniDFA<I>(alphabet, sulOracle, false, AcexAnalyzers.LINEAR_FWD);
		default:
			throw new RuntimeException("Learner not supported for this method");
		}
	}

	private EquivalenceOracle<DFA<?, I>, I, Boolean> loadTester(DFAMembershipOracle<I> sulOracle) {
		switch (config.getTestingMethod()) {
		// Other methods are somewhat smarter than random testing: state coverage,
		// trying to distinguish states, etc.
		case WMethod:
			return new DFAWMethodEQOracle<>(sulOracle, config.getMaxDepth());
		case WpMethod:
			return new DFAWpMethodEQOracle<>(sulOracle, config.getMaxDepth());
		case RandomWpMethod:
			return new RandomWpMethodEQOracle<>(sulOracle, config.getMinSize(), config.getRandLength(), config.getMaxSymbols());
		default:
			throw new RuntimeException("Tester not supported for this method");
		}
	}

	/**
	 * Helper class to configure a learning and equivalence oracle. Tell it which
	 * learning and testing method you want, and it produces the corresponding
	 * oracles (and counters for statistics) as attributes.
	 */
	public class DFALearningSetup extends LearningSetup<I, Boolean, DFA<?, I>> {
		public final EquivalenceOracle<DFA<?, I>, I, Boolean> eqOracle;
		public final LearningAlgorithm<DFA<?, I>, I, Boolean> learner;
		public final Counter nrSymbols, nrResets;

		public DFALearningSetup(Alphabet<I> alphabet) {
			DFASymbolCounterOracle<I> symbolCounterOracle = new DFASymbolCounterOracle<>(sulOracle, "symbol counter");
			DFACounterOracle<I> resetCounterOracle = new DFACounterOracle<>(symbolCounterOracle, "reset counter");
			DFAHashCacheOracle<I> cacheOracle = new DFAHashCacheOracle<>(resetCounterOracle);
			nrSymbols = symbolCounterOracle.getStatisticalData();
			nrResets = resetCounterOracle.getStatisticalData();

			// Choosing an equivalence oracle
			eqOracle = loadTester(cacheOracle);

			// Choosing a learner
			learner = loadLearner(cacheOracle, alphabet);
		}

		@Override
		public EquivalenceOracle<DFA<?, I>, I, Boolean> getEquivalenceOracle() {
			return eqOracle;
		}

		@Override
		public LearningAlgorithm<DFA<?, I>, I, Boolean> getLearningAlgorithm() {
			return learner;
		}

		@Override
		public Counter getResetCounter() {
			return nrResets;
		}

		@Override
		public Counter getSymbolCounter() {
			return nrSymbols;
		}
	}

	@Override
	protected LearningSetup<I, Boolean, DFA<?, I>> buildLearningSetup(Alphabet<I> alphabet) {
		DFALearner<I>.DFALearningSetup learningSetup = new DFALearningSetup(alphabet);
		return learningSetup;
	}

}
