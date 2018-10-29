grammar ICSS;

//--- LEXER: ---

//Literals
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';

//Literals
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;

//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//--- PARSER: ---

stylesheet: variable_assignment* stylerule* EOF;

stylerule: selector+ OPEN_BRACE body+ CLOSE_BRACE;
selector: ID_IDENT #ID | CLASS_IDENT #CLASS| LOWER_IDENT #LOW;
body:  (declaration| variable_assignment| stylerule);
declaration: property COLON expression SEMICOLON;
property: LOWER_IDENT;

variable_assignment: variable_reference ASSIGNMENT_OPERATOR expression SEMICOLON;
variable_reference: CAPITAL_IDENT;

expression: literal | variable_reference | operation; //of een operation
literal: PIXELSIZE #PixelLiteral |
         PERCENTAGE #PercentageLiteral |
         SCALAR #ScalarLiteral |
         COLOR #ColorLiteral;

calc: SCALAR | calc PLUS calc | calc MIN calc | calc MUL calc;

operation:  (literal | variable_reference) #eoperation|
            operation MUL operation #muloperation |
            operation PLUS operation #plusoperation |
            operation MIN operation #minexpression;