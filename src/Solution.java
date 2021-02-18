import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
	public static void main(String[] args) {

		final Scanner sc = new Scanner(System.in);
		final int N = Integer.valueOf(sc.nextLine());
		
		/* REG_EX breakdown
		 * ****************
		 * Tokens satisfying the parameters between first "(" and ")" would be m.group(1)
		 * < -> Tag open character
		 * . -> Any character except line breaks
		 * + -> At least ONE match of the preceding token
		 * > -> Tag close character
		 * 
		 * Second group, m.group(2), which is what we want to print, would be anything in between our group 1 and 3 
		 * parameters that contains anything that is NOT "<"
		 * [ ] -> Content between square brackets indicates a range
		 * ^   -> Not
		 * <   -> Tag opener character
		 * +   -> At least ONE match of the preceding token
		 * 
		 * And finally, m.group(3), would be the exact same word/tag that matched the m.group(1) but with
		 * the preceding "/". Note that since we are using a reference to group one with the "\1" escaped 
		 * character modifier, the third group will be the exact same as the group one, case sensitive.
		 * That way we ensure to meet the problem criteria while capturing any word in runtime.
		 * 
		 * The added backslash is for compatibility purposes
		 */
		for (int i = 0; i < N; i++) {
			final String LINE = sc.nextLine();
			final String REG_EX = "<(.+)>([^<]+)</\\1>";
			boolean printNone = true;

			Pattern p = Pattern.compile(REG_EX);
			Matcher m = p.matcher(LINE);

			while (m.find()) {
				System.out.println(m.group(2));
				printNone = false;
			}

			if (printNone)
				System.out.println("None");
		}
		sc.close();
	}
}
