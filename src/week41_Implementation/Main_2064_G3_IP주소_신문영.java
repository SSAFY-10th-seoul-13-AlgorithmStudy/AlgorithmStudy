package week41_Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2064_G3_IP주소_신문영 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[][] ips = new String[n][4];
		for (int i = 0; i < n; i++) {
			ips[i] = br.readLine().split("\\.");
		}
		
		StringBuilder network = new StringBuilder();
		StringBuilder subnet = new StringBuilder();
		boolean isDiffrent = false;
		for (int i = 0; i < 4; i++) {
			int min = Integer.parseInt(ips[0][i]);
			int max = min;
			
			for (int j = 1; j < n; j++) {
				min = min & Integer.parseInt(ips[j][i]);
				max = max | Integer.parseInt(ips[j][i]);
			}
			
			if (!isDiffrent) {
				network.append(min).append(".");
				subnet.append(255 - (max - min)).append(".");
			} else {
				network.append(0).append(".");
				subnet.append(0).append(".");
			}
			
			if (min != max) isDiffrent = true;
		}
		
		network.delete(network.length() - 1, network.length());
		subnet.delete(subnet.length() - 1, subnet.length());
		System.out.println(network);
		System.out.println(subnet);
	}
	
}
