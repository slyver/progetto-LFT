/*

   Stati:

      0. stato iniziale nessun simbolo letto
         -iniziale
      1. è stato letto '/' come primo simbolo
      2. è stato letto '*' come secondo simbolo
      3. non sono stati letti '/', '*' come primo e secondo simbolo
      4. è stato letto '*' come possibile penultimo simbolo
      5. è stato letto '/' come possibile ultimo simbolo
         -finale
   Funzione di transizione:

      f( 0, '/' ) = 1;
      f( 0, ('*'+'a') )= 3;

      f( 1, '*' ) = 2;
      f( 1, ('/'+'a') )= 3;

      f( 2, ('/'+'a') ) = 2;
      f( 2, '*' )= 4;

      f( 3, ('/'+'a'+'*') ) = 3;

      f( 4, '/' ) = 5;
      f( 4, ('*'+'a') )= 2;

      f( 5, ('*'+'a'+'*') )= 2;
*/

public class Commenti {

   public static boolean scan( String s ){

      int state = 0;
      int i = 0;

      while( state >= 0 && i < s.length() ){

         final char ch = s.charAt( i++ );

         switch( state ){

            case 0:
               if( ch == '/' ) state = 1;
               else if( ch == '*' || ch == 'a' ) state = 3;
               else state = -1;
                  break;

            case 1:
               if( ch == '*' ) state = 2;
               else if( ch == '/' || ch == 'a' ) state = 3;
               else state = -1;
                  break;


            case 2:
               if( ch == '/' || ch == 'a' ) state = 2;
               else if( ch == '*' ) state = 4;
               else state = -1;
                  break;

            case 3:
               if( ch == '*' || ch == '/' || ch == 'a' ) state = 3;
               else state = -1;
                  break;

            case 4:
               if( ch == '/' ) state = 5;
               else if( ch == '*' || ch == 'a' ) state = 2;
               else state = -1;
                  break;

            case 5:
               if( ch == '/' || ch == '*' || ch == 'a' ) state = 2;
               else state = -1;
                  break;
         }
      }
         return state == 5;
   }

   public static void main( String[] args ){

      System.out.println(  scan( args[ 0 ] ) ? "OK" : "NOPE" );
   }
}
