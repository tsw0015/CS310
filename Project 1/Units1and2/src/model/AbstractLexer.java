package model;

/**
 * Do not modify.
 */
public abstract class AbstractLexer {
	/**
	 * The token types in the language (excluding the
	 * <code>null</code> token for the end of input).
	 * <p>
	 * Do not call any methods on this enumeration type
	 * or on instances of this enumeration type.
	 */
	public static enum Token {
		LET_KEYWORD,	// regex /let/i
		EQUAL,			// regex /=/
		COMMA,			// regex /,/
		EVAL_KEYWORD,	// regex /eval/i
		QUESTION,		// regex /\?/
		DOUBLE_ARROW,	// regex /<->/
		ARROW,			// regex /->/
		VEE,			// regex /v/i
		CARET,			// regex /\^/
		APOSTROPHE,		// regex /'/
		OPEN_PAREN,		// regex /\(/
		CLOSE_PAREN,	// regex /\)/
		VARIABLE_NAME,	// regex /[a-z]+/i but not let/eval/v
		TRUE_LITERAL,	// regex /1/
		FALSE_LITERAL	// regex /0/
	}

	/**
	 * Assumed to be called once and only once before
	 * the first call to {@link AbstractLexer#lex()}
	 * to initialize the lexer's input sentence.
	 *
	 * @param sentence An input sentence
	 */
	public abstract void initialize(char[] sentence);

	/**
	 * Advances to the next lexeme in the input and
	 * updates {@link AbstractLexer#TOKEN} and
	 * {@link AbstractLexer#LEXEME} with the
	 * next token and lexeme, respectively.
	 *
	 * @throws TokenException
	 * When an unexpected token is reached
	 */
	public abstract void lex();

	/**
	 * The next token in the input (or <code>null</code>
	 * when the end of input is reached), updated on
	 * each call to {@link AbstractLexer#lex()}.
	 * <p>
	 * Do not redefine this field in the subclass.
	 */
	public Token TOKEN;

	/**
	 * The next lexeme in the input (or <code>null</code>
	 * if the lexeme is the only instance of the token),
	 * updated on each call to {@link AbstractLexer#lex()}.
	 * <p>
	 * Do not redefine this field in the subclass.
	 */
	public char[] LEXEME;

	/**
	 * Thrown when an invalid token is reached.
	 */
	@SuppressWarnings("serial")
	public static class TokenException extends RuntimeException {
		public TokenException() {
			super();
		}

		public TokenException(String message) {
			super(message);
		}
	}
}