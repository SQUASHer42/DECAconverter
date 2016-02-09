# DECAconverter
Welcome to the Alpha 1.1 build of the DECA-converter program. This program currently accepts String input (of a copied DECA test and answer key) and it outputs a .txt file with entrees in this format:

    test number;exam type;exam location;question number;question text;choice A;choice B;choice C;choice D;answer;answer text
    ex) 1079;FINANCE;REGIONALS;25;What is the square root of -1?;0;i;-1;1;i;i represents the imaginary value of the square root of -1.
    
Currently known problems and bugs:

    1.) Only tests 2009 and up can be used with this program.
    
    2.) It is unknown whether this program will work for exams other than Finance Cluster Exams.
    
    3.) Some errors may occur if the input has formatting errors (For example, an extra space in a sentence or specific misspelled words).
    
    4.) There is an error inputting the answer key. For now, go to the source code and remove the "//" signs so the program can work without the answer key data.
