package com.gpengtao.test.aviator;

import com.googlecode.aviator.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengtao.geng on 2023/8/16 19:17.
 */
public class AviatorTest {

	@Test
	public void test_hello_world() throws IOException {
		// Compile the script into an Expression instance.
		Expression exp = AviatorEvaluator.getInstance().compileScript(this.getClass().getResource("/aviator/hello.av").getPath());

		// Run the expression.
		exp.execute();
	}

	@Test
	public void test_env() {
		Map<String, Object> env = new HashMap<>();
		env.put("a", 100);
		env.put("b", 10.5d);
		env.put("c", 20L);

		Expression exp = AviatorEvaluator.getInstance().compile("a-(b-c) > 100");
		Object ret = exp.execute(env);

		System.out.println(ret);
	}

	@Test
	public void test_interpreter() {
		// 解释执行引擎
		AviatorEvaluatorInstance defaultEngine = AviatorEvaluator.newInstance(EvalMode.INTERPRETER);
		// 打开跟踪执行
		defaultEngine.setOption(Options.TRACE_EVAL, true);
		// 打印options
		defaultEngine.getOptions().entrySet().forEach(System.out::println);

		Map<String, Object> env = new HashMap<>();
		env.put("a", 100);
		env.put("b", 10.5d);
		env.put("c", 20L);
		Expression exp = defaultEngine.compile("a-(b-c) > 100");
		Object ret = exp.execute(env);
		System.out.println(ret);
	}
}
