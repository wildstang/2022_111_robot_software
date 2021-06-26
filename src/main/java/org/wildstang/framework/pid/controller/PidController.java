package org.wildstang.framework.pid.controller;

import org.wildstang.framework.core.Core;
import org.wildstang.framework.pid.input.IPidInput;
import org.wildstang.framework.pid.output.IPidOutput;

import edu.wpi.first.wpilibj.Timer;

/**
 * Controller for PID motor control.
 * @author Nathan
 */
public class PidController {

    private double p; // P coefficient
    private double i; // I coefficient
    private double d; // D coefficient
    private double currentError; // Current error
    private double previousError; // Previous error
    private double setPoint; // The set point
    private double errorSum; // Sum of previous errors (for I calculation)
    private double errorIncrement; // Max increment to error sum each call
    private double errorEpsilon; // Allowable error in determining when done
    private double staticEpsilon; // Allowable error in steady state. Used to
    // account for small acceptable motor
    // backdrive
    private double maxIntegral;
    private double integralErrorThresh;
    private double differentiatorBandLimit;
    private double maxOutput; // Ceiling on calculation output
    private double minOutput;
    private double maxInput; // Ceiling on calculation input
    private double minInput;
    private PidStateType currentState; // State of the pid for calculating I
    private double minOnTargetTime; // Minimum number of cycles in epsilon range
    // to be done
    private boolean allowStaticEpsilon;
    private Timer stabilizationTimer;
    private IPidInput pidSource;
    private IPidOutput pidOutput;
    private String controllerName;
    private double output;
    // Prevents the output value from being written directly to the output
    private boolean outputEnabled;
    private double p_config;
    private double i_config;
    private double d_config;
    private double errorIncrement_config;
    private double errorEpsilon_config;
    private double staticEpsilon_config;
    private double maxIntegral_config;
    private double integralErrorThresh_config;
    private double differentiatorBandLimit_config;
    private double maxOutput_config;
    private double minOutput_config;
    private double maxInput_config;
    private double minInput_config;

    /**
     * Constructs and initialized controller values.
     * @param source IPidInput for PID control.
     * @param output IPidOutput for PID control.
     * @param pidControllerName Name of the controller.
     */
    public PidController(IPidInput source, IPidOutput output, String pidControllerName) {
        p = 1.0;
        i = 0.0;
        d = 0.0;
        currentError = 0.0;
        previousError = 0.0;
        setPoint = 0.0;
        errorSum = 0.0;
        errorIncrement = 1.0;
        errorEpsilon = 1.0;
        staticEpsilon = 0.0;
        maxIntegral = 1.0;
        integralErrorThresh = -1.0;
        differentiatorBandLimit = 1.0;
        maxOutput = 1.0;
        minOutput = -1.0;
        maxInput = 1000.0;
        minInput = -1.0;
        currentState = PidStateType.PID_INITIALIZE_STATE;
        minOnTargetTime = 0.2;
        allowStaticEpsilon = false;
        stabilizationTimer = new Timer();
        pidSource = source;
        pidOutput = output;
        controllerName = pidControllerName;

        p_config = Core.getConfigManager().getConfig()
                .getDouble(this.getClass().getName() + "." + pidControllerName + ".p", 0.0);
        i_config = Core.getConfigManager().getConfig()
                .getDouble(this.getClass().getName() + "." + pidControllerName + ".i", 0.0);
        d_config = Core.getConfigManager().getConfig()
                .getDouble(this.getClass().getName() + "." + pidControllerName + ".d", 0.0);
        errorIncrement_config = Core.getConfigManager().getConfig().getDouble(
                this.getClass().getName() + "." + pidControllerName + ".errorIncrement", 0.0);
        errorEpsilon_config = Core.getConfigManager().getConfig().getDouble(
                this.getClass().getName() + "." + pidControllerName + ".errorEpsilon", 0.0);
        staticEpsilon_config = Core.getConfigManager().getConfig().getDouble(
                this.getClass().getName() + "." + pidControllerName + ".staticEpsilon", 0.0);
        maxIntegral_config = Core.getConfigManager().getConfig().getDouble(
                this.getClass().getName() + "." + pidControllerName + ".maxIntegral", 0.0);
        integralErrorThresh_config = Core.getConfigManager().getConfig().getDouble(
                this.getClass().getName() + "." + pidControllerName + ".integralErrorThresh", 0.0);
        differentiatorBandLimit_config = Core.getConfigManager().getConfig().getDouble(
                this.getClass().getName() + "." + pidControllerName + ".differentiatorBandLimit",
                0.0);
        maxOutput_config = Core.getConfigManager().getConfig()
                .getDouble(this.getClass().getName() + "." + pidControllerName + ".maxOutput", 0.0);
        minOutput_config = Core.getConfigManager().getConfig()
                .getDouble(this.getClass().getName() + "." + pidControllerName + ".minOutput", 0.0);
        maxInput_config = Core.getConfigManager().getConfig()
                .getDouble(this.getClass().getName() + "." + pidControllerName + ".maxInput", 0.0);
        minInput_config = Core.getConfigManager().getConfig()
                .getDouble(this.getClass().getName() + "." + pidControllerName + ".minInput", 0.0);

        this.setErrorIncrementPercentage(errorIncrement);
    }

