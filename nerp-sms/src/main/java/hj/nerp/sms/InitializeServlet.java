package hj.nerp.sms;

import hj.nerp.util.ThreadPoolContainer;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitializeServlet
 */
public class InitializeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		// Open SMS Service
		ThreadPoolContainer.getThreadPoolSender("SMS",
				"hj.nerp.sms.service.SmsSender",
				"hj.nerp.sms.service.SmsReceiver", 5000L);
	}

}
