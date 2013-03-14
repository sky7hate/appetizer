all: Parser.java Symbols.java Yylex.java

Parser.java: appetizer.cup
	java -jar ../../../lib/java-cup-11a.jar -parser Parser -symbols Symbols -interface < appetizer.cup

Symbols.java: appetizer.cup
	java -jar ../../../lib/java-cup-11a.jar -parser Parser -symbols Symbols -interface < appetizer.cup

Yylex.java: appetizer.flex
	java -cp ../../../lib/JFlex.jar JFlex.Main appetizer.flex

