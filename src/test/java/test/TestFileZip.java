/*
 * @Project Name: ctosb-codebuild-web
 * @File Name: TestFileZip.java
 * @Package Name: build
 * @Date: 2017年9月29日下午5:05:14
 * @Creator: liliangang-1163
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * TODO
 * @author liliangang-1163
 * @date 2017年9月29日下午5:05:14
 * @see
 */
public class TestFileZip {

	@Test
	public void test() throws IOException {
		FileUtils.forceMkdir(new File("tmp"));
		ZipArchiveOutputStream archiveOutputStream = new ZipArchiveOutputStream(new File("tmp/test.zip"));
		for (int i = 0; i < 3; i++) {
			String directory = "tmp/folder" + i;
			String fileName = "Test" + i + ".txt";
			File folder = new File(directory);
			File file = new File(folder, fileName);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			FileUtils.write(file, "test" + Math.random() * 100, "utf-8");
			ZipArchiveEntry entry = new ZipArchiveEntry(file, "src/main/java/" + file.getName());
			archiveOutputStream.putArchiveEntry(entry);
			archiveOutputStream.write(FileUtils.readFileToByteArray(file));
			archiveOutputStream.closeArchiveEntry();
		}
		archiveOutputStream.finish();
		archiveOutputStream.close();
		FileUtils.forceDelete(new File("tmp"));
	}

	@Test
	public void testReplace() {
		String str = "com\\ctosb/test";
		Assert.assertEquals("com.ctosb.test", str.replaceAll("[/\\\\]", "."));
	}
}
