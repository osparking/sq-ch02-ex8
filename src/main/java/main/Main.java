package main;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.PrintStream;
import java.util.function.Supplier;

import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
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
		BeanDefinitionCustomizer customizer = bd -> bd.setPrimary(true);
		context.registerBean("rookie", Parrot.class, supplier, customizer);
		
		var q = new Parrot();
		q.setName("미키");
		supplier = () -> q;
		
		context.registerBean("meekie", Parrot.class, supplier);
		
		var pInCtxt = (Parrot) context.getBean(Parrot.class);
		var ps = new PrintStream(System.out, true, UTF_8);
		ps.println(pInCtxt.getName());

	}

}
