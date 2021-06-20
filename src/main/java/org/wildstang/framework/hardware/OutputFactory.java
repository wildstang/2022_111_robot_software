package org.wildstang.framework.hardware;

import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.io.outputs.Output;

/**
 * Creates Output objects attached to a specified port, and of a specified type.
 * This interface would have been purge because it only has one implementation,
 * however, that implementation is in org.wildstang.hardware and we would prefer
 * org.wildstang.framework not import anything from there.
 *
 * @author Steve
 *
 */
public interface OutputFactory {

    /**
     * Any preperation should be done in this method.
     */
    public void init();

    /**
     * Creates an Output from an enumeration of WsOutputs.
     * @param p_output An enumeration of WsOutputs.
     * @return A constructed Output.
     */
    public Output createOutput(Outputs p_output);
}
