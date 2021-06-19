package org.wildstang.framework.hardware;

import org.wildstang.framework.core.Inputs;
import org.wildstang.framework.io.inputs.Input;

/**
 * Creates Input objects attached to a specified port, and of a specified type.
 * This interface would have been purge because it only has one implementation,
 * however, that implementation is in org.wildstang.hardware and we would prefer
 * org.wildstang.framework not import anything from there.
 *
 * @author Steve
 *
 */
public interface InputFactory {

    /**
     * Any preperation should be done in this method.
     */
    public void init();

    /**
     * Creates an Input from an enumeration of WsInputs.
     * @param An enumeration of WsInputs.
     * @return A constructed Input.
     */
    public Input createInput(Inputs p_input);
}
