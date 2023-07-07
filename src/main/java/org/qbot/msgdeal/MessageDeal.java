package org.qbot.msgdeal;

import com.alibaba.fastjson2.JSONObject;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.file.AbsoluteFile;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;
import org.qbot.PluginVersion;
import org.qbot.toolkit.PluginUtil;
import org.qbot.toolkit.Setting;
import org.qbot.toolkit.Utils;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * MessageDeal class
 *
 * @author 649953543@qq.com
 * @date 2021/09/22
 */

@SuppressWarnings({"ALL", "AlibabaMethodTooLong"})
public class MessageDeal {
    PluginUtil pluginUtil = new PluginUtil();
    Utils utils = new Utils();
    Group group = null;

    Path dataFolderPath = Utils.getPluginsDataPath();

    private static final String STRING_LISTENTOMUSIC = "听歌";
    private static final String STRING_QQ = "qq";
    private static final String STRING_SAY = "说";
    private static final String STRING_WE_CHAT = "微信";
    private static final String STRING_HOROSCOPE = "星座运势";
    private static final String STRING_COMBAT_POWER_QUERY = "战力查询";
    private static final String STRING_MUSIC_SYSTEM = "音乐系统";
    private static final String STRING_FAIL = "失败";
    private static final String STRING_FORTUNE = "运势";
    private static final String STRING_TODAY_FORTUNE = "今日运势";
    private static final String STRING_GET_FAILED = "获取失败";
    private static final String STRING_NEWS = "新闻";
    private static final String STRING_TODAY_NEWS = "今日新闻";
    private static final String STRING_BEAUTY = "美女";
    private static final String STRING_BEAUTY_PICTURES = "美女图片";
    private static final String STRING_GET_SIGN = "求签";
    private static final String STRING_GUANYIN_GET_SIGN = "观音求签";
    private static final String STRING_MENU = "菜单";
    private static final String STRING_WEATHER = "天气";
    private static final String STRING_HELP = "帮助";
    private static final String STRING_FISH = "摸鱼办";
    private static final String STRING_YUQING = "鱼情";
    private static final String STRING_YUQINGCX = "鱼情查询";

    private static final String STRING_BEAUTY_VIDEO = "美女视频";
    private static final String NUM_ONE = "1";
    private static final int NUM_ELEVEN = 11;
    private static final String[] TWELVE_HOROSCOPE = {"白羊", "金牛", "双子", "巨蟹", "狮子", "处女", "天秤", "天蝎", "射手", "摩羯", "水瓶", "双鱼"};
    private static final String[] BEAUTY_VIDEO_URL = {"http://www.yujn.cn/api/zzxjj.php", "http://www.yujn.cn/api/yuzu.php", "http://www.yujn.cn/api/tianmei.php", "http://www.yujn.cn/api/jksp.php", "http://www.yujn.cn/api/ndym.php", "http://www.yujn.cn/api/sbkl.php", "http://www.yujn.cn/api/rewu.php", "http://www.yujn.cn/api/luoli.php", "http://www.yujn.cn/api/manhuay.php", "http://www.yujn.cn/api/shejie.php"}
            ;

    ExecutorService executorService = Executors.newCachedThreadPool();

