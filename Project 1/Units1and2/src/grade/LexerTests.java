package grade;

import static model.AbstractLexer.Token.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import model.AbstractLexer.Token;
import model.AbstractLexer.TokenException;

/**
 * Do not modify.
 */
@RunWith(Parameterized.class)
public class LexerTests {
	@Parameters(name="{index}: {0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{ "eval 1?", tokens(EVAL_KEYWORD, TRUE_LITERAL, QUESTION) },
			{ "eval 0?", tokens(EVAL_KEYWORD, FALSE_LITERAL, QUESTION) },
			{ "eval 1'?", tokens(EVAL_KEYWORD, TRUE_LITERAL, APOSTROPHE, QUESTION) },
			{ "eval 0'?", tokens(EVAL_KEYWORD, FALSE_LITERAL, APOSTROPHE, QUESTION) },

			{ "eval 1 -> 1?", tokens(EVAL_KEYWORD, TRUE_LITERAL, ARROW, TRUE_LITERAL, QUESTION) },
			{ "eval 1 -> 0?", tokens(EVAL_KEYWORD, TRUE_LITERAL, ARROW, FALSE_LITERAL, QUESTION) },
			{ "eval 0 -> 1?", tokens(EVAL_KEYWORD, FALSE_LITERAL, ARROW, TRUE_LITERAL, QUESTION) },
			{ "eval 0 -> 0?", tokens(EVAL_KEYWORD, FALSE_LITERAL, ARROW, FALSE_LITERAL, QUESTION) },

			{ "eval 1 <-> 1?", tokens(EVAL_KEYWORD, TRUE_LITERAL, DOUBLE_ARROW, TRUE_LITERAL, QUESTION) },
			{ "eval 1 <-> 0?", tokens(EVAL_KEYWORD, TRUE_LITERAL, DOUBLE_ARROW, FALSE_LITERAL, QUESTION) },
			{ "eval 0 <-> 1?", tokens(EVAL_KEYWORD, FALSE_LITERAL, DOUBLE_ARROW, TRUE_LITERAL, QUESTION) },
			{ "eval 0 <-> 0?", tokens(EVAL_KEYWORD, FALSE_LITERAL, DOUBLE_ARROW, FALSE_LITERAL, QUESTION) },

			{ "let P = 1, let Q = 1, eval P ^ Q?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), CARET, withLexeme(VARIABLE_NAME, "Q"), QUESTION) },
			{ "let P = 1, let Q = 0, eval P ^ Q?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), CARET, withLexeme(VARIABLE_NAME, "Q"), QUESTION) },
			{ "let P = 0, let Q = 1, eval P ^ Q?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), CARET, withLexeme(VARIABLE_NAME, "Q"), QUESTION) },
			{ "let P = 0, let Q = 0, eval P ^ Q?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), CARET, withLexeme(VARIABLE_NAME, "Q"), QUESTION) },

			{ "let P = 1, let Q = 1, let R = 1, eval P ^ Q ^ R?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "R"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), CARET, withLexeme(VARIABLE_NAME, "Q"), CARET, withLexeme(VARIABLE_NAME, "R"), QUESTION) },
			{ "let P = 1, let Q = 1, let R = 0, eval P ^ Q ^ R?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "R"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), CARET, withLexeme(VARIABLE_NAME, "Q"), CARET, withLexeme(VARIABLE_NAME, "R"), QUESTION) },
			{ "let P = 1, let Q = 0, let R = 1, eval P ^ Q ^ R?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "R"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), CARET, withLexeme(VARIABLE_NAME, "Q"), CARET, withLexeme(VARIABLE_NAME, "R"), QUESTION) },
			{ "let P = 1, let Q = 0, let R = 0, eval P ^ Q ^ R?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "R"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), CARET, withLexeme(VARIABLE_NAME, "Q"), CARET, withLexeme(VARIABLE_NAME, "R"), QUESTION) },
			{ "let P = 0, let Q = 1, let R = 1, eval P ^ Q ^ R?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "R"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), CARET, withLexeme(VARIABLE_NAME, "Q"), CARET, withLexeme(VARIABLE_NAME, "R"), QUESTION) },
			{ "let P = 0, let Q = 1, let R = 0, eval P ^ Q ^ R?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "R"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), CARET, withLexeme(VARIABLE_NAME, "Q"), CARET, withLexeme(VARIABLE_NAME, "R"), QUESTION) },
			{ "let P = 0, let Q = 0, let R = 1, eval P ^ Q ^ R?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "R"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), CARET, withLexeme(VARIABLE_NAME, "Q"), CARET, withLexeme(VARIABLE_NAME, "R"), QUESTION) },
			{ "let P = 0, let Q = 0, let R = 0, eval P ^ Q ^ R?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "R"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), CARET, withLexeme(VARIABLE_NAME, "Q"), CARET, withLexeme(VARIABLE_NAME, "R"), QUESTION) },

			{ "let A = 1, let B = 1, eval A v B?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "A"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "B"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "A"), VEE, withLexeme(VARIABLE_NAME, "B"), QUESTION) },
			{ "let A = 1, let B = 0, eval A v B?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "A"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "B"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "A"), VEE, withLexeme(VARIABLE_NAME, "B"), QUESTION) },
			{ "let A = 0, let B = 1, eval A v B?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "A"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "B"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "A"), VEE, withLexeme(VARIABLE_NAME, "B"), QUESTION) },
			{ "let A = 0, let B = 0, eval A v B?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "A"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "B"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "A"), VEE, withLexeme(VARIABLE_NAME, "B"), QUESTION) },

			{ "let A = 1, let B = 1, let C = 1, eval A v B v C?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "A"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "B"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "C"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "A"), VEE, withLexeme(VARIABLE_NAME, "B"), VEE, withLexeme(VARIABLE_NAME, "C"), QUESTION) },
			{ "let A = 1, let B = 1, let C = 0, eval A v B v C?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "A"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "B"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "C"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "A"), VEE, withLexeme(VARIABLE_NAME, "B"), VEE, withLexeme(VARIABLE_NAME, "C"), QUESTION) },
			{ "let A = 1, let B = 0, let C = 1, eval A v B v C?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "A"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "B"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "C"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "A"), VEE, withLexeme(VARIABLE_NAME, "B"), VEE, withLexeme(VARIABLE_NAME, "C"), QUESTION) },
			{ "let A = 1, let B = 0, let C = 0, eval A v B v C?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "A"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "B"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "C"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "A"), VEE, withLexeme(VARIABLE_NAME, "B"), VEE, withLexeme(VARIABLE_NAME, "C"), QUESTION) },
			{ "let A = 0, let B = 1, let C = 1, eval A v B v C?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "A"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "B"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "C"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "A"), VEE, withLexeme(VARIABLE_NAME, "B"), VEE, withLexeme(VARIABLE_NAME, "C"), QUESTION) },
			{ "let A = 0, let B = 1, let C = 0, eval A v B v C?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "A"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "B"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "C"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "A"), VEE, withLexeme(VARIABLE_NAME, "B"), VEE, withLexeme(VARIABLE_NAME, "C"), QUESTION) },
			{ "let A = 0, let B = 0, let C = 1, eval A v B v C?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "A"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "B"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "C"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "A"), VEE, withLexeme(VARIABLE_NAME, "B"), VEE, withLexeme(VARIABLE_NAME, "C"), QUESTION) },
			{ "let A = 0, let B = 0, let C = 0, eval A v B v C?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "A"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "B"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "C"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "A"), VEE, withLexeme(VARIABLE_NAME, "B"), VEE, withLexeme(VARIABLE_NAME, "C"), QUESTION) },

			{ "let X = 1, let Y = 1, eval X -> Y?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "X"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Y"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "X"), ARROW, withLexeme(VARIABLE_NAME, "Y"), QUESTION) },
			{ "let X = 1, let Y = 0, eval X -> Y?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "X"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Y"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "X"), ARROW, withLexeme(VARIABLE_NAME, "Y"), QUESTION) },
			{ "let X = 0, let Y = 1, eval X -> Y?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "X"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Y"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "X"), ARROW, withLexeme(VARIABLE_NAME, "Y"), QUESTION) },
			{ "let X = 0, let Y = 0, eval X -> Y?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "X"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Y"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "X"), ARROW, withLexeme(VARIABLE_NAME, "Y"), QUESTION) },

			{ "let X = 1, let Y = 1, let Z = 1, eval X -> Y -> Z?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "X"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Y"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Z"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "X"), ARROW, withLexeme(VARIABLE_NAME, "Y"), ARROW, withLexeme(VARIABLE_NAME, "Z"), QUESTION) },
			{ "let X = 1, let Y = 1, let Z = 0, eval X -> Y -> Z?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "X"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Y"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Z"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "X"), ARROW, withLexeme(VARIABLE_NAME, "Y"), ARROW, withLexeme(VARIABLE_NAME, "Z"), QUESTION) },
			{ "let X = 1, let Y = 0, let Z = 1, eval X -> Y -> Z?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "X"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Y"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Z"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "X"), ARROW, withLexeme(VARIABLE_NAME, "Y"), ARROW, withLexeme(VARIABLE_NAME, "Z"), QUESTION) },
			{ "let X = 1, let Y = 0, let Z = 0, eval X -> Y -> Z?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "X"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Y"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Z"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "X"), ARROW, withLexeme(VARIABLE_NAME, "Y"), ARROW, withLexeme(VARIABLE_NAME, "Z"), QUESTION) },
			{ "let X = 0, let Y = 1, let Z = 1, eval X -> Y -> Z?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "X"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Y"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Z"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "X"), ARROW, withLexeme(VARIABLE_NAME, "Y"), ARROW, withLexeme(VARIABLE_NAME, "Z"), QUESTION) },
			{ "let X = 0, let Y = 1, let Z = 0, eval X -> Y -> Z?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "X"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Y"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Z"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "X"), ARROW, withLexeme(VARIABLE_NAME, "Y"), ARROW, withLexeme(VARIABLE_NAME, "Z"), QUESTION) },
			{ "let X = 0, let Y = 0, let Z = 1, eval X -> Y -> Z?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "X"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Y"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Z"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "X"), ARROW, withLexeme(VARIABLE_NAME, "Y"), ARROW, withLexeme(VARIABLE_NAME, "Z"), QUESTION) },
			{ "let X = 0, let Y = 0, let Z = 0, eval X -> Y -> Z?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "X"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Y"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Z"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "X"), ARROW, withLexeme(VARIABLE_NAME, "Y"), ARROW, withLexeme(VARIABLE_NAME, "Z"), QUESTION) },

			{ "let D = 1, let E = 1, eval D <-> E?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "D"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "E"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "D"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "E"), QUESTION) },
			{ "let D = 1, let E = 0, eval D <-> E?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "D"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "E"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "D"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "E"), QUESTION) },
			{ "let D = 0, let E = 1, eval D <-> E?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "D"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "E"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "D"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "E"), QUESTION) },
			{ "let D = 0, let E = 0, eval D <-> E?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "D"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "E"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "D"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "E"), QUESTION) },

			{ "let D = 1, let E = 1, let F = 1, eval D <-> E <-> F?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "D"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "E"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "F"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "D"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "E"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "F"), QUESTION) },
			{ "let D = 1, let E = 1, let F = 0, eval D <-> E <-> F?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "D"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "E"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "F"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "D"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "E"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "F"), QUESTION) },
			{ "let D = 1, let E = 0, let F = 1, eval D <-> E <-> F?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "D"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "E"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "F"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "D"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "E"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "F"), QUESTION) },
			{ "let D = 1, let E = 0, let F = 0, eval D <-> E <-> F?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "D"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "E"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "F"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "D"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "E"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "F"), QUESTION) },
			{ "let D = 0, let E = 1, let F = 1, eval D <-> E <-> F?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "D"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "E"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "F"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "D"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "E"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "F"), QUESTION) },
			{ "let D = 0, let E = 1, let F = 0, eval D <-> E <-> F?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "D"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "E"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "F"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "D"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "E"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "F"), QUESTION) },
			{ "let D = 0, let E = 0, let F = 1, eval D <-> E <-> F?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "D"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "E"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "F"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "D"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "E"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "F"), QUESTION) },
			{ "let D = 0, let E = 0, let F = 0, eval D <-> E <-> F?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "D"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "E"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "F"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "D"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "E"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "F"), QUESTION) },

			{ "let W = 1, eval W?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "W"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "W"), QUESTION) },
			{ "let W = 0, eval W?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "W"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "W"), QUESTION) },
			{ "let W = 1, eval W'?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "W"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "W"), APOSTROPHE, QUESTION) },
			{ "let W = 0, eval (W')'?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "W"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "W"), APOSTROPHE, CLOSE_PAREN, APOSTROPHE, QUESTION) },
			{ "let W = 1, eval (W)?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "W"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "W"), CLOSE_PAREN, QUESTION) },
			{ "let W = 0, eval ((W))?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "W"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, OPEN_PAREN, withLexeme(VARIABLE_NAME, "W"), CLOSE_PAREN, CLOSE_PAREN, QUESTION) },

			{ "let AB = 1, let Q = 1, eval (AB ^ Q)' <-> (AB' v Q')?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "AB"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "AB"), CARET, withLexeme(VARIABLE_NAME, "Q"), CLOSE_PAREN, APOSTROPHE, DOUBLE_ARROW, OPEN_PAREN, withLexeme(VARIABLE_NAME, "AB"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "Q"), APOSTROPHE, CLOSE_PAREN, QUESTION) },
			{ "let AB = 1, let Q = 0, eval (AB ^ Q)' <-> (AB' v Q')?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "AB"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "AB"), CARET, withLexeme(VARIABLE_NAME, "Q"), CLOSE_PAREN, APOSTROPHE, DOUBLE_ARROW, OPEN_PAREN, withLexeme(VARIABLE_NAME, "AB"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "Q"), APOSTROPHE, CLOSE_PAREN, QUESTION) },
			{ "let AB = 0, let Q = 1, eval (AB ^ Q)' <-> (AB' v Q')?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "AB"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "AB"), CARET, withLexeme(VARIABLE_NAME, "Q"), CLOSE_PAREN, APOSTROPHE, DOUBLE_ARROW, OPEN_PAREN, withLexeme(VARIABLE_NAME, "AB"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "Q"), APOSTROPHE, CLOSE_PAREN, QUESTION) },
			{ "let AB = 0, let Q = 0, eval (AB ^ Q)' <-> (AB' v Q')?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "AB"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "AB"), CARET, withLexeme(VARIABLE_NAME, "Q"), CLOSE_PAREN, APOSTROPHE, DOUBLE_ARROW, OPEN_PAREN, withLexeme(VARIABLE_NAME, "AB"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "Q"), APOSTROPHE, CLOSE_PAREN, QUESTION) },

			{ "let P = 1, let CD = 1, eval (P v CD)' <-> (P' ^ CD')?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "CD"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "P"), VEE, withLexeme(VARIABLE_NAME, "CD"), CLOSE_PAREN, APOSTROPHE, DOUBLE_ARROW, OPEN_PAREN, withLexeme(VARIABLE_NAME, "P"), APOSTROPHE, CARET, withLexeme(VARIABLE_NAME, "CD"), APOSTROPHE, CLOSE_PAREN, QUESTION) },
			{ "let P = 1, let CD = 0, eval (P v CD)' <-> (P' ^ CD')?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "CD"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "P"), VEE, withLexeme(VARIABLE_NAME, "CD"), CLOSE_PAREN, APOSTROPHE, DOUBLE_ARROW, OPEN_PAREN, withLexeme(VARIABLE_NAME, "P"), APOSTROPHE, CARET, withLexeme(VARIABLE_NAME, "CD"), APOSTROPHE, CLOSE_PAREN, QUESTION) },
			{ "let P = 0, let CD = 1, eval (P v CD)' <-> (P' ^ CD')?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "CD"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "P"), VEE, withLexeme(VARIABLE_NAME, "CD"), CLOSE_PAREN, APOSTROPHE, DOUBLE_ARROW, OPEN_PAREN, withLexeme(VARIABLE_NAME, "P"), APOSTROPHE, CARET, withLexeme(VARIABLE_NAME, "CD"), APOSTROPHE, CLOSE_PAREN, QUESTION) },
			{ "let P = 0, let CD = 0, eval (P v CD)' <-> (P' ^ CD')?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "CD"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "P"), VEE, withLexeme(VARIABLE_NAME, "CD"), CLOSE_PAREN, APOSTROPHE, DOUBLE_ARROW, OPEN_PAREN, withLexeme(VARIABLE_NAME, "P"), APOSTROPHE, CARET, withLexeme(VARIABLE_NAME, "CD"), APOSTROPHE, CLOSE_PAREN, QUESTION) },

			{ "let JKL = 1, let Q = 1, eval (JKL -> Q) <-> (JKL' v Q)?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "JKL"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "JKL"), ARROW, withLexeme(VARIABLE_NAME, "Q"), CLOSE_PAREN, DOUBLE_ARROW, OPEN_PAREN, withLexeme(VARIABLE_NAME, "JKL"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "Q"), CLOSE_PAREN, QUESTION) },
			{ "let JKL = 1, let Q = 0, eval (JKL -> Q) <-> (JKL' v Q)?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "JKL"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "JKL"), ARROW, withLexeme(VARIABLE_NAME, "Q"), CLOSE_PAREN, DOUBLE_ARROW, OPEN_PAREN, withLexeme(VARIABLE_NAME, "JKL"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "Q"), CLOSE_PAREN, QUESTION) },
			{ "let JKL = 0, let Q = 1, eval (JKL -> Q) <-> (JKL' v Q)?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "JKL"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "JKL"), ARROW, withLexeme(VARIABLE_NAME, "Q"), CLOSE_PAREN, DOUBLE_ARROW, OPEN_PAREN, withLexeme(VARIABLE_NAME, "JKL"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "Q"), CLOSE_PAREN, QUESTION) },
			{ "let JKL = 0, let Q = 0, eval (JKL -> Q) <-> (JKL' v Q)?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "JKL"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, OPEN_PAREN, withLexeme(VARIABLE_NAME, "JKL"), ARROW, withLexeme(VARIABLE_NAME, "Q"), CLOSE_PAREN, DOUBLE_ARROW, OPEN_PAREN, withLexeme(VARIABLE_NAME, "JKL"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "Q"), CLOSE_PAREN, QUESTION) },

			{ "let P = 1, let MNO = 1, eval P -> MNO <-> P' v MNO?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "MNO"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), ARROW, withLexeme(VARIABLE_NAME, "MNO"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "P"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "MNO"), QUESTION) },
			{ "let P = 1, let MNO = 0, eval P -> MNO <-> P' v MNO?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "MNO"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), ARROW, withLexeme(VARIABLE_NAME, "MNO"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "P"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "MNO"), QUESTION) },
			{ "let P = 0, let MNO = 1, eval P -> MNO <-> P' v MNO?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "MNO"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), ARROW, withLexeme(VARIABLE_NAME, "MNO"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "P"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "MNO"), QUESTION) },
			{ "let P = 0, let MNO = 0, eval P -> MNO <-> P' v MNO?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "MNO"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), ARROW, withLexeme(VARIABLE_NAME, "MNO"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "P"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "MNO"), QUESTION) },

			{ "let AAA = 1, let bbb = 1, eval AAA -> bbb <-> bbb' -> AAA'?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "AAA"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "bbb"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "AAA"), ARROW, withLexeme(VARIABLE_NAME, "bbb"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "bbb"), APOSTROPHE, ARROW, withLexeme(VARIABLE_NAME, "AAA"), APOSTROPHE, QUESTION) },
			{ "let aaa = 1, let BBB = 0, eval aaa -> BBB <-> BBB' -> aaa'?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "aaa"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "BBB"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "aaa"), ARROW, withLexeme(VARIABLE_NAME, "BBB"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "BBB"), APOSTROPHE, ARROW, withLexeme(VARIABLE_NAME, "aaa"), APOSTROPHE, QUESTION) },
			{ "let Aaa = 0, let BBB = 1, eval Aaa -> BBB <-> BBB' -> Aaa'?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "Aaa"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "BBB"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "Aaa"), ARROW, withLexeme(VARIABLE_NAME, "BBB"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "BBB"), APOSTROPHE, ARROW, withLexeme(VARIABLE_NAME, "Aaa"), APOSTROPHE, QUESTION) },
			{ "let AAA = 0, let bbB = 0, eval AAA -> bbB <-> bbB' -> AAA'?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "AAA"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "bbB"), EQUAL, FALSE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "AAA"), ARROW, withLexeme(VARIABLE_NAME, "bbB"), DOUBLE_ARROW, withLexeme(VARIABLE_NAME, "bbB"), APOSTROPHE, ARROW, withLexeme(VARIABLE_NAME, "AAA"), APOSTROPHE, QUESTION) },

			{ "Let P = 1, Let Q = P, Eval P v Q?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, withLexeme(VARIABLE_NAME, "P"), COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), VEE, withLexeme(VARIABLE_NAME, "Q"), QUESTION) },
			{ "LET p = 0, LET Q = p, EVAL p V Q?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "p"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), EQUAL, withLexeme(VARIABLE_NAME, "p"), COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "p"), VEE, withLexeme(VARIABLE_NAME, "Q"), QUESTION) },
			{ "LeT P = 1, lEt q = P', EvAl P v q?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "q"), EQUAL, withLexeme(VARIABLE_NAME, "P"), APOSTROPHE, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "P"), VEE, withLexeme(VARIABLE_NAME, "q"), QUESTION) },
			{ "leT p = 0, LEt q = p', eVaL p V q?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "p"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "q"), EQUAL, withLexeme(VARIABLE_NAME, "p"), APOSTROPHE, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "p"), VEE, withLexeme(VARIABLE_NAME, "q"), QUESTION) },

			{ "let LP = 1, let EQ = 1, let VR = LP' ^ EQ', eval VR?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "LP"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "EQ"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "VR"), EQUAL, withLexeme(VARIABLE_NAME, "LP"), APOSTROPHE, CARET, withLexeme(VARIABLE_NAME, "EQ"), APOSTROPHE, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "VR"), QUESTION) },
			{ "let LP = 1, let EQ = 0, let VR = LP' ^ EQ', eval VR?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "LP"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "EQ"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "VR"), EQUAL, withLexeme(VARIABLE_NAME, "LP"), APOSTROPHE, CARET, withLexeme(VARIABLE_NAME, "EQ"), APOSTROPHE, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "VR"), QUESTION) },
			{ "let LP = 0, let EQ = 1, let VR = LP' ^ EQ', eval VR?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "LP"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "EQ"), EQUAL, TRUE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "VR"), EQUAL, withLexeme(VARIABLE_NAME, "LP"), APOSTROPHE, CARET, withLexeme(VARIABLE_NAME, "EQ"), APOSTROPHE, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "VR"), QUESTION) },
			{ "let LP = 0, let EQ = 0, let VR = LP' ^ EQ', eval VR?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "LP"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "EQ"), EQUAL, FALSE_LITERAL, COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "VR"), EQUAL, withLexeme(VARIABLE_NAME, "LP"), APOSTROPHE, CARET, withLexeme(VARIABLE_NAME, "EQ"), APOSTROPHE, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "VR"), QUESTION) },

			{ "eval LET?", tokens(EVAL_KEYWORD, LET_KEYWORD, QUESTION) },
			{ "let P = 1, eval p?", tokens(LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, TRUE_LITERAL, COMMA, EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "p"), QUESTION) },
			{ "eval Q, let P = 0?", tokens(EVAL_KEYWORD, withLexeme(VARIABLE_NAME, "Q"), COMMA, LET_KEYWORD, withLexeme(VARIABLE_NAME, "P"), EQUAL, FALSE_LITERAL, QUESTION) },
			{ "let -><-><->->EP? LQ", tokens(LET_KEYWORD, ARROW, DOUBLE_ARROW, DOUBLE_ARROW, ARROW, withLexeme(VARIABLE_NAME, "EP"), QUESTION, withLexeme(VARIABLE_NAME, "LQ")) },
			{ "Vvv <->-><--> 1", tokens(withLexeme(VARIABLE_NAME, "Vvv"), DOUBLE_ARROW, ARROW, TokenException.class) },
			{ "^vEVAL letEVAL ' V Pv & ^", tokens(CARET, withLexeme(VARIABLE_NAME, "vEVAL"), withLexeme(VARIABLE_NAME, "letEVAL"), APOSTROPHE, VEE, withLexeme(VARIABLE_NAME, "Pv"), TokenException.class) },
		});
	}

	public static Object[] tokens(Object... tokens) {
		return tokens;
	}

	public static Object[] withLexeme(Token token, String lexeme) {
		return new Object[] {token, lexeme};
	}

	@Parameter(value=0)
	public String sentence;

	@Parameter(value=1)
	public Object[] tokens;

	private static model.AbstractLexer LEXER;

	@BeforeClass
	public static void setup() {
		LEXER = new unit.Lexer();
	}

	@Test
	public void testTokens() {
		LEXER.initialize(sentence.toCharArray());

		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i] instanceof Token) {
				LEXER.lex();

				assertEquals(
					"Token " + (i+1) + " of " + tokens.length + " must match expected token",
					tokens[i],
					LEXER.TOKEN
				);

				assertNull(
					"Lexeme for token " + (i+1) + " of " + tokens.length + " must be null",
					LEXER.LEXEME
				);
			}
			else if (tokens[i] instanceof Object[]) {
				LEXER.lex();

				assertEquals(
					"Token " + (i+1) + " of " + tokens.length + " must match expected token",
					((Object[]) tokens[i])[0],
					LEXER.TOKEN
				);

				assertEquals(
					"Lexeme for token " + (i+1) + " of " + tokens.length + " must match expected lexeme",
					((Object[]) tokens[i])[1],
					LEXER.LEXEME != null ? String.valueOf(LEXER.LEXEME) : null
				);
			}
			else if (tokens[i] == TokenException.class) {
				assertThrows(
					"Invalid token " + (i+1) + " of " + tokens.length + " must throw token exception",
					TokenException.class,
					new ThrowingRunnable() {
						public void run() throws Throwable {
							LEXER.lex();
						}
					}
				);
			}
		}
	}
}
