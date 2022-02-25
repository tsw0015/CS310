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
		throw new UnsupportedOperationException();
	}

	@Override
	public void lex() {
		TOKEN = null;
		LEXEME = null;

		if(S == null || i>= S.length)
			return;

		while(S[i] == ' ' || S[i] == '	') { 
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
		
		//TODO Account for case insensitivity of keywords
		else if(S[i] == 'l' || S[i] == 'e' || S[i] == 'v') {
			if(i+1 < S.length && S[i+1] == 'e') {
				if(i+2 < S.length && S[i+2] == 't') {

				}
			}
			else if (i+1 < S.length && S[i+1] == 'v') {
				if(i+2 < S.length && S[i+2] == 'a') {
					if(i+3 < S.length && S[i+3] == 'l') {

					}
				}
			}
			else if(i+1 < S.length && S[i+1] == ' ') {
				
			}
		}
		else if(S[i] >= 'a' && S[i] <= 'z') {
			TOKEN = Token.VARIABLE_NAME;
			i++;
		}

		throw new UnsupportedOperationException();
	}
}
