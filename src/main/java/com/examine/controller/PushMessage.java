package com.examine.controller;


import com.examine.config.websocket.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class PushMessage {

    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav=new ModelAndView("/index");
        mav.addObject("cid", cid);
        return mav;
    }

    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public String pushToWeb(@PathVariable String cid,String message) {
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    /**
     * 消息推送
     *
     * @param message
     * @return
     * @throws IOException
     */
    @RequestMapping("/socket")
    public String push(String message) throws IOException {
        try {
            WebSocketServer.sendInfo(message,"0");
        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }
}
