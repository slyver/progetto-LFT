import java.io.*;
import java.util.*;

public class Lexer {

   public static int line = 1;
   private char peek = ' ';

   private void readch( BufferReader br ){

      try {
         peek = ( char ) br.read();
      } catch( IOException e ){
         peek = ( char ) -1; //ERROR
      }
   }

   public Token lexical_scan( BufferReader br ){

      while( peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r' ){

         if( peek == '\n' ) line ++;

            readch( br );
      }

      switch( peek ){

         case '!':
            peek = ' ';
               return Token.not;

         case '+':
            peek = ' ';
               return Token.plus;


         case '-':
            peek = ' ';
               return Token.minus;

         case '*':
            peek = ' ';
               return Token.mult;

         case '/':
            peek = ' ';
               return Token.div;

         case ';':
            peek = ' ';
               return Token.semicolon;

         case '(':
            peek = ' ';
               return Token.lpt;


         case ')':
            peek = ' ';
               return Token.rpt;


         case '{':
            peek = ' ';
               return Token.lpg;

         case '}':
            peek = ' ';
               return Token.rpg;

         case '&':
            readch( br );
            if( peek == '&' ){
               peek = ' ';
                  return Word.and;
            }
            else{
               System.err.println( "Erroneus characters after '&' : " + peek );
                  return null;
            }

         case '||':
            readch( br );
            if( peek == '|' ){
               peek = ' ';
                  return Word.or;
            }
            else{
               System.err.println( "Erroneus characters after '|' : " + peek );
                  return null;
            }

         case ':':
            readch( br );
            if( peek == '=' ){
               peek = ' ';
                  return Word.and;
            }
            else{
               System.err.println( "Erroneus characters after ':' : " + peek );
                  return null;
            }

         case '<':
            readch( br );
            if( peek == '=' ){
               peek = ' ';
                  return Word.le;
            }
            else if( peek == '>'){
               peek = ' ';
                  return Word.ne;
            }
            else{
               peek = ' ';
                  return Word.lt;
            }


         case '>':
            readch( br );
            if( peek == '=' ){
               peek = ' ';
                  return Word.ge;
            }
            else{
               peek = ' ';
                  return Word.gt;
            }

         case '=':
            readch( br );
            if( peek == '=' ){
               peek = ' ';
                  return Word.eq;
            }
            else{
               peek = ' ';
                  return Word.assign;
            }

         case ( char ) -1:
            return new Token( Tag.EOF );

         default:
            if( Character.isLetter( peek ) )

      }
   }

}
