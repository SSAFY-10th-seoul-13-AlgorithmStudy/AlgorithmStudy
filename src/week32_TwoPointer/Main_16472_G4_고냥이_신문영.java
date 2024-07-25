package week32_TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main_16472_G4_고냥이_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		int answer = 0;
		Set<Character> charSet = new HashSet<>();
		Set<Character> tempSet;
		
		int currentLength = 0;
		for (int i = 0; i < str.length(); i++) {
			char character = str.charAt(i);
			
			if (!charSet.contains(character)) {
				if (charSet.size() < N) {
					charSet.add(character);
					currentLength++;
				} else {
					
					int currentPosition = i;
					tempSet = new HashSet<>();
					while (currentPosition >= 0 && tempSet.size() <= N) {
						tempSet.add(str.charAt(currentPosition));
						if (tempSet.size() > N) {
							tempSet.remove(str.charAt(currentPosition));
							break;
						}
						currentPosition--;
					}
					
					answer = Math.max(answer, Math.max(currentLength, i - currentPosition));
					charSet = tempSet;
					currentLength = i - currentPosition;
				}
			} else {
				currentLength++;
			}
		}
		
		answer = Math.max(answer, currentLength);
		
		System.out.println(answer);
	}
}