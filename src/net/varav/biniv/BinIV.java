package net.varav.biniv;

public class BinIV {
	
	public static InputReader in = new InputReader(System.in);
	public static OutputWriter out = new OutputWriter(System.out);

	public static final String VERSION = "BinIV \"PreAlpha v0.0.0\"";
	
	public static String code;
	
	public static void main(String[] args) {
		if (args.length == 0) displayHelp();
		if (args[0].startsWith("-") || args[0].startsWith("/")) {
			switch (args[0].substring(1).toLowerCase()) {
			case "help":
			case "?":
				displayHelp();
				break;
			default:
				System.out.println("Unrecognized action: " + args[0]);
				displayHelp();
				break;
			}
		}
		code = FileUtils.readFile(args[0]);
		new BIVM(code).execute();
		
	}
	
	public static void terminate() {
		out.close();
	}
	
	public static void displayHelp() {
		System.out.println(//
				"Usage:\n" + //
						"\t-help -?\n" + //
						"\t\tDisplay help\n" + //
						"\t-version\n" + //
						"\t\tDisplay the current version");
		System.exit(0);
	}
}
