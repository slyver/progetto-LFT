import java.io.*;
import java.util.*;

public class Lexer {

   public static int line = 1;
   private char peek = ' ';

   private void readch( BufferedReader br ){

      try {
         peek = ( char ) br.read();
      } catch( IOException e ){
         peek = ( char ) -1; //ERROR
      }
   }

   public Token lexical_scan( BufferedReader br ){

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

         case '|':
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
                  return Word.assign;
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
               System.err.println( "Erroneus characters after '=' : " + peek );
                  return null;
            }

         case ( char ) -1: return new Token( Tag.EOF );

         default:

            if( Character.isLetter( peek ) ){

               String b = new String();

               do{
                  b += peek;
                     readch( br );
               } while( Character.isLetter( peek ) ||
                           Character.isDigit( peek ) );

               if( b.equals( "if" ) ) return Word.iftok;
               else if( b.equals( "else" ) ) return Word.elsetok;
               else if( b.equals( "while" ) ) return Word.whiletok;
               else if( b.equals( "read" ) ) return Word.read;
               else if( b.equals( "print" ) ) return Word.print;
               else return new Word( Tag.ID, b );
            }

            else if( Character.isDigit( peek ) ){

               String b = new String();

               do{
                  b += peek;
                     readch( br );
               } while( Character.isDigit( peek ) );

               return new Number( b );
            }

            else{
               System.err.println( "Erroneus character: " + peek );
                  return null;
            }
      }
   }

   public static void main( String[] args ){

      Lexer lex = new Lexer();

      String path = "input.txt";

      try{

         BufferedReader br = new BufferedReader( new FileReader( path ) );

         Token tok;

         do{
            tok = lex.lexical_scan( br );
               System.out.println( "Scan: " + tok );
         } while( tok.tag != Tag.EOF );

            br.close();

      } catch (IOException e) { e.printStackTrace(); }
   }
}