    @SuppressWarnings("AlibabaMethodTooLong")
    public void msgDel(Long senderId, String senderName, Group group, String msg, MessageChain msgchains) throws IOException,
            InterruptedException,
            ScriptException, NoSuchMethodException, ParseException {
        MessageChain chain;
        this.group = group;
        System.out.println("收到的消息:" + msg + " 消息长度:" + msg.length());
        if (STRING_FISH.equals(msg)) {
            chain = new MessageChainBuilder().append(new PlainText(pluginUtil.moFish())).build();
            group.sendMessage(chain);
            return;
        }


        if ("".equals(msg)) {
            chain = new MessageChainBuilder().append(new At(senderId)).append(new PlainText("\n你没得事情干唛？@我作甚么？")).append(new Face(312)).build();
            group.sendMessage(chain);
            return;
        }

        if(msg.equals(STRING_BEAUTY_VIDEO)){
            group.sendMessage("美女视频列表:\n" +
                    "1小姐姐视频\n" +
                    "2玉足美腿\n" +
                    "3甜妹系列\n" +
                    "4jk洛丽塔\n" +
                    "5你的欲梦\n" +
                    "6双倍快乐\n" +
                    "7热舞系列\n" +
                    "8萝莉系列\n" +
                    "9漫画芋\n" +
                    "10蛇姐系列\n"+
                    "例如：美女视频1");
            return;
        }

        if (msg.contains(STRING_BEAUTY_VIDEO)) {
            int type = 0;
            try{
                type = Integer.parseInt(msg.replace(STRING_BEAUTY_VIDEO, ""));
                System.out.println(type);
            }catch (Exception e){
                group.sendMessage("格式错误，请输入正确的格式");
                return;
            }
            try {
                MessageSource.recall(msgchains);
            } catch (Exception e) {
                System.out.printf("撤回失败1");
            }
            MessageReceipt<Group> messageReceipts = group.sendMessage("正在上传，请稍后...");
            String filePath = "";
            switch (type) {
                case 1:
                    filePath = pluginUtil.getVideo(BEAUTY_VIDEO_URL[0]);
                    break;
                case 2:
                    filePath = pluginUtil.getVideo(BEAUTY_VIDEO_URL[1]);
                    break;
                case 3:
                    filePath = pluginUtil.getVideo(BEAUTY_VIDEO_URL[2]);
                    break;
                case 4:
                    filePath = pluginUtil.getVideo(BEAUTY_VIDEO_URL[3]);
                    break;
                case 5:
                    filePath = pluginUtil.getVideo(BEAUTY_VIDEO_URL[4]);
                    break;
                case 6:
                    filePath = pluginUtil.getVideo(BEAUTY_VIDEO_URL[5]);
                    break;
                case 7:
                    filePath = pluginUtil.getVideo(BEAUTY_VIDEO_URL[6]);
                    break;
                case 8:
                    filePath = pluginUtil.getVideo(BEAUTY_VIDEO_URL[7]);
                    break;
                case 9:
                    filePath = pluginUtil.getVideo(BEAUTY_VIDEO_URL[8]);
                    break;
                case 10:
                    filePath = pluginUtil.getVideo(BEAUTY_VIDEO_URL[9]);
                    break;
                default:
                    group.sendMessage("格式错误，请输入正确的格式");
                    return;
            }

            File video = new File(filePath);
            try (ExternalResource resource = ExternalResource.create(video)) {
                AbsoluteFile uploadFile = group.getFiles().getRoot().uploadNewFile("/" + video.getName(), resource);
                messageReceipts.recall();
                int recallTimes = Setting.getImageRecall();
                try {
                    if (recallTimes != 0) {
                        Thread.sleep(recallTimes * 1000);
                        uploadFile.delete();
                    }
                } catch (Exception e) {
                    System.out.printf("撤回失败");
                }
            } catch (Exception e) {
                group.sendMessage("发送失败");
            } finally {

            }
            return;
        }

        if (msg.indexOf(STRING_LISTENTOMUSIC) == 0) {
            msg = msg.replace(STRING_LISTENTOMUSIC, "");
            if ("".equals(msg)) {
                chain = new PlainText("？ \n你总得告诉我要听什么吧？").plus(new Face(244));
                group.sendMessage(chain);
                return;
            }
            PluginUtil pluginUtil = new PluginUtil();
            MusicShare musicShare = pluginUtil.getMusic(msg);
            group.sendMessage(musicShare);
            return;
        }

        if (msg.indexOf(STRING_SAY) == 0) {
            msg = msg.replaceFirst(STRING_SAY, "");
            if ("".equals(msg)) {
                chain = new PlainText("？ \n你总得告诉我要说什么吧？").plus(new Face(244));
                group.sendMessage(chain);
                return;
            }
            PluginUtil pluginUtil = new PluginUtil();
            ExternalResource audio = pluginUtil.getVoice(msg);
            if (audio == null) {
                group.sendMessage("语音系统未配置，请联系管理员");
                return;
            }
            Audio audio1 = group.uploadAudio(audio);
            group.sendMessage(audio1);
            audio.close();
            return;
        }

        if (msg.contains(STRING_QQ)) {
            msg = msg.replace(STRING_QQ, "");
            msg = msg.replace(" ", "");
            JSONObject jsonObject = pluginUtil.getPower(msg, "qq");
            if (jsonObject == null) {
                group.sendMessage("获取失败");
                return;
            }
            String heroInfo = jsonObject.getString("data");
            String heroImg = jsonObject.getString("img");
            Image image = getImageAdd(heroImg);
            chain = new MessageChainBuilder().append(new At(senderId)).append(image).append(new PlainText("\n" + heroInfo)).build();
            group.sendMessage(chain);
            return;
        }

        if (msg.contains(STRING_WE_CHAT)) {
            msg = msg.replace(STRING_WE_CHAT, "");
            msg = msg.replace(" ", "");
            JSONObject jsonObject = pluginUtil.getPower(msg, "wx");
            if (jsonObject == null) {
                group.sendMessage("获取失败");
                return;
            }
            String heroInfo = jsonObject.getString("data");
            String heroImg = jsonObject.getString("img");
            Image image = getImageAdd(heroImg);
            chain = new MessageChainBuilder().append(new At(senderId)).append(image).append(new PlainText("\n" + heroInfo)).build();
            group.sendMessage(chain);
            return;
        }

        if (STRING_HOROSCOPE.equals(msg)) {
            chain = new MessageChainBuilder().append(new At(senderId)).append(new PlainText("\n请@我并发送星座名\n例如@XXX 白羊")).build();
            group.sendMessage(chain);
            return;
        }

        if (STRING_COMBAT_POWER_QUERY.equals(msg)) {
            chain = new MessageChainBuilder().append(new At(senderId)).append(new PlainText("\n请@我并发送qq/微信+英雄名\n例如@XXX qq 伽罗")).build();
            group.sendMessage(chain);
            return;
        }

        if (STRING_MUSIC_SYSTEM.equals(msg)) {
            chain = new MessageChainBuilder().append(new At(senderId)).append(new PlainText("\n请@我并发送听歌+音乐名\n例如@XXX 听歌稻香")).build();
            group.sendMessage(chain);
            return;
        }

        if (STRING_FORTUNE.equals(msg) || STRING_TODAY_FORTUNE.equals(msg)) {
            String replaceMsg = pluginUtil.getFortune();
            if (!replaceMsg.contains(STRING_GET_FAILED)) {
                chain = new At(senderId).plus(new PlainText("\n" + replaceMsg));
            } else {
                chain = new At(senderId).plus(new PlainText("\n" + replaceMsg).plus(new Face(263)));
            }
            group.sendMessage(chain);
            return;
        }

        if (STRING_NEWS.equals(msg) || STRING_TODAY_NEWS.equals(msg)) {
            String newsImgUrl = pluginUtil.getNews();
            if (!newsImgUrl.contains(STRING_FAIL)) {
                Image image = getImageAdd(newsImgUrl);
                chain = new MessageChainBuilder().append(image).build();
            } else {
                chain = new At(senderId).plus(new PlainText("\n获取失败"));
            }
            group.sendMessage(chain);
            return;
        }

        if (STRING_BEAUTY.equals(msg) || STRING_BEAUTY_PICTURES.equals(msg)) {
            ForwardMessageBuilder builder1 = new ForwardMessageBuilder(group);
            ForwardMessageBuilder builder = new ForwardMessageBuilder(group);

            builder.add(2119517658L, "一位不愿透露姓名的群友", getImageAdd(dataFolderPath + "/ewm.png"));
            builder.add(2119517658L, "一位不愿透露姓名的群友", new PlainText("注意\n由于tx限制\n请按照以下提示操作\n使用浏览器打开").plus(getImageAdd(dataFolderPath + "/ts.jpg")));
            builder.add(2119517658L, "一位不愿透露姓名的群友",
                    new PlainText("注意\n刷新即可切换图片").plus(new Face(271)));
            ForwardMessage forward = builder.build();
            ForwardMessage forward1 = builder1.add(2119517658L, "一位不愿透露姓名的群友", forward).build();
            MessageReceipt<Group> messageReceipts = group.sendMessage(forward1);
            int recallTimes = Setting.getImageRecall();
            try {
                MessageSource.recall(msgchains);
            } catch (Exception e) {
                System.out.printf("撤回失败1");
            }
            try {
                if (recallTimes != 0) {
                    Thread.sleep(recallTimes * 1000);
                    messageReceipts.recall();
                }
            } catch (Exception e) {
                System.out.printf("撤回失败");
            }
            return;
        }

        //获取星座运势
        for (String s : TWELVE_HOROSCOPE) {
            if (msg.equals(s) || msg.equals(s + "座")) {
                chain = new MessageChainBuilder().append(new At(senderId)).append(new PlainText("\n" + s + "座运势\n" + pluginUtil.getHoroscope(s))).build();
                group.sendMessage(chain);
                return;
            }
        }


        if (STRING_HELP.equals(msg)) {
            chain = new MessageChainBuilder().append(new At(senderId)).append(new PlainText("\n帮助文档:\nhttps://www" +
                    ".miraiqbot.top/\n当前版本：" + PluginVersion.VERSION_NUM)).build();
            group.sendMessage(chain);
            return;
        }

        if (STRING_GET_SIGN.equals(msg) || STRING_GUANYIN_GET_SIGN.equals(msg)) {
            File qqFile = new File(utils.getPluginsDataPath() + "/cache/qq/" + senderId + ".cache");
            String qian;
            if (qqFile.exists()) {
                qian = utils.readData(qqFile);
                if (NUM_ONE.equals(qian)) {
                    qian = pluginUtil.getCq();
                    utils.rewriteData(qqFile, qian);
                }
            } else {
                qian = pluginUtil.getCq();
                utils.rewriteData(qqFile, qian);
            }
            chain = new MessageChainBuilder().append(new At(senderId)).append(new PlainText("\n" + qian)).build();
            group.sendMessage(chain);
            return;
        }
        if (STRING_YUQING.equals(msg) || STRING_YUQINGCX.equals(msg)) {
            chain = new MessageChainBuilder().append(new PlainText(Utils.CocFishGet())).build();
            group.sendMessage(chain);
            return;
        }

        if (STRING_MENU.equals(msg)) {
            group.sendMessage(getMenuTxt());
        } else if (Setting.getAi()) {
            group.sendMessage(pluginUtil.aiReply(senderId, senderName, group.getId(), msg));
        }

    }

    /**
     * 菜单
     */
    public MessageChain getMenuTxt() {
        return new MessageChainBuilder().append(new Face(147)).append(new PlainText("           菜单          ")).append(new Face(147)).append(new PlainText("\n◇━━━━━━━━◇\n")).append(new Face(190)).append(new PlainText("今日运势  今日新闻")).append(new Face(190)).append(new PlainText("\n")).append(new Face(190)).append(new PlainText("星座运势  观音求签")).append(new Face(190)).append(new PlainText("\n")).append(new Face(190)).append(new PlainText("音乐系统  语音系统")).append(new Face(190)).append(new PlainText("\n")).append(new Face(190)).append(new PlainText("美女图片  美女视频")).append(new Face(190)).append(new PlainText("\n")).append(new Face(190)).append(new PlainText("战力查询  鱼情查询")).append(new Face(190)).append(new PlainText("\n◇━━━━━━━━◇\nPS:@我并发相应文字查看指令\n当前版本：" + PluginVersion.VERSION_NUM)).build();
    }


    public Image getImageAdd(String filepath) throws IOException {
        ExternalResource img = ExternalResource.create(new File(filepath));
        Image image = this.group.uploadImage(img);
        img.close();
        return image;
    }


}