    /**
     * Returns the P term.
     * @return The P (proportional) term.
     */
    public double getP() {
        return p;
    }

    /**
     * Returns the I term.
     * @return The I (integral) term.
     */
    public double getI() {
        return i;
    }

    /**
     * Returns the D term.
     * @return The D (derivative) term.
     */
    public double getD() {
        return d;
    }

    /**
     * Returns epsilon.
     * @return Epsilon, the allowable error in completion.
     */
    public double getEpsilon() {
        return errorEpsilon;
    }

    /**
     * Returns static epsilon.
     * @return Static epsilon, the allowable error in steady-state.
     */
    public double getStaticEpsilon() {
        return staticEpsilon;
    }

    /**
     * Set the 3 PID constants.
     * @param p Proportional - Proportional to the current value of the error.
     * @param i Integral - Accounts for past values of the error.
     * @param d Derivative - Best estimate of the future trend of the error.
     */
    public void setConstants(double p, double i, double d) {
        this.p = p;
        this.i = i;
        this.d = d;
    }

    /**
     * Sets static epsilon.
     * @param epsilon Static epsilon, the allowable error in steady-state.
     */
    public void setStaticEpsilon(double epsilon) {
        this.staticEpsilon = epsilon;
    }

    /**
     * Sets epsilon.
     * @param epsilon Epsilon, the allowable error in completion.
     */
    public void setErrorEpsilon(double epsilon) {
        this.errorEpsilon = epsilon;
    }

    /**
     * Sets error increment percentage.
     * @param inc Error increment percentage, the max error increment per call.
     */
    public void setErrorIncrementPercentage(double inc) {
        errorIncrement = ((maxOutput - minOutput) * (inc / 100.0f));
    }

    /**
     * Sets the min and max output.
     * @param min Minimum output, floor on PID output.
     * @param max Maximum output, ceiling on PID output.
     */
    public void setMinMaxOutput(double min, double max) {
        if (min < max) {
            minOutput = min;
            maxOutput = max;
        }
    }

    /**
     * Sets the min and max input.
     * @param min Minimum input, floor on PID input.
     * @param max Maximum input, ceiling on PID input.
     */
    public void setMinMaxInput(double min, double max) {
        if (min < max) {
            minInput = min;
            maxInput = max;
        }
    }

    /**
     * Sets the max integral.
     * @param max Max integral.
     */
    public void setMaxIntegral(double max) {
        maxIntegral = Math.abs(max);
    }

    /**
     * Sets the integral error threshold.
     * @param thresh Intergral error threshold.
     */
    public void setIntegralErrorThresh(double thresh) {
        integralErrorThresh = Math.abs(thresh);
    }

    /**
     * Returns the integral error threshold.
     * @return Intergral error threshold.
     */
    public double getIntegralErrorThresh() {
        return integralErrorThresh;
    }

    /**
     * Sets the min stabilization time.
     * @param time New min stabilization time.
     */
    public void setMinStabilizationTime(double time) {
        minOnTargetTime = time;
    }

