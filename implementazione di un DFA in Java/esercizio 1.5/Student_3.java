/*

   stati:
      0. nessun input ricevuto ancora
         -iniziale
      1. lettera fra [A-K]
      2. lettera fra [L-Z]
      3. numero pari dopo stato 1, 3 o 4
         -accettazione
      4. numero dispari dopo 1, 3 o 4
      5. numero pari dopo stato 2, 5 o 6
      6. numero dispari dopo stato 2, 5 o 6
         -accettazione
      7. rifiuto

   Funzione di transizione:

      f( 0, [A-K] ) = 1;
      f( 0, [L-Z] ) = 2;
      f( 0, [a-z]+[0-9] ) = 7;

      f( 1, [a-z]+[A-Z] ) = 1;
      f( 1, (0 + 2 + 4 + 6 + 8) ) = 3;
      f( 1, (1 + 3 + 5 + 7 + 9) ) = 4;

      f( 2, [a-z]+[A-Z] ) = 2;
      f( 2, (0 + 2 + 4 + 6 + 8) ) = 5;
      f( 2, (1 + 3 + 5 + 7 + 9) ) = 6;

      f( 3, (0 + 2 + 4 + 6 + 8) ) = 3;
      f( 3, (1 + 3 + 5 + 7 + 9) ) = 4;
      f( 3, [a-z]+[A-Z] ) = 7;

      f( 4, (0 + 2 + 4 + 6 + 8) ) = 3;
      f( 4, (1 + 3 + 5 + 7 + 9) ) = 4;
      f( 4, [a-z]+[A-Z] ) = 7;

      f( 5, (0 + 2 + 4 + 6 + 8) ) = 5;
      f( 5, (1 + 3 + 5 + 7 + 9) ) = 6;
      f( 5, [a-z]+[A-Z] ) = 7;

      f( 6, (0 + 2 + 4 + 6 + 8) ) = 5;
      f( 6, (1 + 3 + 5 + 7 + 9) ) = 6;
      f( 6, [a-z]+[A-Z] ) = 7;

      f( 7, [A-Z]+[a-z]+[0-9] ) = 7;

   Minimizzazione
   1. {0, 1, 2, 4, 5, 7} {3, 6}
      f( {1, 2, 4, 5}, [A-Z]+[a-z]+[0-9] ) = {3, 6}

   2. {0, 7} {1, 2, 4, 5} {3, 6}
      f( {0}, [0-9] ) = {1, 2,}

   3. {0} {7} {1, 2, 4, 5} {3, 6}
      f( {1}, (0 + 2 + 4 + 6 + 8) ) = {3}

   4. {0} {7} {1} {2, 4, 5} {3, 6}
      f( {2}, (1 + 3 + 5 + 7 + 9) ) = {6}

   5. {0} {7} {1} {2} {4, 5} {3, 6}
      f( {4}, (0 + 2 + 4 + 6 + 8) ) = {3}

   6. {0} {7} {1} {2} {4} {5} {3, 6}
      f( {3}, (1 + 3 + 5 + 7 + 9) ) = {4}

   7. {0} {7} {1} {2} {4} {5} {3} {6}

*/

public class Student_3{

   public static boolean scan( String s ){

      int state = 0;
      int i = 0;

      while( state >= 0 && i < s.length() ){

         final char ch = s.charAt( i++ );

         switch( state ){

            case 0:

               if( ch >= 'A' && ch <= 'K' ) state = 1;

               else if( ch >= 'L' && ch <= 'Z' ) state = 2;

               else if( ch >= 'a' && ch <= 'z' ||
                        ch >= '0' && ch <= '9') state = 7;

               else state = -1;

                  break;

            case 1:

               if( ch >= 'A' && ch <= 'Z' ||
                   ch >= 'a' && ch <= 'z'   ) state = 1;


               else if( ch == '0' ||
                        ch == '2' ||
                        ch == '4' ||
                        ch == '6' ||
                        ch == '8'   ) state = 3;

               else if( ch == '1' ||
                        ch == '3' ||
                        ch == '5' ||
                        ch == '7' ||
                        ch == '9'   ) state = 4;

               else state = -1;

                  break;

            case 2:

               if( ch >= 'A' && ch <= 'Z' ||
                   ch >= 'a' && ch <= 'z'   ) state = 2;

               else if( ch == '0' ||
                        ch == '2' ||
                        ch == '4' ||
                        ch == '6' ||
                        ch == '8'   ) state = 5;

               else if( ch == '1' ||
                        ch == '3' ||
                        ch == '5' ||
                        ch == '7' ||
                        ch == '9'   ) state = 6;

               else state = -1;

                  break;

            case 3:

               if( ch == '0' ||
                   ch == '2' ||
                   ch == '4' ||
                   ch == '6' ||
                   ch == '8'   ) state = 3;

               else if( ch == '1' ||
                        ch == '3' ||
                        ch == '5' ||
                        ch == '7' ||
                        ch == '9' ) state = 4;

               else if( ch >= 'A' && ch <= 'Z' ||
                        ch >= 'a' && ch <= 'z'   ) state = 7;

               else state = -1;

                  break;

            case 4:

               if( ch == '0' ||
                   ch == '2' ||
                   ch == '4' ||
                   ch == '6' ||
                   ch == '8'   ) state = 3;

               else if( ch == '1' ||
                        ch == '3' ||
                        ch == '5' ||
                        ch == '7' ||
                        ch == '9' ) state = 4;

               else if( ch >= 'A' && ch <= 'Z' ||
                        ch >= 'a' && ch <= 'z'   ) state = 7;

               else state = -1;

                  break;

            case 5:

               if( ch == '0' ||
                   ch == '2' ||
                   ch == '4' ||
                   ch == '6' ||
                   ch == '8'   ) state = 5;

               else if( ch == '1' ||
                        ch == '3' ||
                        ch == '5' ||
                        ch == '7' ||
                        ch == '9' ) state = 6;

               else if( ch >= 'A' && ch <= 'Z' ||
                        ch >= 'a' && ch <= 'z'   ) state = 7;

               else state = -1;

                  break;

            case 6:

               if( ch == '0' ||
                   ch == '2' ||
                   ch == '4' ||
                   ch == '6' ||
                   ch == '8'   ) state = 5;

               else if( ch == '1' ||
                        ch == '3' ||
                        ch == '5' ||
                        ch == '7' ||
                        ch == '9' ) state = 6;

               else if( ch >= 'A' && ch <= 'Z' ||
                        ch >= 'a' && ch <= 'z'   ) state = 7;

               else state = -1;

                  break;

            case 7:

               if( ch >= 'A' && ch <= 'Z' ||
                   ch >= 'a' && ch <= 'z' ||
                   ch >= '0' && ch <= '9'   ) state = 7;

               else state = -1;

                  break;
         }
      }
         return state == 3 || state == 6;
   }

   public static void main( String[] args ){

      System.out.println( scan( args[ 0 ] ) ? "OK" : "NOPE" );
   }
}
