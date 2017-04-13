/*
   progettare un linguaggio che riconosca le stringe contenenti e soli un numero
   di matricola e cognome dove la combinazione dei due sia accettata per
   matricole pari cui iniziale del cognome sia fra A e K o matricole dispari
   cui iniziale del cognome sia fra L e Z.

   stati:
      0. nessun input ricevuto ancora ( iniziale )
      1. numero pari
      2. numero dispari
      3. accettazione ( accettazione )
      4. rifiuto ( rifiuto )

   funzione di transizione:
      f( 0, (0 + 2 + 4 + 6 + 8)^+ ) = 1;
      f( 0, (1 + 3 + 5 + 7 + 9)^+ ) = 2;
      f( 0, [A-z]^+) = 4;

      f( 1, (0 + 2 + 4 + 6 + 8)^+ ) = 1;
      f( 1, (1 + 3 + 5 + 7 + 9)^+ ) = 2;
      f( 1, [A-K] ) = 3;
      f( 1, [L-z]^+ ) = 4;

      f( 2, (0 + 2 + 4 + 6 + 8)^+ ) = 1;
      f( 2, (1 + 3 + 5 + 7 + 9)^+ ) = 2;
      f( 2, ([A-K]+[a-z])^+ ) = 4;
      f( 2, [L-Z] ) = 3;

      f( 3, [0-9] ) = 4;
      f( 3, [A-z] ) = 3

      f( 4, ([A-z]+[0-9])^* )= 4

   Minimizzazione:

      1. {0, 1, 2, 4} {3}
            {1, 2} ([A-K]+[L-Z])-> {3}

      2. {0, 4} {1, 2} {3}
            {0} (0 + 2 + 4 + 6 + 8) + (1 + 3 + 5 + 7 + 9) -> {1, 2}

      3. {0} {4} {1, 2} {3}
            {1} [A-K]-> {3}

      4. {0} {4} {1} {2} {3}

*/

public class Student{

   public static boolean scan( String s ){

      int state = 0;
      int i = 0;

      while( state >= 0 && i < s.length() ){

         final char ch = s.charAt( i++ );

         switch( state ){

            case 0:
               if( ch == '0' ||
                   ch == '2' ||
                   ch == '4' ||
                   ch == '6' ||
                   ch == '8' ) state = 1;
               else if( ch == '1' ||
                        ch == '3' ||
                        ch == '5' ||
                        ch == '7' ||
                        ch == '9' ) state = 2;
               else if( ch >= 'A' && ch <= 'z' ) state = 4;
               else state = -1;
                  break;

            case 1:
               if( ch == '0' ||
                   ch == '2' ||
                   ch == '4' ||
                   ch == '6' ||
                   ch == '8' ) state = 1;
               else if( ch == '1' ||
                        ch == '3' ||
                        ch == '5' ||
                        ch == '7' ||
                        ch == '9' ) state = 2;
               else if( ch >= 'A' && ch <= 'K' ) state = 3;
               else if( ch >= 'L' && ch <= 'z' ) state = 4;
               else state = -1;
                  break;

            case 2:
               if( ch == '0' ||
                   ch == '2' ||
                   ch == '4' ||
                   ch == '6' ||
                   ch == '8' ) state = 1;
               else if( ch == '1' ||
                        ch == '3' ||
                        ch == '5' ||
                        ch == '7' ||
                        ch == '9' ) state = 2;
               else if( ch >= 'L' && ch <= 'Z' ) state = 3;
               else if( ch >= 'A' && ch <= 'K' ||
                        ch >= 'a' && ch <= 'z') state = 4;
               else state = -1;
                  break;

            case 3:
               if( ch == '0' ||
                   ch == '2' ||
                   ch == '4' ||
                   ch == '6' ||
                   ch == '8' ) state = 4;
               else if( ch == '1' ||
                        ch == '3' ||
                        ch == '5' ||
                        ch == '7' ||
                        ch == '9' ) state = 4;
               else if( ch >= 'A' && ch <= 'z' ) state = 3;
               else state = -1;
                  break;

            case 4:
               if( ch == '0' ||
                   ch == '2' ||
                   ch == '4' ||
                   ch == '6' ||
                   ch == '8' ) state = 4;
               else if( ch == '1' ||
                        ch == '3' ||
                        ch == '5' ||
                        ch == '7' ||
                        ch == '9' ) state = 4;
               else if( ch >= 'A' && ch <= 'z' ) state = 4;
               else state = -1;
                  break;
         }
      }
         return state == 3;
   }

   public static void main( String[] args ){

      System.out.println( scan( args[ 0 ] ) ? "OK" : "NOPE" );
   }
}
