import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Entree {
	public static boolean isNumber(String str) {
		try {  
			if(str.contains(".")) {
				str = str.replace(".", "");
				double d = Double.parseDouble(str);
			}
			else return false;

		}  
		catch(NumberFormatException nfe) {  
			return false;  
		}  
		return true;  
	}
	public static String readUntilSource(int i, ArrayList list) {
		String out = "";
		i++;
		while(!list.get(i).equals("SOURCE:")) {
			out += list.get(i) + " ";
			i++;
		}
		return out;
	}
	public static String readUntilNumber(int i, ArrayList list) {
		String out = "";
		i++;
		while(!isNumber((String)list.get(i)) && !list.get(i).equals("Test")) {
			out += list.get(i) + " ";
			i++;
		}
		return out;
	}
	public static String readUntilAnswer(int i, ArrayList list) {
		String out = "";
		i++;
		while(!(list.get(i).equals("A.") || list.get(i).equals("B.") || list.get(i).equals("C.") || list.get(i).equals("D."))) {
			out += list.get(i) + " ";
			i++;
		}
		return out;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = null, br2 = null;
		BufferedWriter bw = null;
		try{
			//TEST
			br = new BufferedReader(new FileReader("input.txt"));
			ArrayList<String> test = new ArrayList<String>();
			String line;
			while((line = br.readLine()) != null){
				for(String i: line.split(" ")) test.add(i);
			}
			
			
			int tnum = 0;
			String ttype = "";
			String[] question = new String[100];
			String[][] q = new String[100][4];
			boolean last = false;
			int num = 0;
			
			for(int i = 0; i<test.size(); i++) {
				
				if(test.get(i).equals("100.")) {
					last = true;
				}
				
				if(isNumber(test.get(i))) {
					question[num] = readUntilAnswer(i, test);
				}
				
				// Collect info based on triggers
				switch(test.get(i)) {
					case "Test":tnum = Integer.parseInt(test.get(i+1));
						break;
					case "EXAM":ttype = test.get(i-2)+" "+test.get(i-1);
						break;
					case "A.": q[num][0]= readUntilAnswer(i, test);
						break;
					case "B.": q[num][1] = readUntilAnswer(i, test);
						break;
					case "C.": q[num][2] = readUntilAnswer(i, test);
						break;
					case "D.": 
						String s = "";
						if(last) {
							for(int x = i+1; x < test.size(); x++) {
								
								s += test.get(x) + " ";
							}
							last = false;
						}else{
							s = readUntilNumber(i, test);
						}
						q[num][3] = s;
						num++;
						break;
				}	
			}
			
			//KEY
			br2 = new BufferedReader(new FileReader("key.txt"));
			ArrayList<String> key = new ArrayList<String>();
			String ln;
			while((ln = br2.readLine()) != null){
				for(String i: ln.split(" ")) key.add(i);
			}
			
			String[] ans = new String[100];
			String[] desc = new String[100];
			int n = 0;
			
			for(int i = 0; i < key.size(); i++){
				if(isNumber(key.get(i))){
					if(key.get(i+1).equals("A") || key.get(i+1).equals("B") || key.get(i+1).equals("C") || key.get(i+1).equals("D")){
						ans[n] = key.get(i+1);
						
						String s = "";
						if(key.get(i).equals("100.")){
							
							for(int x = i+2; x < key.size(); x++) {
								if(key.get(x).equals("Test") || key.get(x).equals("SOURCE:")){
									break;
								}
								s += key.get(x) + " ";
							}
							desc[n] = s;
						}
						else{
							desc[n] = readUntilSource(i+1, key);
						}
						n++;
					}
				}
			}
			
			//OUTPUT
			bw = new BufferedWriter(new FileWriter("out.txt"));
			for(int i = 0; i < 100; i++) {
				bw.write(String.format("%d.%s: %d. %s%n A. %s%n B. %s%n C. %s%n D. %s%n ANSWER: %s -%s%n%n", tnum, ttype, i+1, question[i], 
						q[i][0], q[i][1], q[i][2], q[i][3], ans[i], desc[i]));
			}
		}
		catch(IOException io) {
			System.err.print("Error");
		}
		finally{
			if(br != null) br.close();
			if(br2 != null) br2.close();
			if(bw != null) bw.close();
		}
	}
}
