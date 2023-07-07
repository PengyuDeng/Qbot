package org.qbot.thread;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.utils.ExternalResource;
import org.qbot.toolkit.PluginUtil;
import org.qbot.toolkit.Setting;
import org.qbot.toolkit.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.qbot.toolkit.Utils.getPluginsDataPath;

/**
 * AutoThread class
 *
 * @author 649953543@qq.com
 * @date 2021/09/22
 */

public class AutoThread extends Thread {
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        Utils utils = new Utils();
        System.out.println("开启自动发送小提醒线程成功！");
        PluginUtil pluginUtil = new PluginUtil();
        Bot bot = Bot.getInstance(Setting.getQq());
        while (true) {
            try {
                Thread.sleep(1000);
                boolean autoTips = Setting.getAutoTips();
                boolean autoNews = Setting.getAutoNews();
                if ("08:30".equals(utils.getNowTime()) && autoNews) {
                    for (int i = 0; i < Setting.getGroup().size(); i++) {
                        try {
                            Group group = bot.getGroup(Setting.getGroup().getLongValue(i));
                            String newsImgUrl = pluginUtil.getNews();
                            if (!newsImgUrl.contains("失败")) {
                                ExternalResource img = ExternalResource.create(new File(newsImgUrl));
                                assert group != null;
                                Image image = group.uploadImage(img);
                                img.close();
                                MessageChain chain = new MessageChainBuilder()
                                        .append(new PlainText("今日新闻"))
                                        .append(image)
                                        .append(new PlainText(pluginUtil.getOne()))
                                        .build();
                                group.sendMessage(chain);
                            }
                        } catch (Exception e) {
                            System.out.println("发送小提醒失败");
                        }
                    }
                    sleep(60000);
                } else if ("15:00".equals(utils.getNowTime()) && autoTips) {
                    for (int i = 0; i < Setting.getGroup().size(); i++) {
                        try {
                            Group group = bot.getGroup(Setting.getGroup().getLongValue(i));
                            assert group != null;
                            MessageChain chain = new MessageChainBuilder()
                                    .append(new PlainText(pluginUtil.moFish()))
                                    .build();
                            group.sendMessage(chain);
                        } catch (Exception e) {
                            System.out.println("发送小提醒失败");
                        }
                    }
                    sleep(60000);
                }
                sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (!bot.isOnline()) {
                    try {
                        utils.sendMsgToWeChat("机器人已离线\n" + utils.getTime4());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("机器人已离线");
                } else {
                    System.out.println("机器人在线");
                }
            }
        }
    }
}
