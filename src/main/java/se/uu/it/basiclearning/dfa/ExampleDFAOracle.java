package se.uu.it.basiclearning.dfa;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.learnlib.api.oracle.SingleQueryOracle.SingleQueryOracleDFA;
import net.automatalib.words.Word;

public class ExampleDFAOracle implements SingleQueryOracleDFA<String> {
	

	ExampleDFAOracle() {
	}
	@Override
	public Boolean answerQuery(Word<String> prefix, Word<String> suffix) {
		Word<String> word = prefix.concat(suffix);
		String k=word.stream().reduce("",(a,b) -> a + b);
		System.out.println(k);
		ProcessBuilder pb = new ProcessBuilder("/bin/sh","run.sh",k);
        Process process;
		try {
			process = pb.start();
			BufferedReader stdInput= new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s = null;
			int count=0;
			while ((s = stdInput.readLine()) != null) {
				if(count==3){
					System.out.println(s);
					if(s.equals("T")){
						return true;
					}else{
						return false;
					}
				}else{
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
