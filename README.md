# travels-旅游项目
# **旅游项目**

项目地址：https://github.com/szluyu99/travels

## **技术栈：**

后端：springboot  + mybatis

前端：axios + Json

前端技术栈、技术框架：Vue + node.js

## **前置知识：**

- 了解vue组件的简单使用
- 熟悉springboot、mybatis

## **学习过程：**

- 跑通项目

- 了解未接触到的知识

- 尝试做修改

- 把一些同时试着处理出来

  

# **后端启动：**

## **任务：**

1. 建立数据库
2. 导入相关依赖包
3. 修改相关配置文件

## **建立数据库：**

建表：

```mysql
#用户表
CREATE TABLE t_user(
    id INT(6) PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(60),
    password VARCHAR(60),
    email VARCHAR(60)
);
#省份表
CREATE TABLE t_province(
    id INT(6) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(60),
    tags VARCHAR(80),
    placecounts INT(4)
);
#景点表
CREATE TABLE t_place(
    id INT(6) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(60),
    picpath MEDIUMTEXT,
    hottime    TIMESTAMP,
    hotticket    DOUBLE(7,2),
    dimticket    DOUBLE(7,2),
    placedes    VARCHAR(300),
    provinceid    INT(6) REFERENCES t_province(id)
);

```



## **导入相关依赖：**

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
   <modelVersion>4.0.0</modelVersion>

   <groupId>com.xx</groupId>
   <artifactId>travels</artifactId>
   <version>0.0.1-SNAPSHOT</version>
   <name>travels</name>
   <description>简单的springboot + vue练手项目</description>

   <properties>
      <java.version>1.8</java.version>
   </properties>

   <!--继承springboot父项目-->
   <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>2.2.6.RELEASE</version>
      <relativePath/> <!-- lookup parent from repository -->
   </parent>

   <dependencies>
      <!--引入web支持-->
      <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
      <!--mybatis-->
      <dependency>
         <groupId>org.mybatis.spring.boot</groupId>
         <artifactId>mybatis-spring-boot-starter</artifactId>
         <version>2.1.2</version>
      </dependency>
      <!--热部署工具-->
      <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-devtools</artifactId>
         <scope>runtime</scope>
         <optional>true</optional>
      </dependency>
      <!--mysql-->
      <dependency>
         <groupId>mysql</groupId>
         <artifactId>mysql-connector-java</artifactId>
         <version>5.1.43</version>
         <scope>runtime</scope>
      </dependency>
      <!--druid-->
      <dependency>
         <groupId>com.alibaba</groupId>
         <artifactId>druid</artifactId>
         <version>1.1.12</version>
      </dependency>
      <!--lombok-->
      <dependency>
         <groupId>org.projectlombok</groupId>
         <artifactId>lombok</artifactId>
         <optional>true</optional>
      </dependency>
      <!--测试-->
      <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-test</artifactId>
      </dependency>
      <!--文件上传工具-->
      <dependency>
         <groupId>commons-fileupload</groupId>
         <artifactId>commons-fileupload</artifactId>
         <version>1.4</version>
      </dependency>
    </dependencies>

   <build>
      <plugins>
         <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
         </plugin>
      </plugins>
   </build>

</project>

```

**修改配置文件：**



```java
#端口
server.port=8989

#项目名称
spring.application.name=travels

#数据库
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/travels?characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=root

