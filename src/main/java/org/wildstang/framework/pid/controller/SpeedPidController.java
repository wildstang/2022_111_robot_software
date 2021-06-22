package org.wildstang.framework.pid.controller;

import org.wildstang.framework.pid.input.IPidInput;
import org.wildstang.framework.pid.output.IPidOutput;

/**
 * Represents a PID controller for motor speed control.
 * @author chadschmidt
 */
public class SpeedPidController extends PidController {

    /**
     * Passes all parameters on to PidController().
     * @param source IPidInput for PID control.
     * @param output IPidOutput for PID control.
     * @param pidControllerName Name of the controller.
     */
    public SpeedPidController(IPidInput source, IPidOutput output, String pidControllerName) {
        super(source, output, pidControllerName);
    }

    /**
     * Calculates the derivative term D based off errors.
     * Assumes target velocity == 0.
     * @return Estimated derivative term D.
     */
    @Override
    protected double calcDerivativeTerm() {
        double d_term = this.getD() * ((this.getError() - this.getPreviousError()));
        double differentiatorBandLimit = this.getDifferentiatorBandLimit();
        // Band-limit the differential term
        if (Math.abs(d_term) > differentiatorBandLimit) {
            d_term = (d_term > 0.0f) ? differentiatorBandLimit : -differentiatorBandLimit;
        }
        return d_term;
    }

}
