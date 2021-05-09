package redis.applications.message_verify.servlet;

import redis.applications.message_verify.constant.CodeConfig;
import redis.clients.jedis.Jedis;
import redis.redisAPI.redisUtil.RedisUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * 02 发送验证码的Servlet
 */
public class SendCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//返回结果
		PrintWriter writer = response.getWriter();
		//解决响应乱码
		response.setCharacterEncoding("UTF-8");

		//获取手机号
		String phoneNo = request.getParameter("phone_no");

		//对手机号进行格式验证，不符合格式要求就返回false ；
		if(!phoneNo.matches("\\d{11}")){
			writer.write("false");
			return;
		}

		//获取jedis
		Jedis jedis = RedisUtil.getJedis();
		//代码复用
		String prefix = CodeConfig.PHONE_PREFIX + phoneNo;
		//存储随机码用的
		String codeKey = prefix+CodeConfig.PHONE_SUFFIX;
		//存储计数器用的
		String countKey = prefix+  CodeConfig.COUNT_SUFFIX;

		//TODO 单日登录的次数的处理
		//如果存在 key:countKey；key值增加1； 否则 countKey为key，1为value进行存储到redis中；
		Long incr = jedis.incr(countKey);
		if(incr>CodeConfig.COUNT_TIMES_1DAY){
			writer.write("limit:超过了单日最多发送次数");
			return;
		}
		if(incr ==1){ //第一次获取验证码
			//设置有效时间为24小时  24小时后，重新计数
			jedis.expire(countKey,CodeConfig.SECONDS_PER_DAY);
		}

		//TODO 随机码的处理
		//获取6位随机码
		String codeValue = getCode(CodeConfig.CODE_LEN);
		//这里进行验证码的发送(打印代替)
		System.out.println("您第【"+incr+"】次获取的验证码为："+codeValue);

		//将获取的随机码保存到redis中，并设置有效的时间为 CodeConfig.CODE_TIMEOUT
		//jedis.expire(jedis.set(codeKey,codeValue),CodeConfig.CODE_TIMEOUT);
		jedis.setex(codeKey,CodeConfig.CODE_TIMEOUT,codeValue);

		//返回true :单日登录次数没超过； 验证码已经保存到redis中了。
		writer.write("true");
 		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 随机生成验证码的方法
	private String getCode(int len) {
		String code = "";
		for (int i = 0; i < len; i++) {
			int rand = new Random().nextInt(10);
			code += rand;
		}
		return code;
	}

}
