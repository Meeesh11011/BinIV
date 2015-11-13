package net.varav.biniv;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BIVM {
	
	private final Stack<Integer> callStack = new Stack<Integer>();
	
	private final char[] data; //Memory
	private final char[] source; //Inputted code
	
	private short pointer = 0;
	private int eval = 0; //Index of the code
	
	private Map<Character, Action> instructionSet = new HashMap<Character, Action>();
	
	interface Action {
		public void action();
	}
	
	public BIVM(String code) {
		this.source = code.toCharArray();
		data = new char[32768];
		
		instructionSet.put('+', () -> this.data[this.pointer]++);
		instructionSet.put('-', () -> this.data[this.pointer]--);
		
		instructionSet.put('>', () -> this.pointer++);
		instructionSet.put('<', () -> this.pointer--);
		
		instructionSet.put('[', () -> System.out.println("Hi"));
		instructionSet.put(']', () -> System.out.println("Hi"));
		
		instructionSet.put('.', () -> print(this.data[this.pointer]));
		instructionSet.put(',', () -> this.data[this.pointer] = getCh());
	}
	
	private void print(char c) {
		BinIV.out.print(c);
	}
	
	private char getCh() {
		return (char) BinIV.in.read();
	}
	
	public int execute() {
		while(eval < source.length) {
			
		}
		return 0;
	}

}