    /**
     * Sets the set point.
     * @param set_point The new set point.
     */
    public void setSetPoint(double set_point) {
        this.setPoint = set_point;
    }

    /**
     * Returns the set point value.
     * @return The current set point value.
     */
    public double getSetPoint() {
        return setPoint;
    }

    /**
     * Sets the differentiator band limit.
     * @param band_limit The new differentiator band limit.
     */
    public void setDifferentiatorBandLimit(double band_limit) {
        differentiatorBandLimit = band_limit;
    }

    /**
     * Returns the differentiator band limit.
     * @return The current differentiator band limit.
     */
    public double getDifferentiatorBandLimit() {
        return differentiatorBandLimit;
    }

    /**
     * Resets the error sum to 0.
     */
    public void resetErrorSum() {
        errorSum = 0.0;
    }

    /**
     * Returns the current output value.
     * @return The current output.
     */
    public double getCurrentOutput() {
        return output;
    }

    /**
     * Set output to enabled or disabled.
     * @param outputEnabled True if set to enabled.
     */
    public void setOutputEnabled(boolean outputEnabled) {
        this.outputEnabled = outputEnabled;
    }

    /**
     * Calculate PID.
     */
    public void calcPid() {
        PidStateType new_state = currentState;

        // Read the value of the process variable under control and limit it
        double current_pv = pidSource.pidRead();
        current_pv = this.limitInput(current_pv);

        // Calculate the current error term
        currentError = setPoint - current_pv;
        System.out.println(this.controllerName + " error: " + currentError);

        // Adjust our metrics depending on where the process variable is as
        // compared
        // to the set point.
        if (currentState == PidStateType.PID_DISABLED_STATE) {
            // PID controller is disabled, nothing to do here...
            // reset everything now.
            this.reset();
            return;
        }
        else if (currentState == PidStateType.PID_INITIALIZE_STATE) {
            // Don't look at the D-term when we're just starting up
            previousError = currentError;

            // Find the next state
            if (currentError <= -errorEpsilon) {
                //
                // Error is negative and outside the epsilon band.
                // Negative errors mean we are above our setpoint
                //
                new_state = PidStateType.PID_ABOVE_TARGET_STATE;
            }
            else if (currentError >= errorEpsilon) {
                //
                // Positive Errors mean we are below our setpoint
                //
                new_state = PidStateType.PID_BELOW_TARGET_STATE;
            }
            else if ((currentError >= (-1 * errorEpsilon)) && (currentError <= errorEpsilon)) {
                new_state = PidStateType.PID_ON_TARGET_STATE;
                stabilizationTimer.reset();
                stabilizationTimer.start();
            }
            else {
                // You had better hope this does not happen
            }

        }
        else if (currentState == PidStateType.PID_BELOW_TARGET_STATE) {
            // In this case, we were above and we switched to below
            if (errorSum < 0) {
                // If we are fighting away from the point, reset the error.
                errorSum = 0;
            }

            if (currentError < errorIncrement) {
                // If the error is smaller than the max increment amount, add
                // it.
                errorSum += currentError;
            }
            else {
                // Otherwise, add the maximum increment per cycle.
                errorSum += errorIncrement;
            }
            if (currentError >= staticEpsilon) {
                allowStaticEpsilon = false;
            }

            // Find the next state
            // Error is inside the epsilon band.
            if ((currentError >= (-1 * errorEpsilon)) && (currentError <= errorEpsilon)) {
                new_state = PidStateType.PID_ON_TARGET_STATE;
                stabilizationTimer.reset();
                stabilizationTimer.start();
            } // Error is negative and outside the epsilon band.
            else if (currentError <= -errorEpsilon) {
                new_state = PidStateType.PID_ABOVE_TARGET_STATE;

                // Reset the error sum
                errorSum = 0.0;
            }
            else {
                // Stay here.
                new_state = PidStateType.PID_BELOW_TARGET_STATE;
            }

        }
        else if (currentState == PidStateType.PID_ON_TARGET_STATE) {
            errorSum = 0.0;
            allowStaticEpsilon = true;

            // Find the next state
            // Error is positive and outside the epsilon band.
            if (currentError >= errorEpsilon) {
                new_state = PidStateType.PID_BELOW_TARGET_STATE;
                stabilizationTimer.stop();
                stabilizationTimer.reset();
            }
            else if (currentError <= -errorEpsilon) {
                new_state = PidStateType.PID_ABOVE_TARGET_STATE;
                stabilizationTimer.stop();
                stabilizationTimer.reset();
            }
            else if (true == (stabilizationTimer.get() > minOnTargetTime)) {
                new_state = PidStateType.PID_STABILIZED_STATE;
                stabilizationTimer.stop();
                stabilizationTimer.reset();
            }
            else {
                // Stay right here, we are on target, but not long enough yet...
                new_state = PidStateType.PID_ON_TARGET_STATE;
            }
        }
        else if (currentState == PidStateType.PID_STABILIZED_STATE) {
            errorSum = 0.0;
            allowStaticEpsilon = true;

            // Find the next state
            // Error is positive and outside the epsilon band.
            if (currentError >= errorEpsilon) {
                new_state = PidStateType.PID_BELOW_TARGET_STATE;
            }
            else if (currentError <= -errorEpsilon) {
                new_state = PidStateType.PID_ABOVE_TARGET_STATE;
            }
            else {
                new_state = PidStateType.PID_STABILIZED_STATE;
            }

        } else if (currentState == PidStateType.PID_ABOVE_TARGET_STATE) {
            // In this case, we were below and we just switched to above
            if (errorSum > 0) {
                // If we are fighting away from the point, reset the error.
                errorSum = 0;
            }
            if (currentError > -errorIncrement) {
                // If the error is smaller than the max increment amount, add it.
                errorSum += currentError;
            }
            else {
                // Otherwise, subtract the maximum increment per cycle.
                errorSum += -errorIncrement;
            }

            if (currentError <= -staticEpsilon) {
                // Check to see if we are outside the static epsilon region
                allowStaticEpsilon = false;
            }

            // Find the next state
            // Error is inside the epsilon band.
            if ((currentError >= (-1 * errorEpsilon)) && (currentError <= errorEpsilon)) {
                new_state = PidStateType.PID_ON_TARGET_STATE;
                stabilizationTimer.reset();
                stabilizationTimer.start();
            }
            // Error is positive and outside the epsilon band.
            else if (currentError >= errorEpsilon) {
                new_state = PidStateType.PID_BELOW_TARGET_STATE;
                // Reset the error sum

                errorSum = 0.0;
            }
            else {
                new_state = PidStateType.PID_ABOVE_TARGET_STATE;
            }

        }
        else {
            // Invalid state
            new_state = PidStateType.PID_DISABLED_STATE;
        }

        currentState = new_state;

        // Finally, calculate the PID output
        double output = this.calcProportionalTerm() + this.calcIntegralTerm()
                + this.calcDerivativeTerm();

        // Handle Static Epsilon
        if ((allowStaticEpsilon == true) && Math.abs(currentError) < staticEpsilon) {
            output = 0;
            errorSum = 0;
        }

        // Clip the output to the allowable region
        output = this.limitOutput(output);

        // Write the pid output, if it's enabled
        if (outputEnabled) {
            pidOutput.pidWrite(output);
        }
        this.output = output;

        // Save the current error for next cycle's D calculation.
        previousError = currentError;
    }

