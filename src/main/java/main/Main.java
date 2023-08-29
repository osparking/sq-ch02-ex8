package main;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.PrintStream;
import java.util.function.Supplier;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.ProjectConfig;

public class Main {
// @formatter:off
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(
											ProjectConfig.class);
		var p = new Parrot();
		p.setName("루키");

		Supplier<Parrot> supplier = () -> p;
		context.registerBean("rookie", Parrot.class, supplier);
		var pInCtxt = (Parrot) context.getBean("rookie", Parrot.class);
		var ps = new PrintStream(System.out, true, UTF_8);
		ps.println(pInCtxt.getName());

	}

}
