/*
 * $URL$
 * $Revision$
 * $Author$
 *
 * Copyright (C) 2007-2008 Joshua Shinavier
 */


package net.fortytwo.ripple.libs.graph;

import net.fortytwo.ripple.RippleException;
import net.fortytwo.ripple.model.PrimitiveStackMapping;
import net.fortytwo.ripple.model.RippleValue;
import net.fortytwo.ripple.model.ModelConnection;
import net.fortytwo.ripple.model.StackContext;
import net.fortytwo.ripple.model.RippleList;
import net.fortytwo.ripple.flow.Sink;
import org.openrdf.model.vocabulary.XMLSchema;

/**
 * A primitive which consumes a resource or literal value and produces its
 * string representation.  For literal values, this is the same literal
 * value but with a type of xsd:string.  For resources identified by URIs,
 * this is the URI as a string.  For blank nodes, this is the identifier of
 * the node.
 */
public class ToString extends PrimitiveStackMapping
{
	private static final int ARITY = 1;

    private static final String[] IDENTIFIERS = {
            GraphLibrary.NS_2008_08 + "toString",
            GraphLibrary.NS_2007_08 + "toString",
            GraphLibrary.NS_2007_05 + "toString"};

    public String[] getIdentifiers()
    {
        return IDENTIFIERS;
    }

	public ToString()
		throws RippleException
	{
		super();
	}

	public int arity()
	{
		return ARITY;
	}

	public void apply( final StackContext arg,
						 final Sink<StackContext, RippleException> solutions )
		throws RippleException
	{
		final ModelConnection mc = arg.getModelConnection();
		RippleList stack = arg.getStack();

		RippleValue v;

		v = stack.getFirst();
		stack = stack.getRest();

		solutions.put( arg.with(
				stack.push( mc.value( mc.toString( v ), XMLSchema.STRING ) ) ) );
	}
}

