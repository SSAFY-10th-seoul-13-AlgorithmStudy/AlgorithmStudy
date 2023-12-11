package week06_DataStructure;

import java.util.*;
import java.io.*;

public class Main_5430_G5_AC_김아린 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(in.readLine());
        
        for (int a = 0; a < T; a++) {
            String s = in.readLine();
            int N = Integer.parseInt(in.readLine());

            ArrayDeque<Integer> v = new ArrayDeque<Integer>();
            String arr = in.readLine();
            
            String[] arrSplit = arr.substring(1, arr.length()-1).split(",");
            for (String num : arrSplit) {
                if (!num.equals(""))
                    v.add(Integer.parseInt(num));
            }
            
            boolean swt = true;
            boolean reverse = true;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'R') {
                    reverse = !reverse;
                }
                else if (s.charAt(j) == 'D') {
                    if (v.size() == 0) {
                        swt = false;
                        System.out.println("error");
                        break;
                    }
                    else {
                        if (reverse)
                            v.pollFirst();
                        else
                            v.pollLast();
                    }
                }
            }
            
            if (swt) {
                StringBuilder sb = new StringBuilder("[");
                if (reverse) {
                    while(!v.isEmpty()) {
                        sb.append(v.pollFirst());
                        if (!v.isEmpty())
                            sb.append(",");
                    }
                }
                else {
                    while(!v.isEmpty()) {
                        sb.append(v.pollLast());
                        if (!v.isEmpty())
                            sb.append(",");
                    }
                }
                sb.append("]");
                System.out.println(sb.toString());
            }
        }
    }
}