#mybatis配置
mybatis.mapper-locations=classpath:com/xx/travels/mapper/*.xml
mybatis.type-aliases-package=com.xx.travels.entity

#打印日志
logging.level.root=info
logging.level.com.xx.travels.dao=debug

#上传文件地址

upload.dir=E:/gitcode/travels-master/images
spring.resources.static-locations=file:${upload.dir}

#放行静态资源

spring.web.resources.static-locations=classpath:/static/
```

# **前端启动：**



## **任务：**

1. 定位资源文件
2. 采用tomcat打包部署
3. 部署tomcat

## **设置Artifacts**

IDEA中Artifact的配置

### **什么是Artifact**

在我们用idea初次开发web项目时，部署Tomcat时会遇到配置Artifacts这个问题，那么这个Artifact到底是什么呢？Artifacts表示某个module要如何打包，可以简单地理解为一个module有了 Artifacts 就可以部署到应用服务器中了。

![1687609372645](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687609372645.png)

添加完成后，发现他提示说’Web’并不包含在任何Artifacts中，于是我们点击create Artifact去创建一个Artifact

![1687609385154](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687609385154.png)

按图示配置好后，随后依次点击Apply|OK

**注:在选择Type类型时，这里选择Exploded，exploded原意为爆炸，爆发，迸发，在这里可以将exploded理解为展开，不压缩，也就是说上图所示红框中目录会一并展示出来，而不进行折叠**

### **Tomcat服务器的配置**

随后在主菜单Run中找到Edit Configurations…打开，随后可以点击左上角的’+'号，从而配置本地Tomcat服务器

![1687609433035](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687609433035.png)

依次设置好各项选项后，发现最底下有个警告，说是没有标记的要部署Artifacts，那我们就标记它，点击fix进行下一步

![1687609444414](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687609444414.png)

在这里我们可以看到我们刚才创建的Artifacts，那么依次点击Apply|OK就可以了

# **项目展示：**

## **初始页面（登录页面）**

![1687609510671](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687609510671.png)

## **注册页面：**

![1687609524666](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687609524666.png)

## **省份列表：**

![1687609536432](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687609536432.png)

## **添加省份：**

![1687609556508](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687609556508.png)

## **修改省份：**

![1687609571208](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687609571208.png)

## **景点列表：**

![1687609583865](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687609583865.png)

## **添加景点：**

![1687609594384](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687609594384.png)

## **修改景点：**

![1687609605658](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687609605658.png)

# **核心功能学习：**

## **验证码实现：**

### **验证码的工具类：**

```java
package com.xx.travels.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;


public class CreateImageCode {
    // 图片的宽度。
    private int width = 160;
    // 图片的高度。
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 4;
    // 验证码干扰线数
    private int lineCount = 20;
    // 验证码
    private String code = null;
    // 验证码图片Buffer
    private BufferedImage buffImg = null;
    Random random = new Random();

    public CreateImageCode() {
        creatImage();
    }

    public CreateImageCode(int width, int height) {
        this.width = width;
        this.height = height;
        creatImage();
    }

    public CreateImageCode(int width, int height, int codeCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        creatImage();
    }

    public CreateImageCode(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        creatImage();
    }

    // 生成图片
    private void creatImage() {
        int fontWidth = width / codeCount;// 字体的宽度
        int fontHeight = height - 5;// 字体的高度
        int codeY = height - 8;

        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.getGraphics();
        //Graphics2D g = buffImg.createGraphics();
        // 设置背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);



        // 设置字体
        //Font font1 = getFont(fontHeight);
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        g.setFont(font);

        // 设置干扰线
        for (int i = 0; i < lineCount; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width);
            int ye = ys + random.nextInt(height);
            g.setColor(getRandColor(1, 255));
            g.drawLine(xs, ys, xe, ye);
        }

        // 添加噪点
        float yawpRate = 0.01f;// 噪声率
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            buffImg.setRGB(x, y, random.nextInt(255));
        }


        String str1 = randomStr(codeCount);// 得到随机字符
        this.code = str1;
        for (int i = 0; i < codeCount; i++) {
            String strRand = str1.substring(i, i + 1);
            g.setColor(getRandColor(1, 255));
            // g.drawString(a,x,y);
            // a为要画出来的东西，x和y表示要画的东西最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处

            g.drawString(strRand, i*fontWidth+3, codeY);
        }


    }

    // 得到随机字符
    private String randomStr(int n) {
        String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }

    // 得到随机颜色
    private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 产生随机字体
     */
    private Font getFont(int size) {
        Random random = new Random();
        Font font[] = new Font[5];
        font[0] = new Font("Ravie", Font.PLAIN, size);
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
        font[2] = new Font("Fixedsys", Font.PLAIN, size);
        font[3] = new Font("Wide Latin", Font.PLAIN, size);
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
        return font[random.nextInt(5)];
    }

    // 扭曲方法
    private void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code.toLowerCase();
    }

    //使用方法
 	/*public void getCode3(HttpServletRequest req, HttpServletResponse 				response,HttpSession session) throws IOException{
        // 设置响应的类型格式为图片格式
            response.setContentType("image/jpeg");
            //禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);


            CreateImageCode vCode = new CreateImageCode(100,30,5,10);
            session.setAttribute("code", vCode.getCode());
            vCode.write(response.getOutputStream());
     }*/

}
```

### **后端解码**

后台控制器：需要对生成的验证码图片进行 Base64 编码后传到前端页面，前端再解析展示图片。

```java
/**
 * 生成验证码
 * @throws IOException
 */
