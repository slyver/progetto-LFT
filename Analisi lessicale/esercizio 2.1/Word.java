public class Word extends Token {

   public String lexeme = "";

   public Word( int tag, String s ){ super( tag ); lexeme = s; }

   public String toString(){ return "<" + tag + ", " + ">"; }

   public static final Word
      iftok = new Word( tag.IF, "if" ),
      elsetok = new Word( tag.ELSE, "else" ),
      whiletok = new Word( tag.WHILE, "while" ),
      assign = new Word( tag.ASSIGN, ":=" ),
      print = new Word( tag.PRINT, "print" ),
      read = new Word( tag.READ, "read" ),
      or = new Word( tag.OR, "||" ),
      and = new Word( tag.AND, "&&" ),
      lt = new Word( tag.RELOP, "<" ),
      gt = new Word( tag.RELOP, ">" ),
      eq = new Word( tag.RELOP, "==" ),
      le = new Word( tag.RELOP, "<=" ),
      ne = new Word( tag.RELOP, "<>" ),
      ge = new Word( tag.RELOP, ">=" ),
}
