# [bootexample](https://www.devmaster.cloud/) &middot; 

bootexample是一个springboot的代码框架.

* **集成了很多基础能力:** 在这个代码框架基础上进行开发，不必花费大量时间精力构建几乎每个工程都需要面对的问题，比如分页查询、跨域访问、业务层事务配置、定时任务、接口访问安全、数据库连接池、redis访问等等，将时间和精力集中于业务逻辑的实现.
* **编程规范约束:** 基于统一的代码框架，对编码行为进行约束，确保项目的不同成员不会因为编码习惯或水平差异导致写出来的代码风格迥异，造成后期维护成本的极大增加.
* **基于代码框架的代码自动生成:** 有了代码框架的约定后，使得代码自动生成变得简单，bootexample自动集成了一个代码生成工具，开发者只需要专注业务逻辑，设计数据库表，然后使用图形化或api接口自动一键生成增删改查代码和接口.
* **基于代码框架的接口文档自动生成:** 进一步，可以通过sdk开发包自动生成漂亮美观的接口文档提供给前端或移动端开发者，大幅提升前后端分离的开发模式下的团队协作效率，不用再手工编写繁琐的接口文档，也不用随着代码改动去人工维护接口文档的变化. 
* **配合在线开发工具:** 使用代码框架，配合在线的开发工具（Bug管理、任务管理、接口文档、测试用例、测试计划等）[在线开发工具](http://www.devmaster.cloud/). 同时为团队管理人员提供了自动的量化考核数据，直观、及时展示团队成员的工作质量和绩效。

学习如何使用bootexample.

## 下载bootexample工程

你可以通过git工具下载bootexample工程，然后通过IDE开发工具（Eclipse等）打开maven工程方式进行打开:

* 在工程的根目录下有个/doc/database.sql，这个文件里有个建立用户表的sql语句，打开根据自己的需要进行修改然后执行语句，如果你本地安装了mysql数据库可以在本地执行，或者到安装了mysql的其他服务器上执行.
* 在Eclipse中启动bootexample服务，通过选中BootexampleApplication.java右键选中run as java application，默认配置application.yml中启动在8081端口.
* 通过curl或postman等工具，进行接口访问测试，比如：http://localhost:8081/api/user/list.

如果接口响应提示没有授权token，可以在UserController类上增加 @CheckToken(false) 将接口安全token检查关掉后重新启动工程.

## 自动生成代码

你可以使用工程zoo目录下的SpringUI或SpringCmd类自动生成代码，他们都是实现了main函数入口的类，可以直接在类上点右键运行，他们负责根据数据库表生成对应的代码，包括：Po类、mapper类、mapper.xml、service类和controller类。
如果使用SpringUI进行代码自动生成:

* SpringUI.java点右键，run as java application
* 在弹出的UI界面上，根据提示输入数据库连接相关信息，输入要生成代码的数据库表名，并指定生成代码的路径以及包的名字，如果你打算在bootexample上生成代码进行测试，包名填写：com.buxiu.bootexample，路径填写：xx/bootexample/src/main/java(工程源代码存放的路径)

**下面是自动生成代码效果图**
 ![自动生成代码效果图](http://sagedata.cn/img/ps-3-1.6dec80df.jpg)
## 接口文档自动生成

可以通过 [devmaster](http://www.devmaster.cloud/)创建项目，并获得项目的sdk访问key，然后在bootexample里按照例子调用接口文档自动生成的sdk，自动生成的文档会放到[devmaster](http://www.devmaster.cloud/)上，提供在线的访问服务，也可以对自动生成的接口文档进行手动编辑:

```java
import java.util.List;

import com.buxiu.hellodev.zoo.apidoc.Jdk8Apidoc;
 
/**
*
*  Apidoc 接口文档自动生成工具 命令行版
*
*  @Author bubuxiu@gmail.com
*
**/
public class ApidocCmd {
     
    public static void main(String[] args)  {
        String packagename = "com.buxiu.bootexample";
        String sourcedir = "/Users/bubuxiu/git/bootexample/src/main/java";
        
        // 生成特定的controller接口文档， null表示生成所有接口
        String specialname = "UserController";
        
        int r = Jdk8Apidoc.init(packagename, sourcedir, "bugonlineappkey"); 
        if(r != 0) { 
            return ;
        }
        
        List<String> controllerlist = Jdk8Apidoc.getContollerList();
        
        for(String controller:controllerlist) {
            if(specialname != null) {
                if(!specialname.equals(controller)) {
                    continue;
                }
            } 
            // 生成的文档默认上传到阿里云服务器，可以通过访问www.bugonline.cn进行在线访问和修改
            Jdk8Apidoc.addOneApi(controller);
        }
    }
}
```
**下面是自动生成的接口文档格式和效果图**
 ![接口文档效果图](http://sagedata.cn/img/ps-4-1.2df8caef.jpg)
上面的代码例子，显示如何创建UserController类的接口文档，其中bugonlineappkey是通过上述步骤在[devmaster](http://www.devmaster.cloud/)创建项目申请获得.



### License

bootexmaple is [MIT licensed](./LICENSE).