@GetMapping("/getImage")
public Map<String, String> getImage(HttpServletRequest request) throws IOException {
    Map<String, String> result = new HashMap<>();
    CreateImageCode createImageCode = new CreateImageCode();
    // 获取验证码
    String securityCode = createImageCode.getCode();
    // 验证码存入session
    String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    request.getServletContext().setAttribute(key, securityCode);
    // 生成图片
    BufferedImage image = createImageCode.getBuffImg();
    //进行base64编码
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ImageIO.write(image, "png", bos);
    String string = Base64Utils.encodeToString(bos.toByteArray());
    result.put("key", key);
    result.put("image", string);
    return result;
}

```

### **前端展示：**



```html
    <!--前后端分离的架构, 动态访问验证码-->
<img :src="src" id="img-vcode" @click="getImage" :key="key">
<label>
    <div class="label-text">验证码：</div>
    <input type="text" v-model="code" name="vcode" style="width: 100px">
</label>
```



```javascript
<script>
    const app = new Vue({
        el: "#app",
        data: {
            code: "",
            src: "",
            key: "",
        },
        methods: {
            getImage() {
                _this = this;
                axios.get("http://localhost:8989/user/getImage").then((res) => {
                    console.log(res.data);
                    _this.src = "data:image/png;base64," + res.data.image;
                    _this.key = res.data.key;
                })
            }
        },
        created() {
            this.getImage(); // 获取验证码
        }
    });
</script>
```

## **分页逻辑：**

### **后端实现：**

#### **mysql的分页逻辑（物理分页）**

```
mysql 的 LIMIT 分页语句：
LIMIT n: 取前 n 个数据，相当于 LIMIT 0, n；
LIMIT 2, 4: 从第 ==3== 行开始检索 4 条数据；
```



#### **SQL查询语句：参数1 开始查询的行数  参数2 查询数据的条数**



```mysql
<!--根据省份Id 进行分页查询-->
<select id="findByProvinceIdPage" resultType="Place">
    SELECT id, name, picpath, hottime, hotticket, dimticket, placedes, provinceid
    FROM t_place
    WHERE provinceid = #{provinceId}
    ORDER BY id
    LIMIT #{start}, #{rows}
</select>
```

**后端业务层分页逻辑：**

 传入的参数是当前所在页数，以及页面显示数量，无法直接应用 MySQL 的 limit 查询子句中，需要转换一下：start = (page - 1) * rows 计算出 limit 字句的第一个参数。

```java
@Override
public List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId) {
    Integer start = (page - 1) * rows; // 计算要查询的数据是从第几条数据开始的
    return placeDAO.findByProvinceIdPage(start, rows, provinceId);
}
```

**后端控制器代码实现：**



```java
/**
 * 根据省份id查询景点的方法
 */
