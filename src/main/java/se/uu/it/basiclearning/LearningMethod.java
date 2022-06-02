package se.uu.it.basiclearning;

/**
 * The learning algorithms. LStar is the basic algorithm, TTT performs much
 * faster but is a bit more inaccurate and produces more intermediate
 * hypotheses, so test well.
 */
public enum LearningMethod {
	LStar, RivestSchapire, TTT, KearnsVazirani
}
