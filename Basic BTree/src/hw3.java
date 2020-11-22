
public class hw3 extends GetInputFromFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GetInputFromFile file = new GetInputFromFile();
		
		try {
			
			if( args.length>1)
				file.splitToTheTabCharacter( args[0], args[1]  );
			else if( args.length == 1 )
				file.splitToTheTabCharacter( args[0], "output.txt"  );
			else
				file.splitToTheTabCharacter( "input.txt", "output1.txt"  );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
/*
1. Heading1
  1.1. SubHeading1
    1.1.1. SubSubHeading1
    1.1.2. SubSubHeading2
    1.1.3. SubSubHeading3
  1.2. SubHeading2
2. Heading2
3. Heading3
__________________________
  ------>  Example input
(\t)2.1(" ")Title1(\n)
text
text
text
(\t)1.2(" ")Title2(\n)
text text
text
(\t)2(" ")Section 2(\n)
_________________________
  ------>   Example output
1
     1.1
     1.2 Title2
text text
text
2 Section 2
     2.1 Title1
text
text
text
*/
