package algorithms.LCS;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubsequenceBruteForce {
	static List<String> subsequenceList = new ArrayList<String>();
	static List<String> resultList = new ArrayList<String>();

	public static List<String> bruteForce(String s1, String s2) {
		getAllSubsequence(s1, "", 0);
		int longest = 0;
		for (String s : subsequenceList) {
			int i = 0, j = 0;
			while (i < s.length() && j < s2.length()) {
				if (s.charAt(i) == s2.charAt(j)) {
					i++;
				}
				j++;
			}
			if (i == s.length() && j <= s2.length()) {
				if (s.length() == longest && !resultList.contains(s)) {
					resultList.add(s);
				} else if (s.length() > longest) {
					longest = s.length();
					resultList.clear();
					resultList.add(s);
				}
			}
		}
		return resultList;
	}

	public static void getAllSubsequence(String s, String sTemp, int start) {
		if (start == s.length() - 1) {
			subsequenceList.add(sTemp);
			subsequenceList.add(sTemp + s.charAt(start));
			return;
		}

		getAllSubsequence(s, sTemp + s.charAt(start), start + 1);
		getAllSubsequence(s, sTemp, start + 1);
	}

	public static void main(String[] args) {
		//getAllSubsequence("ABCBDAB", "", 0);
		//System.out.println(subsequenceList);
		System.out.println(bruteForce("ABCBDAB", "BDCABA"));
		System.out.println(bruteForce("sea", "eat"));
	}
}