@GetMapping("/findAllPlace")
public Map<String, Object> findAllPlace(Integer page, Integer rows, String provinceId) {
    HashMap<String, Object> map = new HashMap<>();
    page = page == null ? 1 : page;
    rows = rows == null ? 4 : rows;
    // 景点集合
    List<Place> places = placeService.findByProvinceIdPage(page, rows, provinceId);

    // 处理分页
    Integer counts = placeService.findByProvinceIdCounts(provinceId); // 总页数
    Integer totalPage = counts % rows == 0 ? counts / rows : counts / rows + 1;

    map.put("places", places);
    map.put("page", page);
    map.put("counts", counts);
    map.put("totalPage", totalPage);

    return map;
}
```

### **前端实现：**

#### **简单的：**

```html
 <div id="pages">
    <!--上一页, 只有当前所在页数>1才会显示-->
    <a href="javascript:;" class="page" v-if="page > 1" @click="findAll(page - 1)"><上一页</a>
    <!--页面-->
    <a href="javascript:;" class="page" v-for="indexpage in totalPage" @click="findAll(indexpage)"
       v-text="indexpage"></a>
    <!--下一页, 只有当前所在页数<总页数才会显示-->
    <a href="javascript:;" class="page" v-if="page < totalPage" @click="findAll(page + 1)">下一页></a>
</div>
```

#### **优化：**

超链接的写法可以更优化一下：优化后点击当前所在页数无效（不会发送任何请求）。



```html
<div id="pages">
    <a href="javascript:;" class="page" v-if="page > 1" @click="findAllPage(page - 1)"><上一页</a>
    <span v-for="index in totalPage">
        <a href="javascript:;" class="page" v-if="page == index" v-text="index"></a>
        <a href="javascript:;" class="page" v-if="page != index" @click="findAllPage(index)" v-text="index"></a>
    </span>
    <a href="javascript:;" class="page" v-if="page < totalPage" @click="findAllPage(page + 1)">下一页></a>
</div>
```





```javascript
<script>
    const app = new Vue({
        el: "#app",
        data: {
            provinces : [],
            page: 1,
            rows: 4,
            totalPage: 0,
            totals: 0,
        },
        methods: {
            findAll(indexpage) { // 查询某一页的数据
                if (indexpage) {
                    this.page = indexpage;
                }
                _this = this; // 保存当前对象, 用于下面的作用域
                axios.get("http://localhost:8989/province/findByPage?page=" + this.page + "&rows=" + this.rows).then((res) => {
                    _this.provinces = res.data.provinces;
                    _this.page = res.data.page;
                    _this.totalPage = res.data.totalPage;
                    _this.totals = res.data.totals;
                });
            }
        },
        created() {
            this.findAll();
        }
    });
</script>
```

## **文件上传实现**

### **后端实现：**

注：由于我们往数据库中插入的是文件的 Base64 编码，因此需要将 数据库中 picpath 字段的大小设置的足够大，可以使用以下几个数据类型：

![1687610124564](C:\Users\29463\AppData\Roaming\Typora\typora-user-images\1687610124564.png)

在配置文件 application.properties 中配置文件上传的路径：

```java
# 文件上传地址
upload.dir=E:/gitcode/travels-master/images
spring.resources.static-locations=file:${upload.dir}

```

#### **控制层逻辑实现：**

在后台控制器中 **注入路径**，并实现文件上传（用 Base64 编码进行处理）：

```java
@Value("${upload.dir}")
private String realPath;

/**
 * 保存景点信息
 *
 * @param pic
 * @return
 */
@PostMapping("/save")
public Result save(MultipartFile pic, Place place) throws IOException {
    Result result = new Result();
    try {
        // 文件上传
        String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
        System.out.println(extension);
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "." + extension;
        System.out.println(newFileName);
        // base64编码处理(注意, 这一步必须放在 transferTo 操作前面!)
        place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
        // 文件上传
        File file = new File(realPath);
        pic.transferTo(new File(file, newFileName));
        // 保存place对象
        placeService.save(place);
        result.setMsg("保存景点信息成功!!!");
    } catch (Exception e) {
        e.printStackTrace();
        result.setState(false).setMsg(e.getMessage());
    }
    return result;
}
```

### **前端实现：**

#### **前端中上传文件：**

给标签添加属性 ref="myFile"

```html
<label>
    <div class="label-text">印象图片：</div>
    <div style="text-align: center;padding-left: 36%">
        <div id="upload-tip">+</div>
        <img src="" alt="" id="img-show" style="display: none">
        <input type="file" id="imgfile" ref="myFile" style="display: none" onchange="imgfileChange()">
    </div>
