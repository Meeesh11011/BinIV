package net.varav.biniv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class BFVM {

	private final Stack<Integer> callStack = new Stack<Integer>();

	private final char[] memory; // Memory
	private final char[] source; // Inputted code

	private short pointer = 0;
	private int sloc = 0; // Index of the code

	private Map<Character, Action> instructionSet = new HashMap<Character, Action>();

	interface Action {
		public void execute();
	}

	public BFVM(String data) {
		memory = new char[32768];

		instructionSet.put('+', () -> this.memory[this.pointer]++);
		instructionSet.put('-', () -> this.memory[this.pointer]--);

		instructionSet.put('>', () -> this.pointer++);
		instructionSet.put('<', () -> this.pointer--);

		instructionSet.put('[', () -> this.callStack.push(sloc));
		instructionSet.put(']', () -> {
			if(this.memory[this.pointer] != 0) this.sloc = this.callStack.peek();
			else this.callStack.pop();
		});

		instructionSet.put('.', () -> print(this.memory[this.pointer]));
		instructionSet.put(',', () -> this.memory[this.pointer] = getCh());
		
		this.source = clean(data);
		System.out.println("Executing: " + new String(source) + "\n");
	}
	
	private char[] clean(String data) { //TODO: Optimize
		char[] source = data.toCharArray();
		List<Character> chars = new ArrayList<Character>();
		HashSet<Character> keys = new HashSet<Character>(instructionSet.keySet());
		for(int i = 0; i < data.length(); i++) {
			if(keys.contains(source[i])) chars.add(source[i]);
		}
		char[] out = new char[chars.size()];
		for(int i = 0; i < out.length; i++) {
		    out[i] = chars.get(i);
		}
		return out;
	}

	private void print(char c) {
		BinIV.out.print(c);
		BinIV.out.flush();
	}

	private char getCh() {
		return (char) BinIV.in.read();
	}

	public int execute() {
		while (sloc < source.length) {
			instructionSet.get(source[sloc++]).execute();
		}
		return 0;
	}

}
