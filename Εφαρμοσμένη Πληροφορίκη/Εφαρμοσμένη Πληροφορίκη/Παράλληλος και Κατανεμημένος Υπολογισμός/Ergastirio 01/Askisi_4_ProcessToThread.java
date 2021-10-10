import java.io.*;
import java.lang.*;
import java.util.*;

class Askisi_4_ProcessToThread {
	
    public static void main(String[] arg) throws IOException, InterruptedException {
 
        // Dimiourgoume mia diergasia
        ProcessBuilder build = new ProcessBuilder();
		
		// I diergasia pou dimiourgisame tha kanei metaglotisi ena java arxeio.
		build.command("javac", "Askisi_4_ProcessToThread.java");
		
		// Trexoume tin diergasia.
		Process process = build.start();
		
		// Perimenoyme na teleiosei tin douleia tis i diergasia.
		process.waitFor();
		
		//Anathetoume mia alli douleia stin diergasia, na treksei ena programma java.
		build.command("java", "Askisi_4_ProcessToThread");
		
		// Trexoume tin diergasia kai pername san eksodo IO tin eksodo IO pou exei i trexon diergasia, dld to cmd.
		process = build.inheritIO().start();
		
		// Perimenoyme na teleiosei tin douleia tis i diergasia.
		process.waitFor();
		
    }
}
