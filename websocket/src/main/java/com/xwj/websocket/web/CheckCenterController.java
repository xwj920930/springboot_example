package com.xwj.websocket.web;

import com.xwj.websocket.socket.MyWebSocket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CheckCenterController {

	//页面请求
	@RequestMapping("/socket/{username}/{toUsername}")
	public ModelAndView socket(@PathVariable String username,@PathVariable String toUsername) {
		ModelAndView mav=new ModelAndView("/socket");
		mav.addObject("username", username);
		mav.addObject("toUsername", toUsername);
		return mav;
	}
	//推送数据接口
	@ResponseBody
	@RequestMapping("/socket/push/{message}")
	public String pushToWeb(@PathVariable("message") String message) {
		MyWebSocket.sendInfo(message);
		return message;
	}
}