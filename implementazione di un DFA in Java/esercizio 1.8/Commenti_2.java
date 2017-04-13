/*
   stati:

      0. stato iniziale
         -accettazione

      1. è stato letto /
         -accettazione

      2. è stato letto * dopo /

      3. è stato letto *

      4. è stato letto / dopo *
         -accettazione

// Funzione di transizione:
/*
      f( 0, '/' ) = 1;
      f( 0, 'a'+'*' ) = 0;

      f( 1, '*' ) = 2;
      f( 1, 'a'+'/' ) = 0;

      f( 2, 'a'+'/' ) = 2;
      f( 2, '*' ) = 3;

      f( 3, 'a'+'*' ) = 2;
      f( 3, '/' ) = 4;

      f( 4, 'a'+'*' ) = 0;
      f( 4, '/' ) = 1;
*/

public class Commenti_2 {

   public static boolean scan( String s ){

      int state = 0;
      int i = 0;

      while( state >= 0 && i < s.length() ){

         final char ch = s.charAt( i++ );

         switch( state ){

            case 0:
               if( ch == '/') state = 1;
               else if( ch == 'a' || ch == '*' ) state = 0;
               else state = -1;
                  break;

            case 1:
               if( ch == '*') state = 2;
               else if( ch == 'a' || ch == '/' ) state = 0;
               else state = -1;
                  break;

            case 2:
               if( ch == '*') state = 3;
               else if( ch == 'a' || ch == '/' ) state = 2;
               else state = -1;
                  break;


            case 3:
               if( ch == '/') state = 4;
               else if( ch == 'a' || ch == '*' ) state = 2;
               else state = -1;
                  break;


            case 4:
               if( ch == '/') state = 1;
               else if( ch == 'a' || ch == '*' ) state = 0;
               else state = -1;
                  break;
         }
      }
         return state == 0 || state == 1 || state == 4;
   }

   public static void main( String[] args ){

      System.out.println( scan( args[ 0 ] ) ? "OK" : "NOPE" );
   }
}
