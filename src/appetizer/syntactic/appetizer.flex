package appetizer.syntactic;

%%

%unicode
%line
%column
%cup
%implements Symbols

%{
	private int commentCount = 0;

	private void err(String message) {
		System.out.println("Scanning error in line " + yyline + ", column " + yycolumn + ": " + message);
	}

	private java_cup.runtime.Symbol tok(int kind) {
		return new java_cup.runtime.Symbol(kind, yyline, yycolumn);
	}

	private java_cup.runtime.Symbol tok(int kind, Object value) {
		return new java_cup.runtime.Symbol(kind, yyline, yycolumn, value);
	}
%}

%eofval{
	{
		if (yystate() == YYCOMMENT) {
			err("Comment symbol do not match (EOF)!");
		}
		return tok(EOF, null);
	}
%eofval}

LineTerm = \n|\r|\r\n
Identifier = [a-z]+
DecInteger = [0-9]+
Whitespace = {LineTerm}|[ \t\f]

%state	YYCOMMENT

%%

<YYINITIAL> {
	"/*" { commentCount = 1; yybegin(YYCOMMENT); }
	"*/" { err("Comment symbol do not match!"); }

	"int"    { return tok(INT); }
	"if"     { return tok(IF); }
	"return" { return tok(RETURN); }

	"(" { return tok(LPAREN); }
	")" { return tok(RPAREN); }
	"{" { return tok(LBRACE); }
	"}" { return tok(RBRACE); }

	";" { return tok(SEMICOLON); }

	"+" { return tok(PLUS); }
	"-" { return tok(MINUS); }
	"*" { return tok(TIMES); }
	"/" { return tok(DIVIDE); }

	"==" { return tok(EQ); }
	"!=" { return tok(NE) ;}
	"<"  { return tok(LT); }
	"<=" { return tok(LE); }
	">"  { return tok(GT); }
	">=" { return tok(GE); }

	"=" { return tok(ASSIGN); }

	{Identifier} { return tok(ID, yytext()); }
	{DecInteger} { return tok(NUM, new Integer(yytext())); }
	{Whitespace} { /* skip */ }

	[^] { throw new RuntimeException("Illegal character " + yytext() + " in line " + (yyline + 1) + ", column " + (yycolumn + 1)); }
}

<YYCOMMENT> {
	"/*" { commentCount++; }
	"*/" { commentCount--; if (commentCount == 0) yybegin(YYINITIAL); }
	[^]  {}
}