</label>
```



```javascript
<script>
    const app = new Vue({
        el: "#app",
        data: {
            provinces: [],
            place: {},
            id: "",
            time: ""
        },
        methods: {
            savePlaceInfo() { // 保存景点的方法
                console.log(this.place); // 获取到了place对象
                let myFile = this.$refs.myFile;
                let files = myFile.files;
                let file = files[0];
                let formData = new FormData();
                formData.append("pic", file);
                formData.append("name", this.place.name);
                formData.append("hottime", this.place.hottime);
                formData.append("hotticket", this.place.hotticket);
                formData.append("dimticket", this.place.dimticket);
                formData.append("placedes", this.place.placedes);
                formData.append("provinceid", this.place.provinceid);
                //axios
                axios({
                    method: 'post',
                    url: 'http://localhost:8989/place/save',
                    data: formData,
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }).then((res) => {
                    console.log(res.data);
                    if (res.data.state) {
                        alert(res.data.msg + ",点击确定回到景点列表");
                        location.href = "./viewspotlist.html?id=" + this.place.provinceid;
                    } else {
                        alert(res.data.msg + ",点击确定回到景点列表");
                    }
                });
            },
            findAllProvinces() {
                _this = this;
                axios.get("http://localhost:8989/province/findByPage?rows=35").then((res) => {
                    console.log(res.data.provinces);
                    _this.provinces = res.data.provinces;
                });
            }
        },
        created() {
            //查询所有省份信息
            this.findAllProvinces();
            //获取省份id
            this.id = location.href.substring(location.href.indexOf("=") + 1);
            console.log(this.id);

            let now = new Date();
            this.time = `${now.getFullYear()}-${now.getMonth() + 1}-${now.getDate()}`;
        }
    });
</script>
```



#### **前端中展示 base64 格式的文件：**

```html
<img :src="'data:image/png;base64,' + place.picpath" class="viewspotimg">
```



```javascript
<script>
        function imgfileChange() {
            var upload_tip = document.getElementById("upload-tip");
            var img_show = document.getElementById("img-show");
            var imgfile = document.getElementById("imgfile");
            var freader = new FileReader();
            freader.readAsDataURL(imgfile.files[0]);
            freader.onload = function (e) {
                img_show.src = e.target.result;
                img_show.style.display = "inline";
                upload_tip.style.display = "none";
            };
        }
