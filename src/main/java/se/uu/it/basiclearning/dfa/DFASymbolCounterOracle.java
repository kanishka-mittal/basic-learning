package se.uu.it.basiclearning.dfa;

import java.util.Collection;

import de.learnlib.api.oracle.MembershipOracle;
import de.learnlib.api.query.Query;
import de.learnlib.api.statistic.StatisticOracle;
import de.learnlib.filter.statistic.Counter;

public class DFASymbolCounterOracle <I>  implements StatisticOracle<I, Boolean>, MembershipOracle.DFAMembershipOracle<I> {

	private MembershipOracle<I, Boolean> oracle;
	private Counter counter;

	public DFASymbolCounterOracle(MembershipOracle<I, Boolean> oracle, String name) {
		this.oracle = oracle;
		this.counter = new Counter(name, "symbols");
	}
	
	@Override
	public void setNext(MembershipOracle<I, Boolean> next) {
		this.oracle = next;
	}

	@Override
	public void processQueries(Collection<? extends Query<I, Boolean>> queries) {
		oracle.processQueries(queries);
		queries.forEach(q -> counter.increment(q.getInput().length()));
	}

	@Override
	public Counter getStatisticalData() {
		return counter;
	}

}
