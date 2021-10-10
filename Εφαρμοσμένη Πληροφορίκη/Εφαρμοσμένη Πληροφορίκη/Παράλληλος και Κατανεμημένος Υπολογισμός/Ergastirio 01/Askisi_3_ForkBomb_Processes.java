import java.io.*;
import java.lang.*;
import java.util.*;

public class Askisi_3_ForkBomb_Processes {
    
	public static void main(String[] args) throws IOException, InterruptedException {

		while(true) {
            Process p = new ProcessBuilder("java", "Askisi_3_ForkBomb").start();
			p.waitFor();
        }
    }
}
