package org.wildstang.framework.pid;

/**
 * Represents the 4 PID constants: F, P, I, and D.
 * Feed-forward-proportional-integral-derivative controller
 * https://en.wikipedia.org/wiki/PID_controller#Feed-forward
 */
public class PIDConstants {

    public final double f;
    public final double p;
    public final double i;
    public final double d;

    /**
     * Create a new PID constant set.
     * @param f Feed-forward - Major proportion of the output.
     * @param p Proportional - Proportional to the current value of the error.
     * @param i Integral - Accounts for past values of the error.
     * @param d Derivative - Best estimate of the future trend of the error.
     */
    public PIDConstants(double f, double p, double i, double d) {
        this.f = f;
        this.p = p;
        this.i = i;
        this.d = d;
    }
}
