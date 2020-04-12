import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.my.utils.SignCount;
import java.util.Arrays;


public class TestRobot {

    public static void main(String[] args) throws Exception {
        SignCount signCount = new SignCount();
        String webhook="https://oapi.dingtalk.com/robot/send?access_token=XXXXXXXXXXXXXXXXXXXXXX";//输入获取的webhook地址
        String serverUrl=webhook+"&timestamp="+signCount.getTimestamp()+"&sign="+signCount.encode();
        DingTalkClient client = new DefaultDingTalkClient(serverUrl);
        System.out.println(serverUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();


        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("测试文本消息");
        request.setText(text);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList("132xxxxxxxx"));
        request.setAt(at);

        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl("https://www.dingtalk.com/");
        link.setPicUrl("");
        link.setTitle("时代的火车向前开");
        link.setText("这个即将发布的新版本，创始人xx称它为“红树林”。\n" +
                "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林");
        request.setLink(link);

        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("测试测试");
        markdown.setText("#### 测试测试\n" +
                "> 测试测试测试测试测试测试测试\n\n" +
                "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"  +
                "> ###### 11点11分发布 [测试](http://www.thinkpage.cn/) \n");
        request.setMarkdown(markdown);

        OapiRobotSendResponse response = client.execute(request);
        System.out.println(response.isSuccess());
    }
}
