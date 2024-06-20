package week27_Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14725_G3_개미굴_신문영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<String> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(stringTokenizer.nextToken());
			StringBuilder stringBuilder = new StringBuilder();
			for (int j = 0; j < K; j++) {
				stringBuilder.append(stringTokenizer.nextToken());
				stringBuilder.append(" ");
			}
			list.add(stringBuilder.toString());
		}
		
		list.sort(String::compareTo);
		
		StringBuilder answer = new StringBuilder();
		
		String first = list.get(0);
		StringBuilder dash = new StringBuilder();
		StringTokenizer currentTokens = new StringTokenizer(first);
		int currentTokenLength = currentTokens.countTokens();
		for (int i = 0; i < currentTokenLength; i++) {
			answer.append(dash.toString() + currentTokens.nextToken());
			answer.append("\n");
			dash.append("--");
		}
		
		StringTokenizer before = new StringTokenizer(first);
		int beforeTokenLength = before.countTokens();
		
		for (int i = 1; i < list.size(); i++) {
			String current = list.get(i);
			dash = new StringBuilder();
			currentTokens = new StringTokenizer(current);
			currentTokenLength = currentTokens.countTokens();
			
			boolean isNotSame = false;
			int length = Math.min(beforeTokenLength, currentTokenLength);
			for (int j = 0; j < length; j++) {
				String beforeToken = before.nextToken();
				String currentToken = currentTokens.nextToken();
				if (beforeToken.equals(currentToken) && !isNotSame) {
					dash.append("--");
					continue;
				} else {
					isNotSame = true;
					answer.append(dash.toString() + currentToken);
					answer.append("\n");
					dash.append("--");
				}
			}
			
			while (currentTokens.hasMoreTokens()) {
				answer.append(dash.toString() + currentTokens.nextToken());
				answer.append("\n");
				dash.append("--");
			}
			
			before = new StringTokenizer(current);
			beforeTokenLength = before.countTokens();
		}
		
		answer.delete(answer.length() - "\n".length(), answer.length());
		System.out.print(answer);
	}
}