</script>
```

# **小知识点：**

## **前端知识：**

### **html复习：**

#### \&emsp;

参考文档：[(30条消息) HTML中的& nbsp; & ensp; & emsp;等6种空格标记_沄莲0621的博客-CSDN博客](https://blog.csdn.net/u014781844/article/details/84859693)

它叫“全角空格”，全称是Em Space，em是字体排印学的计量单位，相当于当前指定的点数。例如，1 em在16px的字体中就是16px。此空格也传承空格家族一贯的特性：透明的，此空格也有个相当稳健的特性，就是其 **占据的宽度正好是1个中文宽度**，而且基本上不受字体影响。

```
&emsp;
```

### **css复习：**

#### **box-shadow**

CSS **box-shadow** 属性用于在元素的框架上添加阴影效果。你可以在同一个元素上设置多个阴影效果，并用逗号将他们分隔开。该属性可设置的值包括阴影的 X 轴偏移量、Y 轴偏移量、模糊半径、扩散半径和颜色。

```css
box-shadow: 5px 5px 6px 6px #ccc,-5px 5px 6px 6px #ccc;
```

[box-shadow - CSS：层叠样式表 | MDN (mozilla.org)](https://developer.mozilla.org/zh-CN/docs/Web/CSS/box-shadow)

#### **linear-gradient()**

CSS **linear-gradient()** 函数用于创建一个表示两种或多种颜色线性渐变的图片。其结果属于数据类型，是一种特别的![img]()数据类型。

```css
background: linear-gradient(#e66465, #9198e5);
```

[linear-gradient() - CSS：层叠样式表 | MDN (mozilla.org)](https://developer.mozilla.org/zh-CN/docs/Web/CSS/gradient/linear-gradient#示例)



#### **:focus**

**:focus** CSS 伪类表示获得焦点的元素（如表单输入）。当用户点击或轻触一个元素或使用键盘的 

Tab 键选择它时，它会被触发。

```css
input:focus {   
    background: #fff;   
}
```





### **href="javascript:;" 含义**

代码中经常遇到这种写法：

```html
<a href="javascript:;" @click="deleteProvince(province.id)">删除省份</a>
```

其中的 href="javascript:;" 是什么意思呢？

- javascript: 表示在触发默认动作时，执行一段 JavaScript 代码；
- javascript:; 表示什么都不执行，这样点击时就没有任何反应，相当于去掉 a 标签的默认行为。

### **select - option 绑定 Vue 实例**

select 中 通过 v-model 绑定当前的选项，option 中使用 v-for 遍历显示所有选项。

```html
   <label>
        <div class="label-text">所属省份：</div>
        <select v-model="place.provinceid">
            <option v-for="pro in provinces" :value="pro.id" v-text="pro.name"></option>
        </select>
   </label>
```

**删除时增加确认选项**

```javascript
deleteProvince(id) {
    if (confirm("确定要删除省份信息吗?")) {
        axios.get("http://localhost:8989/province/delete?id=" + id).then((res) => {
            if (res.data.state) {
                alert(res.data.msg + "点击确定跳转到省份列表页面!");
                location.reload(true); // 刷新当前页面
            } else {
                alert(res.data.msg);
            }
        });
    }
}
```

**Vue 获取地址栏跳转的参数**

对于这么一个 a 标签，我们要在另一个页面获取这个 url 的参数 id：

```html
<a :href="'./updateprovince.html?id=' + province.id">修改省份</a>
```

可以通过 location.href 获取 url 再进行截取：

```javascript
this.id = location.href.substring(location.href.indexOf("=") + 1);
```

## **工具：**

### **commons-fileupload -文件上传：**

#### **简介：**

是一个Java库，用于处理文件上传的操作。它提供了简单且易于使用的API，可以方便地将文件上传到服务器上。



相关文档：[commons-fileupload的详细介绍与使用 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/244856852)

官网：[FileUpload – Using FileUpload (apache.org)](https://commons.apache.org/proper/commons-fileupload/using.html)

#### **导入：**

```
<!--文件上传工具-->
<dependency>
   <groupId>commons-fileupload</groupId>
   <artifactId>commons-fileupload</artifactId>
   <version>1.4</version>
</dependency>
```

## **注解：**

### **@Accessors(chain=true)   链式**

#### **介绍：**

lombok提供   能让我们方便使用链式方法创建实体对象。

#### **例子🌰：**

```java
package com.xx.travels.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 29463
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) // 链式调用
public class Result {
    private Boolean state = true;
    private String msg;
    private String userId;
}

```

### **@JsonFormat   日期格式化**

#### **介绍：**

@JsonFormat注解，是在Jackson中定义的一个注解，是一个时间格式化注解。此注解用于属性上，作用是把DATE类型的数据转化成为我们想要的格式。

注意：@JsonFormat注解不是SPRING自带的注解，而是Jackson的注解，所以使用该注解前需要添加jackson相关的依赖包。当然如果是 SpringBoot项目就不需要自己手动添加依赖了，因为在spring-boot-start-web下已经包含了jackson的相关依赖了。

#### **例子🌰：**

```java
package com.xx.travels.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Place {
    private String id;
    private String name;
    private String picpath;
    
     //解决方法：加上timezone = "GMT+8
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")  时间差问题
    
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date hottime;
 
    private Double hotticket;
    private Double dimticket;
    private String placedes;
    private String provinceid;
}


```

