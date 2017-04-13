/*
   hint: 2^k mod 3 = { 1 se k è pari e 2 se k è dispari }

   Stati:
      0. somma parziale = 0 pari
      1. somma parziale = 0 dispari
      2. somma parziale = 1 pari
      3. somma parziale = 1 dispari
      4. somma parziale = 2 pari
      5. somma parziale = 2 dispari

   Funzione di transizione:

      f( 0, '1' ) = 5;
      f( 0, '0' ) = 1;

      f( 1, '1' ) = 2;
      f( 1, '0' ) = 0;

      f( 2, '1' ) = 1;
      f( 2, '0' ) = 3;

      f( 3, '1' ) = 4;
      f( 3, '0' ) = 2;

      f( 4, '1' ) = 3;
      f( 4, '0' ) = 5;

      f( 5, '1' ) = 0;
      f( 5, '0' ) = 4;

*/

public class MultiTre {

   public static boolean scan( String s ){

      int state = 1;
      int i = 0;

      while( state >= 0 && i < s.length() ){

         final char ch = s.charAt( i++ );

         switch( state ){

            case 0:
               if( ch == '1' ) state = 5;
               else if( ch == '0') state = 1;
               else state = -1;
                  break;

            case 1:
               if( ch == '1' ) state = 2;
               else if( ch == '0') state = 0;
               else state = -1;
                  break;

            case 2:
               if( ch == '1' ) state = 1;
               else if( ch == '0') state = 3;
               else state = -1;
                  break;

            case 3:
               if( ch == '1' ) state = 4;
               else if( ch == '0') state = 2;
               else state = -1;
                  break;

            case 4:
               if( ch == '1' ) state = 3;
               else if( ch == '0') state = 5;
               else state = -1;
                  break;

            case 5:
               if( ch == '1' ) state = 0;
               else if( ch == '0') state = 4;
               else state = -1;
                  break;
         }
      }
         return state == 0 || state == 1;
   }

   public static void main( String[] args ){

      System.out.println( scan( args[ 0 ] ) ? "OK" : "NOPE" );
   }
}