    /**
     * Returns true if the controller is in the target or stabilized state.
     * @return True if the controller is target or stabilized.
     */
    public boolean isOnTarget() {
        boolean on_target = ((PidStateType.PID_ON_TARGET_STATE == currentState)
                || (PidStateType.PID_STABILIZED_STATE == currentState));
        return on_target;
    }

    /**
     * Returns true if the controller is in the stabilized state.
     * @return True if the controller is stabilized.
     */
    public boolean isStabilized() {
        boolean stabilized = (PidStateType.PID_STABILIZED_STATE == currentState);
        return stabilized;
    }

    /**
     * Returns true if the controller is in the enabled state.
     * @return True if the controller is enabled.
     */
    public boolean isEnabled() {
        return (PidStateType.PID_DISABLED_STATE != currentState);
    }

    /**
     * Puts the controller in the enabled state only if in the disabled state.
     */
    public void enable() {
        if (currentState == PidStateType.PID_DISABLED_STATE) {
            currentState = PidStateType.PID_INITIALIZE_STATE;
        }
    }

    /**
     * Resets and put the controller in the disabled state.
     */
    public void disable() {
        this.reset();
        currentState = PidStateType.PID_DISABLED_STATE;
    }

    /**
     * Resets all error values in controller.
     */
    public void reset() {
        errorSum = 0.0;
        currentError = 0.0;
        previousError = 0.0;
        allowStaticEpsilon = true;
    }

