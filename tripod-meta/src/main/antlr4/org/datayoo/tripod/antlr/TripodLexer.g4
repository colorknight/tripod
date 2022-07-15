lexer grammar TripodLexer;

options {
language = Java;
}

//tokens {
//}

// Keywords

// Literals

NUMBER:      Digits ('.' Digits)?;

TERM:       TermChar* LetterOrDigit TermChar*;

IDENTIFIER:  Letter LetterOrDigit* ('.' Letter LetterOrDigit*)*;

Q_PHRASE:       '\'' (~['\\\r\n] | EscapeSequence | '\\\'')* '\'';

DQ_PHRASE:     '"' (~["\\\r\n] | EscapeSequence | '"')* '"';

// Separators
LPAREN:             '(';
RPAREN:             ')';
LBRACE:             '{';
RBRACE:             '}';
LBRACK:             '[';
RBRACK:             ']';
COMMA:              ',';

// Operators
NOT:                '!';
COLON:              ':';
AND:                '&' | '&&';
OR:                 '|' | '||';
MUST:               '+';
MINUS:              '-';
STAR:                '*';
CARET:              '^';
SWANGDASH:          '~';
QMARK:              '?';

// Whitespace and comments
WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

// Identifiers


// Fragment rules

fragment EscapeSequence
    : '\\' [btnfr"'\\]
    ;

fragment Digits
    : [0-9] ([0-9]*)?
    ;

fragment TermChar
    : LetterOrDigit
    | [?*]
    ;

fragment LetterOrDigit
    : Letter
    | [0-9]
    ;

fragment Letter
    : [a-zA-Z$_] // these are the "java letters" below 0x7F
    | ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
    | [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
    ;