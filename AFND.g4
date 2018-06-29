grammar AFND; 

prog:  estados ';' NEWLINE simbolos ';' NEWLINE transicao ';' NEWLINE inicial ';' NEWLINE finais ';' # printPro;

estados: 'E={' ESTADO (',' ESTADO)* '}'                       # printEst;
simbolos: 'S={' CHAR (',' CHAR)* '}'                          # printSim;
transicao: 'T={' NEWLINE (regra NEWLINE)+ '}'                 # printTra;
regra: '(' ESTADO ',' CHAR ')={' ESTADO (',' ESTADO)* '}'     # printReg;
inicial: 'I=' ESTADO                                          # printIni;
finais: 'F={' ESTADO (',' ESTADO)* '}'                        # printFin;

NEWLINE: '\r'? '\n';       // nova linha
WS:      [ \t]+ -> skip;   // lida com whitespace
CHAR:    [A-Za-z0-9];      // char alfanumérico
ESTADO:  'q'[0-9]+;        // estado