    /**
     * Returns the current error value.
     * @return The current error value.
     */
    public double getError() {
        return currentError;
    }

    /**
     * Returns the previous error value.
     * @return The previous error value.
     */
    public double getPreviousError() {
        return previousError;
    }

    /**
     * Calculates the proportional term P based off current error.
     * @return Estimated proportional term P.
     */
    private double calcProportionalTerm() {
        double p_term = p * currentError;
        return p_term;
    }

    /**
     * Calculates the integral term I based off current error.
     * @return Estimated integral term I.
     */
    private double calcIntegralTerm() {
        // Prevent Integral Wind Up.
        if (integralErrorThresh != -1) {
            if (currentError > integralErrorThresh || currentError < -integralErrorThresh) {
                this.resetErrorSum();
            }
        }
        double i_term = i * errorSum;
        if (i_term > maxIntegral) {
            i_term = maxIntegral;
        } else if (i_term < -maxIntegral) {
            i_term = -maxIntegral;
        } else {
            // Leave i-term alone
        }

        return i_term;
    }

    /**
     * Calculates the derivative term D based off errors.
     * @return Estimated derivative term D.
     */
    protected double calcDerivativeTerm() {
        double d_term = d * (currentError - previousError);

        // Band-limit the differential term
        if (Math.abs(d_term) > differentiatorBandLimit) {
            d_term = (d_term > 0.0f) ? differentiatorBandLimit : -differentiatorBandLimit;
        }
        return d_term;
    }

    /**
     * Limit an output value by the max and min value.
     * Effectively if too low the output is the min
     * and if too high the output is the max.
     * @param output Output value to limit.
     * @return Limited output value.
     */
    private double limitOutput(double output) {
        double clipped_output = 0.0;
        if (output > maxOutput) {
            clipped_output = maxOutput;
        } else if (output < minOutput) {
            clipped_output = minOutput;
        } else {
            // Output is already in range, just pass it along
            clipped_output = output;
        }

        return clipped_output;
    }

    /**
     * Limit an input value by the max and min value.
     * Effectively if too low the output is the min
     * and if too high the output is the max.
     * @param input Input value to limit.
     * @return Limited input value.
     */
    private double limitInput(double input) {
        double clipped_input = 0.0;
        if (input > maxInput) {
            clipped_input = maxInput;
        } else if (input < minInput) {
            clipped_input = minInput;
        } else {
            // Input is already in range, just pass it along
            clipped_input = input;
        }

        return clipped_input;
    }

    /**
     * Returns the current state of the controller
     * @return Current state.
     */
    public PidStateType getState() {
        return this.currentState;
    }

    /**
     * Notifys the controller of a config change.
     * Controller then set values to config values.
     */
    public void notifyConfigChange() {
        p = p_config;
        i = i_config;
        d = d_config;
        errorIncrement = errorIncrement_config;
        errorEpsilon = errorEpsilon_config;
        staticEpsilon = staticEpsilon_config;
        maxIntegral = maxIntegral_config;
        integralErrorThresh = integralErrorThresh_config;
        differentiatorBandLimit = differentiatorBandLimit_config;
        maxOutput = maxOutput_config;
        minOutput = minOutput_config;
        maxInput = maxInput_config;
        minInput = minInput_config;
    }

    /**
     * Returns the name of the controller.
     * @return Controller name.
     */
    public String getName() {
        return controllerName;
    }

    /**
     * Returns the name in combination with the PID state.
     * @return Name and PID state.
     */
    @Override
    public String toString() {
        return getName() + " " + getState();
    }
}