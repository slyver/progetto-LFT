/*
   linguaggio che riconosca identificatori, stringe contenenti lettere, numeri,
   underscore, che non comincino con un numero o un underscore e che non possano
   essere solo composte di un underscore

   stati:
      0. nessun simbolo è stato letto ancora ( stato iniziale / rifiuto )
      1. il primo simbolo è un numero o un underscore ( rifiuto )
      2. il primo simbolo è una lettera ( accettazione )

   funzione di transizione:
      f( 0, [Az] ) = 2;
      f( 0, [09] ) = 1;
      f( 0, '_' ) = 1;
      f( 1, [Az] ) = 1;
      f( 1, [09] ) = 1;
      f( 1, '_' ) = 1;
      f( 2, [Az] ) = 2;
      f( 2, [09] ) = 2;
      f( 2, '_' ) = 2;

   */

   public class Identifier {

      public static boolean scan( String s ){

         int state = 0;
         int i = 0;

         while( state >= 0 && i < s.length() ){

            final char ch = s.charAt( i++ );

            switch( state ){

               case 0:
                  if( ch >= '0' && ch <= '9' || ch == '_' ) state = 1;
                  else if( ch >= 'A' && ch <= 'z' ) state = 2;
                  else state = -1;
                     break;

               case 1:
                  if( ch >= '0' && ch <= '9' ||
                      ch >= 'A' && ch <= 'z' ||
                      ch == '_') state = 1;
                  else state = -1;
                     break;

               case 2:
                  if( ch >= '0' && ch <= '9' ||
                     ch >= 'A' && ch <= 'z' ||
                     ch == '_') state = 2;
                  else state = -1;
                     break;
            }
         }
            return state == 2;
      }

      public static void main( String[] args ) {

         System.out.println( scan( args[ 0 ] ) ? "OK" : "NOPE" );
      }
   }
