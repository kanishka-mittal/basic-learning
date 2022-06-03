package se.uu.it.basiclearning;

public class LearnerConfig {
	/**
	 * For random walk, the chance to reset after every input
	 */
	private double resetProbability = 0.1;
	
	/**
	 * For random walk and Random-Wp, the number of symbols that is tested in total (maybe with resets in between).
	 */
	private int maxSymbols = 1000;
	
	/**
	 * MaxDepth-parameter for W-method and Wp-method. This acts as the parameter 'n' for an n-complete test suite.
	 * Typically not larger than 3. Decrease for quicker runs.
	 */
	private int maxDepth = 2;
	
	/**
	 * minimum size of test query for Random Wp-method 
	 */
	private int minSize = 5;
	
	/**
	 * maximum size of test query for Random Wp-method 
	 */
	private int randLength = 20;
	
	/**
	 * Output directory where to store learned model and intermediary hypotheses.
	 */
	public String outputDir = "output";
	
	/**
	 * Random seed to use.
	 */
	private long seed = 0;
	
	/**
	 * Learning method to use
	 */
	private LearningMethod learningMethod = LearningMethod.TTT;
	
	/**
	 * Testing method to use
	 */
	private TestingMethod testingMethod = TestingMethod.RandomWpMethod;
	
	public void setResetProbability(double resetProbability) {
		this.resetProbability = resetProbability;
	}

	public void setMaxSymbols(int maxSymbols) {
		this.maxSymbols = maxSymbols;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
	


	public LearningMethod getLearningMethod() {
		return learningMethod;
	}

	public void setLearningMethod(LearningMethod learningMethod) {
		this.learningMethod = learningMethod;
	}

	public TestingMethod getTestingMethod() {
		return testingMethod;
	}

	public void setTestingMethod(TestingMethod testingMethod) {
		this.testingMethod = testingMethod;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public double getResetProbability() {
		return resetProbability;
	}

	public int getMaxSymbols() {
		return maxSymbols;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public String getOutputDir() {
		return outputDir;
	}
	
	public long getSeed() {
		return seed;
	}
	
	public int getMinSize() {
		return minSize;
	}

	public int getRandLength() {
		return randLength;
	}

}
