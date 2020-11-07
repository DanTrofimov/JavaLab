package ru.itis.words.app;

import ru.itis.words.utils.Replacer;
import com.beust.jcommander.JCommander;
public class Program {
	public static void main(String argv[]) {

		Args args = new Args();

		JCommander.newBuilder()
		.addObject(args)
		.build()
		.parse(argv);

		Replacer replacer = new Replacer();
		System.out.println(replacer.replace(args.text, 
			args.source, args.target));
	}
}
