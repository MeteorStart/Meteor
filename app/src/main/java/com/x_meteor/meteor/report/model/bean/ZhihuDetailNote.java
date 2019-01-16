package com.x_meteor.meteor.report.model.bean;

import java.util.List;

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/15 15:35
 * @company:
 * @email: lx802315@163.com
 */
public class ZhihuDetailNote {
    /**
     * body : <div class="main-wrap content-wrap">
     <div class="headline">

     <div class="img-place-holder"></div>

     </div>

     <div class="content-inner">

     <div class="question">
     <h2 class="question-title"></h2>

     <div class="answer">

     <div class="meta">
     <img class="avatar" src="http://pic4.zhimg.com/v2-fccc74ce7a626f6f7c861885b9cd09ef_is.jpg">
     <span class="author">一言，</span><span class="bio">勿忘初心</span>
     </div>

     <div class="content">
     <p>其实工伤认定在国内司法实践中充满很多奇葩的故事，有些情况看起来匪夷所思的受伤都能认定为工伤，以至于前两天我们还在说：工伤认定这事情，主要就靠编故事。</p>
     <p>言归正传，<strong>工伤认定在我国是一个行政行为，由统筹地区社保行政部门作出</strong>。行政行为的特性决定着必须遵循合法性，法无授权即禁止，换言之<strong>哪些行为能够认定为工伤，是规定的非常死的，符合规定就是，不符合就不是。</strong></p>
     <p>《工伤保险条例》第十四条规定：职工有下列情形之一的，应当认定为工伤：（一）在工作时间和工作场所内，因工作原因受到事故伤害的；（二）工作时间前后在工作场所内，从事与工作有关的预备性或者收尾性工作受到事故伤害的；（三）在工作时间和工作场所内，因履行工作职责受到暴力等意外伤害的；（四）患职业病的；（五）因工外出期间，由于工作原因受到伤害或者发生事故下落不明的；（六）在上下班途中，受到非本人主要责任的交通事故或者城市轨道交通、客运轮渡、火车事故伤害的；（七）法律、行政法规规定应当认定为工伤的其他情形。</p>
     <p>《工伤保险条例》第十五条规定：职工有下列情形之一的，视同工伤：（一）在工作时间和工作岗位，突发疾病死亡或者在 48 小时之内经抢救无效死亡的；（二）在抢险救灾等维护国家利益、公共利益活动中受到伤害的；（三）职工原在军队服役，因战、因公负伤致残，已取得革命伤残军人证，到用人单位后旧伤复发的。</p>
     <p>稍微归类一下举出的情况肯定是不可能符合第十五条的规定的，可以排除，再看第十四条，其实<strong>工伤分为两类，一类是职业病，一类是意外事故。无论怎么解释，肥胖都算不上意外事故吧。再说职业病，2013 年 12 月 30 日，国家卫生计生委公布了《职业病分类和目录》，将职业病调整为 132 种，10 大类</strong>。</p>
     <p>这十大类是：1、职业性尘肺病及其他呼吸系统疾病。2、职业病皮肤病。3、职业性眼病。4、职业性耳鼻喉口腔疾病。5、职业性化学中毒。6、物理因素所致职业病。7、职业性放射性疾病。8、职业性传染病。9、职业性肿瘤。10、其他职业病。</p>
     <p>体重超标哪一个都放不进去。</p>
     <p>综上，在国内不算工伤。</p>
     </div>
     </div>

     <div class="view-more"><a href="http://www.zhihu.com/question/300585829">查看知乎讨论<span class="js-question-holder"></span></a></div>

     </div>

     <div class="question">
     <h2 class="question-title"></h2>

     <div class="answer">

     <div class="content">
     <p><strong>本题来自知乎圆桌 » <a class="internal" href="https://www.zhihu.com/roundtable/officehealth">拯救上班「卒」</a>，更多「办公室健康」话题相关讨论欢迎关注。</strong></p>
     </div>
     </div>

     </div>

     </div>
     </div><script type=“text/javascript”>window.daily=true</script>
     * image_source : 《千与千寻》
     * title : 在公司食堂吃太多导致体重超标，算工伤吗？
     * image : https://pic3.zhimg.com/v2-ecf330f2be4f4cb23cd4fc2d0cb05a4e.jpg
     * share_url : http://daily.zhihu.com/story/9705659
     * js : []
     * ga_prefix : 011510
     * images : ["https://pic3.zhimg.com/v2-e640466804d41ea78df973133edb6102.jpg"]
     * type : 0
     * id : 9705659
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<String> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
