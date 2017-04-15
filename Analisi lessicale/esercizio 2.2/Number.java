
public class Number extends Token {

   public int num = 0;

   public Number( String s ){

      super( Tag.NUM );

      for( int i = 0; i < s.length(); i++ )
         num = 10*num + Character.digit( s.charAt( i ), 10 );
   }

   public String toString(){ return "<" + tag + ", " + num +  ">"; }
}
