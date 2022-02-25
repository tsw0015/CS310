package unit;

public class Lexer extends model.AbstractLexer {
	// TODO: do NOT redefine superclass fields

	private char[] S;
	private int i;

	@Override
	public void initialize(char[] sentence) {
		// TODO: implement this method stub
		S = sentence;
		i = 0;
		//throw new UnsupportedOperationException();
	}

	@Override
	public void lex() {
		TOKEN = null;
		LEXEME = null;

		if(S == null || i>= S.length)
			return;

		while(S[i] == ' ' || S[i] == '	' || S[i] == '\n') { 
			//TODO Account for all whitespace types
			i++;
			if ( i>= S.length)
				return;
		}

		if(S[i] == '=') {
			TOKEN = Token.EQUAL;
			i++;
		}
		else if(S[i] == ',') {
			TOKEN = Token.COMMA;
			i++;
		}
		else if(S[i] == '?') {
			TOKEN = Token.QUESTION;
			i++;
		}
		else if(S[i] == '^') {
			TOKEN = Token.CARET;
			i++;
		}
		else if(S[i] == '\'') {
			TOKEN = Token.APOSTROPHE;
			i++;
		}		
		else if(S[i] == '(') {
			TOKEN = Token.OPEN_PAREN;
			i++;
		}
		else if(S[i] == ')') {
			TOKEN = Token.CLOSE_PAREN;
			i++;
		}
		else if(S[i] == '1') {
			TOKEN = Token.TRUE_LITERAL;
			i++;
		}
		else if(S[i] == '0') {
			TOKEN = Token.FALSE_LITERAL;
			i++;
		}
		
		//TODO Check for trailing characters that would cause the token to be variable name not keyword
		//TODO Increment i correct number of space
		//TODO handle mixed chases where var name begins with part of keyword but changes
		else if(S[i] == 'l' || S[i] == 'L' ) {
			if(i+1 < S.length && S[i+1] == 'e' || S[i+1] == 'E') {
				if(i+2 < S.length && S[i+2] == 't') {
					if(i+3 < S.length && S[i+3] == ' ' || S[i+3] == '	' || S[i+3] == '\n') {
						TOKEN = Token.LET_KEYWORD;
						i+=3;
					}
				}
			}
		}
		else if (S[i] == 'e' || S[i] == 'E') {
			if (i+1 < S.length && S[i+1] == 'v' || S[i+1] == 'V') {
				if(i+2 < S.length && S[i+2] == 'a' || S[i+1] == 'A') {
					if(i+3 < S.length && S[i+3] == 'l' || S[i+1] == 'L') {
						if(i+4 < S.length && S[i+4] == ' ' || S[i+4] == '	' || S[i+4] == '\n') {
							TOKEN = Token.EVAL_KEYWORD;
							i+=3;
						}
					}
				}
			}
		}
		else if(S[i] == 'v' || S[i] == 'V' ) {
			if(i+1 < S.length && S[i+1] == ' ' || S[i+1] == '	' || S[i+1] == '\n') {
				TOKEN = Token.VEE;
				i++;
			}
		}
		else if(S[i] >= 'a' && S[i] <= 'z') {
			TOKEN = Token.VARIABLE_NAME;
			i++;
		}

		//throw new UnsupportedOperationException();
	}
}
