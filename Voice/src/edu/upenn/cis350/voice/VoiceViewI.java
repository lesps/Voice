package edu.upenn.cis350.voice;

public interface VoiceViewI {
	/**
	 * Returns an answer in the range of 0 to 10
	 * @return An integer in the range of 0 to 10
	 */
	public int getAnswer();
	
	/**
	 * Accepts an answer in the range of 0 to 10 and sets the corresponding
	 * VoiceView to visualize that answer
	 * @param i The answer to be visualized
	 */
	public void setAnswer(int i);
	
	/**
	 * Animates the view after an answer has been received. This method must
	 * receive control and maintain it while the animation is playing, then return
	 * the control to the calling function.
	 */
	public void animate();
}
