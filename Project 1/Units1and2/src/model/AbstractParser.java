package model;

import model.AbstractLexer.Token;

/**
 * Do not modify.
 */
public abstract class AbstractParser {
	/**
	 * This parser's {@link AbstractLexer#Lexer()} instance.
	 * <p>
	 * Do not redefine this field in the subclass.
	 */
	public AbstractLexer lexer;

	/**
	 * Instantiates the parser's lexer instance and
	 * initializes the lexer's input sentence by calling
	 * {@link AbstractLexer#initialize(char[])}.
	 * <p>
	 * Then interprets the input sentence as a boolean.
	 *
	 * @param sentence An input sentence
	 * @return The result of interpreting the input
	 *
	 * @throws InputException
	 * If the input is syntactically invalid
	 */
	public abstract boolean interpret(char[] sentence);

	/**
	 * If the lexer's next token matches the given
	 * token, returns true and advances the lexer.
	 * <p>
	 * Otherwise, returns false and does not advance.
	 *
	 * @param token The given token
	 * @return Whether the next token matches
	 */
	public abstract boolean accept(Token token);

	/**
	 * If the lexer's next token matches the given
	 * token, returns true.
	 * <p>
	 * Otherwise, returns false.
	 * <p>
	 * In either case, does not advance the lexer.
	 * <p>
	 * This method is optional, so a stub is provided.
	 *
	 * @param token The given token
	 * @return Whether the next token matches
	 */
	public boolean peek(Token token) {
		throw new UnsupportedOperationException();
	}

	/**
	 * If the lexer's next token matches the given
	 * token, advances the lexer.
	 * <p>
	 * Otherwise, throws an exception because
	 * the input is syntactically invalid.
	 *
	 * @param token The given token
	 * @return Whether the next token matches
	 *
	 * @throws InputException
	 * If the next token does not match
	 */
	public abstract void expect(Token token);

	/**
	 * Thrown when an input is syntactically invalid.
	 */
	@SuppressWarnings("serial")
	public static class InputException extends RuntimeException {
		public InputException() {
			super();
		}

		public InputException(String message) {
			super(message);
		}
	}

	/**
	 * Thrown when a variable name is missing or duplicated
	 * or when another lookup table error occurs.
	 */
	@SuppressWarnings("serial")
	public static class VariableException extends RuntimeException {
		public VariableException() {
			super();
		}

		public VariableException(String message) {
			super(message);
		}
	}

	// OPTIONAL TRACING FRAMEWORK FOLLOWS

	/**
	 * The current indentation level of
	 * the tracing framework.
	 */
	private int trace_indent;

	/**
	 * Assumed to be called once and only once at the
	 * start of {@link AbstractParser#interpret(char[])}
	 * to initialize the tracing framework.
	 * <p>
	 * Initializes the indentation.
	 */
	protected final void trace_start() {
		trace_indent = 0;
	}

	/**
	 * Assumed to be called once and only once at the
	 * start of each recursive descent production method.
	 * <p>
	 * Increases the indentation.
	 *
	 * @param production The name of the production
	 */
	protected final void trace_open(String production) {
		trace_flush(production + " {");
		trace_indent++;
	}

	/**
	 * Assumed to be called once and only once at the
	 * end of each recursive descent production method
	 * without a return value.
	 * <p>
	 * Decreases the indentation.
	 *
	 * @param production The name of the production
	 */
	protected final void trace_close(String production) {
		trace_flush("End of " + production);
		trace_indent--;
		trace_flush("}");
	}

	/**
	 * Assumed to be called once and only once at the
	 * end of each recursive descent production method
	 * with a return value.
	 * <p>
	 * Decreases the indentation.
	 *
	 * @param production The name of the production
	 * @param result The result of the production
	 */
	protected final void trace_close(String production, Object result) {
		trace_flush("Return: " + (result != null ? String.valueOf(result) : "void"));
		trace_flush("End of " + production);
		trace_indent--;
		trace_flush("}");
	}

	/**
	 * Assumed to be called once and only once
	 * after each time the lexer advances.
	 * <p>
	 * Does not affect the indentation.
	 */
	protected final void trace_lexed() {
		trace_flush("Lexed: " + lexer.TOKEN + (lexer.LEXEME != null ? "[" + String.valueOf(lexer.LEXEME) + "]" : ""));
	}

	/**
	 * Called by other tracing framework methods
	 * to flush their output to the console.
	 * <p>
	 * Can be called with a custom line to output.
	 * <p>
	 * Does not affect the indentation.
	 *
	 * @param production The line to output
	 */
	protected final void trace_flush(String line) {
		for (int i = 1; i <= trace_indent; i++)
			System.out.print("  ");
		System.out.println(line);
	}
}