package redis.applications.message_verify.servlet;

import org.apache.commons.lang.StringUtils;
import redis.applications.message_verify.constant.CodeConfig;
import redis.clients.jedis.Jedis;
import redis.redisAPI.redisUtil.RedisUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 校验验证码的Servlet
 */
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//返回结果
		PrintWriter writer = response.getWriter();
		//解决响应乱码
		response.setCharacterEncoding("UTF-8");

		//获取用户的手机号和填入的验证码
		String phoneNo = request.getParameter("phone_no");
		String verifyCode = request.getParameter("verify_code");

		if(StringUtils.isEmpty(phoneNo) || StringUtils.isEmpty(verifyCode)){
			writer.write("手机号或者验证码不能为空！");
			return;
		}
		if(!phoneNo.matches("\\d{11}")|| !verifyCode.matches("\\d{"+CodeConfig.CODE_LEN +"}")){
			writer.write("手机号必须为11位，验证码长度不是"+CodeConfig.CODE_LEN+"位！");
			return;
		}

		//存储随机码用的
		String codeKey = CodeConfig.PHONE_PREFIX + phoneNo +CodeConfig.PHONE_SUFFIX;

		//从redis中获取存储的随机码
		Jedis jedis = RedisUtil.getJedis();
		String codeValue = jedis.get(codeKey);

		//比较用户输入的随机码 是否和redis中保存的随机码一样
		if(verifyCode.equals(codeValue)){
			System.out.println("验证码验证通过了！");
			writer.write("true");
		}else{
			System.out.println("验证没通过！");
			writer.write("false");
		}
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
