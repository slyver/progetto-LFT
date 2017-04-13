/*
   riconoscimento delle matricole del turno 2 e 3 dove però il numero di
   matricola e il cognome possono essere separati da una sequenza di spazzi,
   possono essere precedute da spazzi e o seguite esempio particolare
   123456De Gasperi è accettato.
   suppungo dato che "1111B" veniva accettato lo sarà anche "111Bi B"

   Stati:
      0. non sono stati letti ancora simboli o sono state lette una o più
         spazzi vuoti.
         -iniziale

      1. è stata letta una cifra pari

      2. è stata letta una cifra dispari

      3. sono stati letti uno o più spazzi dopo un numero pari

      4. sono stati letti uno o più spazzi dopo un numero dispari

      5. è stata letta una fra [A-Z] consistente cogli stati precedenti
         -accettazione

      6. sono stati letti uno o più spazzi dopo la lettera maiuscola
         -accettazione

      7. sono stati letti caratteri minuscoli dopo la maiuscola
         -accettazione

      8. è stato letto un simbolo inatteso

   Funzione di transizione:

      f( 0, " " ) = 0
      f( 0, (0 + 2 + 4 + 6 + 8) ) = 1
      f( 0, (1 + 3 + 5 + 7 + 9) ) = 2
      f( 0, [A-z] ) = 8

      f( 1, " " ) = 3
      f( 1, (0 + 2 + 4 + 6 + 8) ) = 1
      f( 1, (1 + 3 + 5 + 7 + 9) ) = 2
      f( 1, [A-K] ) = 5
      f( 1, [L-z]+[0-9] ) = 8

      f( 2, " " ) = 4
      f( 2, (0 + 2 + 4 + 6 + 8) ) = 1
      f( 2, (1 + 3 + 5 + 7 + 9) ) = 2
      f( 2, [L-Z] ) = 5
      f( 2, [A-K]+[a-z] ) = 8

      f( 3, " " ) = 3
      f( 3, [A-K] ) = 5
      f( 3, [L-z]+[0-9] ) = 8

      f( 4, " " ) = 4
      f( 4, [L-Z] ) = 5
      f( 4, [A-K]+[a-z]+[0-9] ) = 8

      f( 5, " " ) = 6
      f( 5, [A-Z]+[0-9] ) = 8
      f( 5, [a-z] ) = 7

      f( 6, " " ) = 6
      f( 6, [A-Z] ) = 5
      f( 6, [a-z]+[0-9] ) = 8

      f( 7, " ") = 6
      f( 7, [a-z] ) = 7
      f( 7, [A-Z] ) = 5
      f( 7, [0-9] ) = 8

      f( 8, " "+[A-Z]+[0-9]+[a-z] ) = 8
*/

      public class Student_2{

         public static boolean scan( String s ){

            int state = 0;
            int i = 0;

            while( state >= 0 && i < s.length() ){

               final char ch = s.charAt( i++ );

               switch( state ){

                  case 0:

                     if( ch == ' ') state = 0;

                     else if( ch == '0' ||
                              ch == '2' ||
                              ch == '4' ||
                              ch == '6' ||
                              ch == '8'   ) state = 1;

                     else if( ch == '1' ||
                              ch == '3' ||
                              ch == '5' ||
                              ch == '7' ||
                              ch == '9'   ) state = 2;

                     else if( ch >= 'A' && ch <= 'z' ) state = 8;

                     else state = -1;

                        break;

                  case 1:

                     if( ch == ' ' ) state = 3;

                     else if( ch == '0' ||
                              ch == '2' ||
                              ch == '4' ||
                              ch == '6' ||
                              ch == '8'   ) state = 1;

                     else if( ch == '1' ||
                              ch == '3' ||
                              ch == '5' ||
                              ch == '7' ||
                              ch == '9'   ) state = 2;

                     else if( ch >= 'A' && ch <= 'K' ) state = 5;

                     else if( ch >= 'L' && ch <= 'Z' ||
                              ch >= 'a' && ch <= 'z' ||
                              ch >= '0' && ch <= '9'   ) state = 8;

                     else state = -1;

                        break;

                  case 2:

                     if( ch == ' ' ) state = 4;

                     else if( ch == '0' ||
                              ch == '2' ||
                              ch == '4' ||
                              ch == '6' ||
                              ch == '8'   ) state = 1;

                     else if( ch == '1' ||
                              ch == '3' ||
                              ch == '5' ||
                              ch == '7' ||
                              ch == '9'   ) state = 2;

                     else if( ch >= 'L' && ch <= 'Z' ) state = 5;

                     else if( ch >= 'A' && ch <= 'K' ||
                              ch >= 'a' && ch <= 'z'   ) state = 8;

                     else state = -1;

                        break;

                  case 3:

                     if( ch == ' ' ) state = 3;

                     else if( ch >= 'A' && ch <= 'K' ) state = 5;

                     else if( ch >= 'L' && ch <= 'Z' ||
                              ch >= 'a' && ch <= 'z' ||
                              ch >= '0' && ch <= '9'   ) state = 8;

                     else state = -1;

                        break;

                  case 4:

                     if( ch == ' ' ) state = 4;

                     else if( ch >= 'L' && ch <= 'Z' ) state = 5;

                     else if( ch >= 'A' && ch <= 'K' ||
                              ch >= 'a' && ch <= 'z' ||
                              ch >= '0' && ch <= '9'   ) state = 8;

                     else state = -1;

                        break;

                  case 5:

                     if( ch == ' ' ) state = 6;

                     else if( ch >= 'a' && ch <= 'z' ) state = 7;

                     else if( ch >= 'A' && ch <= 'Z' ||
                              ch >= '0' && ch <= '9'   ) state = 8;

                     else state = -1;

                        break;

                  case 6:

                     if( ch == ' ' ) state = 6;

                     else if( ch >= 'A' && ch <= 'Z' ) state = 5;

                     else if( ch >= 'a' && ch <= 'z' ||
                              ch >= '0' && ch <= '9'   ) state = 8;

                     else state = -1;
                        break;

                  case 7:

                     if( ch == ' ' ) state = 6;

                     else if( ch >= 'a' && ch <= 'z' ) state = 7;

                     else if( ch >= 'A' && ch <= 'Z' ) state = 5;

                     else if( ch >= '0' && ch <= '9' ) state = 8;

                     else state = -1;

                        break;

                  case 8:

                     if( ch == ' ' ||
                         ch >= 'a' && ch <= 'z' ||
                         ch >= 'A' && ch <= 'Z' ||
                         ch >= '0' && ch <= '9'   ) state = 8;

                     else state = -1;

                        break;

               }
            }
               return state == 5 || state == 6 || state == 7;
         }

         public static void main( String[] args ){

            System.out.println( scan( args[ 0 ] ) ? "OK" : "NOPE" );
         }
      }
