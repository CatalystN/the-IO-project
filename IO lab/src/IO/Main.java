package IO;
import java.io.*;
import java.lang.System;

public class Main {
	private static File wd = new File(System.getProperty("user.dir"));
	protected static void exit(String[] cmd) {
		System.exit(0);
	}
	
	protected static void pwd(String[] cmd) {
		//System.out.println(System.getProperty("user.dir"));
		try {
			System.out.println(wd.getCanonicalPath());
		}
		catch(IOException ex) {
			System.out.println("IO err");
		}
		//System.out.println(dir.listFiles().length);
	}
	
	protected static void ls(String[] cmd) {
		if(cmd.length>1 && cmd[1].equals("-l"))
			for(File file: wd.listFiles()) {
				long size = file.length();
				System.out.print(file);
				if(size > 0)
					System.out.println(" " + size + " f");
				else
					System.out.println(" d");
			}
		else
			for(File file: wd.listFiles()) {
				System.out.println(file);
			}
	}

	protected static void cd(String[] cmd) {
		if(cmd[1].equals(".."))
			wd = wd.getParentFile();
		else
			wd = new File(wd, cmd[1]);
	}
	
	protected static void rm(String[] cmd) {
		File temp = new File(cmd[1]);
		try {
			if(temp.delete())
				System.out.println("File deleted successfully");
			else
				System.out.println("Failed to delete (file missing or dir contains files)");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	protected static void length(String[] cmd) {
		wd.length();
	}
	
	public static void main(String[] args) throws IOException {
		
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		while(true) {
			String line = reader.readLine();
			if(line == null) break;
			String[] cmd = line.split(" ");
			switch(cmd[0]) {
				case"exit":
					exit(cmd);
					break;
				case"pwd":
					pwd(cmd);
					break;
				case"ls":
					ls(cmd);
					break;
				case"cd":
					cd(cmd);
					break;
				case"rm":
					rm(cmd);
					break;
				default:
					for(String a : cmd)
						System.out.println(a);
					break;
			}
		}
		

		
		reader.close();		
	}
}
