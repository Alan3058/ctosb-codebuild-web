package test;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/*
 * @Project Name: ctosb-codebuild-web
 * @File Name: TestFreemarker.java
 * @Package Name:
 * @Date: 2017年9月29日上午9:51:14
 * @Creator: liliangang-1163
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */
/**
 * TODO
 * @author liliangang-1163
 * @date 2017年9月29日上午13:21:14
 * @see
 */
public class TestFreemarker {

	@Test
	public void testStringTemplateLoader() throws IOException {
		StringTemplateLoader stl = new StringTemplateLoader();
		String template = "${key}";
		stl.putTemplate("hello", template);
		Object source = stl.findTemplateSource("hello");
		Reader reader = stl.getReader(source, "utf-8");
		String dest = IOUtils.toString(reader);
		Assert.assertEquals(template, dest);
	}

	@Test
	public void testTemplate() throws TemplateException, IOException {
		String str = "${key}";
		Template template = new Template(null, str, null);
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "testkey");
		StringWriter stringWriter = new StringWriter();
		template.process(map, stringWriter);
		Assert.assertEquals(stringWriter.toString(),"testkey");
	}
}
