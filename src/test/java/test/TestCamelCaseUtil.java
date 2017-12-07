
package test;

import com.ctosb.codebuild.util.CamelCaseUtil;
import org.junit.Assert;
import org.junit.Test;

/*
 * @Project Name: ctosb-codebuild-web
 * @File Name: TestCamelCaseUtil.java
 * @Package Name:
 * @Date: 2017年9月28日上午11:30:22
 * @Creator: liliangang-1163
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */
/**
 * TODO
 * @author liliangang-1163
 * @date 2017年9月28日上午13:30:22
 * @see
 */
public class TestCamelCaseUtil {

	@Test
	public void test() {
		System.out.println(CamelCaseUtil.toUnderlineName("ISOCertifiedStafF"));
        System.out.println(CamelCaseUtil.toUnderlineName("CertifiedStaFF"));
        System.out.println(CamelCaseUtil.toUnderlineName("UserID"));
        System.out.println(CamelCaseUtil.toCamelCase("iso_certified_staff"));
        System.out.println(CamelCaseUtil.toCamelCase("certified_staff"));
        System.out.println(CamelCaseUtil.toCamelCase("user_id"));
	}

}
