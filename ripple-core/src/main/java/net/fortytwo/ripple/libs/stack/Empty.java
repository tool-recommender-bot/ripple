package net.fortytwo.ripple.libs.stack;

import net.fortytwo.flow.Sink;
import net.fortytwo.ripple.RippleException;
import net.fortytwo.ripple.model.ModelConnection;
import net.fortytwo.ripple.model.PrimitiveStackMapping;
import net.fortytwo.ripple.model.RippleList;

/**
 * A primitive which consumes a list and yields true if the list is empty,
 * otherwise false.
 *
 * @author Joshua Shinavier (http://fortytwo.net)
 */
public class Empty extends PrimitiveStackMapping {
    private static final String[] IDENTIFIERS = {
            StackLibrary.NS_2013_03 + "empty",
            StackLibrary.NS_2008_08 + "empty"};

    public String[] getIdentifiers() {
        return IDENTIFIERS;
    }

    public Empty() {
        super();
    }

    public Parameter[] getParameters() {
        return new Parameter[]{
                new Parameter("l", "a list", true)};
    }

    public String getComment() {
        return "l  =>  true if l is an empty list, otherwise false";
    }

    public void apply(final RippleList arg,
                      final Sink<RippleList> solutions,
                      final ModelConnection mc) throws RippleException {

        Object l;

        l = arg.getFirst();
        final RippleList rest = arg.getRest();

        Sink<RippleList> listSink = list -> {
            boolean result = list.isNil();
            solutions.accept(
                    rest.push(result));
        };

        mc.toList(l, listSink);
    }
}
