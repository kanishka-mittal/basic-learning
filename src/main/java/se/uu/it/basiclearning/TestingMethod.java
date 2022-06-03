package se.uu.it.basiclearning;

/**
 * The testing algorithms. Random walk is the simplest, but may perform badly on
 * large models: the chance of hitting a hard-to-reach transition is very small.
 * WMethod and WpMethod are smarter. 
 */
public enum TestingMethod {
	RandomWalk, WMethod, WpMethod, RandomWpMethod;
}
