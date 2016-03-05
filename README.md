# SspuRegister

学生考勤系统开发（Android版）
 摘 要
 随着宽带无线接入技术和移动终端技术的飞速发展，人们迫切希望能够随时随地乃至在移动过程中都能方便地从互联网获取信息和服务，移动互联网应运而生并迅猛发展。Android是一种以Linux内核为基础的开放源代码的移动设备操作系统，仅推出两年其市场占有率就超越了称霸十年的Symbian系统。2013年5月，Android在中国的占有率有71.5%，超过其主要竞争对手苹果公司约50%。世界占有率亦有近70%。安卓系统以其开放的平台，众多、免费的应用，华丽、新鲜的运行界面，简单、亲切的用户体验而备受消费者的喜爱。
 本课题主要完成的任务就是针对实际情况，结合相关开发实例，提出一种基于IC卡的学生考勤管理系统APP，使教师及时高效，快速的了解每一个学生的出勤情况，可以大大降低考勤工作量和考勤时间，并可以及时了解学生各个时段的考勤状况。本课题中主要采用C/S模式，客户端使用安卓平台Java-ADT和嵌入式SQLite数据库开发，利用校园卡进行考勤管理。服务器端使用MS-Visual C#对数据库中的数据添加删除、报表统计、导入导出。 该课题使用了射频识别卡。射频识别卡取代传统的接触式IC卡，成为智能卡领域的新潮流。RFID卡由于成功地结合射频识别技术和IC卡技术，解决了无源(卡内无电池)和免接触的难题，因此，具有磁卡和接触式IC卡不可比拟的优点。RFID卡由IC芯片、感应天线组成，完全密封在一个标准PVC卡片中，无外露部分。校园卡就是其中的一种校园管理系统。在学生考勤系统的设计中，利用无线射频识别技术(RFID)，可实现对学生进行考勤、记录等功能。 关键词：Android；MS-Visual C#；SQLite数据库；C/S；射频技术；APP；


Attendance System (android)
 Abstract
 With the rapid development of broadband wireless access technologies and mobile terminal technology, people are eager to access to information and services on the Internet wherever and easily, the mobile internet come into being and develop fast． Android is a Linux-kernel-based open source operating system for mobile devices, only launched two years beyond its market share on the Symbian system to dominate the decade． May 2013, Android's market share in China has 71．5%, over its main rival Apple about 50%． There is about 70% of the World Market, android system ingratiate much consumers with its open platform, numerous free applications, gorgeous, fresh running interface, simple and friendly user． Server uses MS-Visual C # to add and delete data, reports, statistics, import and export in the database.
 The main task is the subject of the actual situation, with related development examples.Raise student attendance management system based on IC Card.Make teachers efficiently and quickly understand each student's attendance, and reduce the workload and attendance time and attendance, keep abreast of the status of student attendance on each period． This paper mainly uses C / S mode, the client uses the Android platform Java-ADT and embedded SQLite database to develop．
 The use of radio frequency identification cards topics. RFID CARDS instead of traditional non-contact IC card. It’s smart new trend. RFID CARDS due to successfully combined RFID technology and IC card technology, solve the passive (card) and contactless without batteries, Therefore, IC card and non-contact incomparable advantages. RFID CARDS by IC chips, induction antenna, completely sealed in a standard PVC CARDS, no exposed portion.Campus card is one kind of school management system. In the design of student checking attendance system, the use of radio frequency identification (RFID), can achieve for students in attendance, recording etc.Function. Key words: Android; MS-Visual C#; SQLite Databases; C/S; RF technology; APP;


第1章 综 述
 本课题中完成的主要工作，是开发安卓校园卡考勤APP，该APP可实现校园内课程考勤的基本功能：例如学生信息录入、学生信息修改、校园卡考勤、查看考勤记录、管理考勤记录等功能。最终实现灵活的课程考勤、信息管理、信息通信等功能，使课程考勤自动化。 1.1课题背景
 风起云涌的高科技时代，智能终端的普及不仅推动了移动互联网的发展，也带来了移动APP应用的爆炸式增长。根据IDC的预测，以2011年全球范围内的382亿下载量计算，到2015年APP下载量会上升到1827亿。
 凭借便携、触屏、高清的丰富体验，以iphone和android为代表的手机移动设备正悄然改变着企业的商务运行。这使得原本定义为消费设备的产品逐渐也应用于商务领域，从而引发了企业级应用厂商把研发重点转移至移动应用平台，将APP作为其提供推广品牌、接触消费者，甚至销售内容的渠道。APP的开发与推广成为了移动互联网行业的一个巨大的市场
 大学校园里学生和老师智能手机几乎人手一部，借助校园里的WLAN网络，大家可以随心所欲的下载自己喜欢的学习工具APP、聊天APP、地图APP等。APP小巧、安装卸载方便。开发一款考勤管理APP能够有效地帮助老师在课堂上点名。省时、省力、点名准确度高。
 1.2 需求分析
 考勤是每个学校工作日中每天都会遇到的情况。在中小学中，学生们的教室每年都是固定由老师安排，相对稳定，座位有些受到学号限制，有些则为老师根据身高、视力等安排，考勤时比较方便；而到了大学中，相对教室学生的流动量大、教室并非固定为某班所用，学生也并非固定前往某教室上课，某课程也并非固定在某教室教授。由此一来，大学生的考勤问题成了每个老师发愁的焦点之一。
 在大学老师考勤的类型中，有的老师为保证上课时间而进行抽查点名，虽然是大大节约了考勤所使用的时间，但是同时却产生了考勤不完整的漏洞的存在；有的老师花费大量时间进行考勤工作，却又在同时耽搁了课时，使得授课内容没有连贯性、授课时间相应减少。因此，开发一套崭新的，既可以满足老师需求进行仔细考勤，又不耽误老师授课时间的可管理、可查询的考勤系统十分必要，并具有广阔的应用前景。
 由于传统统计学生上课信息的手段比较简陋，在统计和汇总过程中，较普遍地存在以下问题：
 ① 统计信息量大，出错率高，统计时间长，汇总困难；
 ② 学校课程繁多，涉及面广。在主要依靠人工查询资料、手工计算的统计方法中，每一部分工作都需要设计人员付出巨大劳动，需要投入较大的精力和占用较长的设计时间；
 ③ 情况复杂时，缺乏有效的分析手段，为了保证可靠性，经常出现重复统计、统计缺失等现象。
 基于RFID技术的学生考勤APP，体积小，使用方便。老师在不耽误任何教学时间的情况下，准确地完成学生的考勤，为教学节省出大量的时间，有着相当的应用空间。此外，此课题的成果可以推广到其他应用场合，比如在公司或企业员工的上下班考勤、数字图书馆系统、停车库管理系统等场合，有着广阔的应用前景。
 从以上问题可以看出，开发一套完善的适合学校现状的、能够解决上述种种问题的课程考勤、查询课程情况的APP具有重要的意义，不仅可以促进课程的顺利完成，还可以使老师的考勤工作有良好的保障，具有广阔的应用前景。
 1.3主要技术
 1.3.1　Android及SDK介绍
 SDK指是软件开发包，被软件开发工程师用于为特定的软件包、软件框架、硬件平台、操作系统等建立应用软件的开发工具的集合。因此，Android SDk指的既是Android专属的软件开发工具包。
 Android是Google公司推出的手机开发平台，和iPhone相似，Android使用了WebKit浏览器引擎，具备触摸屏、高级图形显示和上网功能，在手机上可以实现查看电子邮件、搜索网址和观看视频节目等功能，较iPhone等其他手机更强调搜索功能，界面更强大，可以说是一种融入全部Web应用的单一平台。 Android手机系统最震撼人心之处在于其开放性和服务免费。Android是一个对第三方软件完全开放的平台，开发者在为其开发程序时 拥有更大的自由度，突破了iPhone等只能添加为数不多的固定软件的枷锁，使得应用的安装更加方便；同时与Windows
 Mobile、Symbian等厂商不同，Android操作系统免费向开发人员提供，这样可节省近三成成本。
 1.3.2　Eclipse简介
 Eclipse是一个著名的跨平台的自由集成开发环境软件。最初主要用来Java语言开发，现在人们可以通过安装插件使其作为C++、Python、PHP等其他语言的开发工具。Eclipse的本身只是一个框架平台，但是随着众多插件的产生，使得Eclipse拥有很好的灵活性。许多软件开发商以Eclipse为框架开发自己的IDE。
 虽然大多数用户更加乐于将Eclipse当作Java集成开发环境来使用，但Eclipse的作用已经不仅限于此。Eclipse还是含有插件开发环境，这个组件主允许希望扩展Eclipse的软件开发人员构建与Eclipse环境无缝集成的工具。由于Eclipse中的每样东西都是插件，对于给Eclipse提供插件，以及给用户提供一致和统一的集成开发环境而言，所有工具开发人员都具有同等的发挥场所。
 这种平等和一致性并不仅限于Java开发工具。尽管Eclipse是使用 Java 语言开发的，但它的用途并不限于 Java 语言；例如，支持诸如 C/C++ 和 COBOL 等编程语言的插件已经可用，或预计将会推出。Eclipse 框架还可用来作为与软件开发无关的其他应用程序类型的基础，比如内容管理系统。 本系统即通过Eclipse，添加Android做插件，匹配上AndroidSDK进行软件的开发。
 1.3.3　Visual Studio．NET 2010简介
 ASP．NET的开发环境有多种选择，该系统的开发选择Visual Studio．NET 2010。
 Visual Studio．NET 2010是一套完整的开发工具，用于生成ASP．NET Web应用程序、XML Web
 Services、桌面应用程序和移动应用程序。该环境提供了常用控件，直接拖动的开发方式提高了开发效率。而且可以创建自定义控件，使程序具有更好的扩展性、可维护性以及重用性。此外，Visual Basic．NET、Visual C++．NET、Visual C#．NET和Visual
 J#．NET使用统一的集成开发环境，该环境允许它们共享并创建混合语言解决方案；这些语言都利用．NET Framework的功能，它提供了对简化ASP．NET Web应用程序和XML Web Services开发关键技术的访问。因此，使用Visual Studio．NET 2005作为开发环境进行高效开发乃是最佳选择。
 1.3.4　SQLite简介
 SQLite，是一款轻型的数据库，是遵守ACID的关系型数据库管理系统，它的设计目标是嵌入式的，而且目前已经在很多嵌入式产品中使用了它，它占用资源非常的低，在嵌入式设备中，可能只需要几百K的内存就够了。它能够支持Windows/Linux/Unix等等主流的操作系统，同时能够跟很多程序语言相结合，比如 Tcl、C#、PHP、Java等，还有ODBC接口，同样比起Mysql、PostgreSQL这两款开源世界著名的数据库管理系统来讲，它的处理速度比他们都快。SQLite第一个Alpha版本诞生于2000年5月。 至今已经有13个年头，SQLite也迎来了一个版本 SQLite 3已经发布。
 以下是SQLite的特点：
 (1)零配置 
 配置和管理SQLite就像得到它一样简单,无需专门的dba
 (2)兼容性
 它可以编译运行在Windows、Linux、BSD、Mac OS X及商用的Unix
 系统如Solaris、HPUX和AIX，还可以应用于很多嵌入式平台如QNX、VxWorks、Symbian、Palm OS和Windows CE,android
 (3)紧凑性
 SQLite的设计可以说是功能齐全但体积很小：1个头文件，1个库，不需要扩展的数据库服务
 (4)简单易用
 (5)可靠性


第2章 总体设计及平台搭建
 2.1 总体设计
 2.1.1 设计目标
 根据高校中，学生上课考勤的实际情况来制定一系列的功能用以真实的记录学生上课的实际报道时间，免去上课前繁复的点名，改用校园卡刷卡点名。准确的记录学生实到时间。系统要实现以下特点：
 ① 性能稳定
 可视化校园卡考勤系统能够满足基本的考勤需要，记录考勤时所需要录入的信息。记录考勤时间、刷卡状态。
 ② 功能完善
 准确记录考勤时间、老师可以根据登陆的用户名只考勤属于自己的班级学生、并提供了排重功能，即对已经考勤的班级不在考勤、对重复刷卡的同学做了限制。
 ③ 统计方便
 服务端可以根据每次的刷卡记录统计一学期的刷卡状态，并生成统计报表
 2.1.2 设计流程
 该系统包括数据模型设计、客户端（手机端）设计和服务端（PC端）设计。
 如图2-1所示。

                   图2-1  考勤系统设计流程图


手机端：教师通过手机，选择相应的考勤课程，默认的考勤时间为当天。选择好考勤时间和考勤课程就可以通过OTG连接手机。通过刷卡学生刷卡记录刷卡学生的考勤时间，判断是否迟到。
 客户端：只要对数据库进行插入和管理操作，用户可以对数据库进行插入、删除、修改操作。并可以根据时间字段导出考勤的报表供教师做期终考核。
 2.1.3模块划分
 考勤系统主要设计三个主模块的设计
 ① 数据库模块设计：包括学生表设计、教师表、课程表、选课表、考勤表和用户表的设计；
 ② PC端模块设计：包括课程信息添加、学生信息添加、教师信息添加、选课信息添加。考勤查询、报表导出；
 ③ 手机端模块设计：包括登考勤功能、学生信息录入、事假管理、出勤录、数据库同步等功能
 2.2 平台搭建
 2.2.1 准备工作
 1．JDK(JavaDevelopmentKit)，官网下载最新版本即可。
 2．Eclipse（一个开放源代码的、基于Java的可扩展开发平台）
 3．ADT，安卓开发插件。
 4．AndroidSDK（这只是个下载工具，不是真正的SDK）
 5.下载地址
 (1)JDK:http://www．oracle．com/technetwork/java/javase/downloads/index．html
 (2)Eclipse:http://www．eclipse．org/downloads/
 (3)ADT:http://developer．android．com/sdk/index．html
 (4)AndroidSDK:http://developer．android．com/tools/sdk/tools-notes．html
 2.2.2 安装与配置
 1．安装JDK
 一路点下一步，这个没什么特殊的东西，直接跳过，不过要记得你装哪了，别2秒钟就不记得了。我安装在了D:\ProgramFiles\Java
 2．安装Eclipse
 解压Eclipse到你的硬盘里，我解压在了D:\android\eclipse
 3．安装SDK
 如果你下载的是．zip或者．tgz的包（SDKinstaller安装之后的），将它解压到一个安全的路径中，默认的会被命名为android-sdk-windows，本文安装的位置为D:\android\android-sdk-windows。如果你下载的是windows安装程序(．exe文件)，运行它之后会检测是否安装了JDK（如果没安装就安装一下），然后安装SDKTools到一个安全的位置（你可以编辑的位置）。
 4．设置环境变量
 （1）新建JAVA_HOME
 JAVA_HOME=D:\programs\jdk1.7.0_03（JDK的安装路径）
 （2）新建ADT_HOME
 ADT_HOME=D:\adt-bundle-windows-x86-20131030\sdk
 （3）PATH
 PATH=%ADT_HOME%\platform-tools;%ADT_HOME%\tools;%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;
 配置完后，作下检查，看是否配置成功，开始菜单->运行->cmd，依次输入java、javac、adb回车后不报错就OK！
 5．ADT安装
 打开 Eclipse IDE，进入菜单中的 "Help" -> "Install New Software"
 点击Add．．．按钮，弹出对话框要求输入Name和Location：Name自己随便取，Location输入http://dl-ssl．google．com/android/eclipse。 确定后，在work with后的下拉列表中选择刚才添加的ADT，会看到下面出有Developer Tools，展开会有Android DDMS和Android Development Tool，勾选。
 完成之后：选择Window > Preferences．．．在左边的面板选择Android，然后在右侧点击Browse．．．并选中SDK路径，点击Apply、OK。配置完成。
 6．模拟器新建与启动
 为使Android应用程序可以在模拟器上运行，必须创建AVD。
 （1）在Eclipse中。选择Windows > Android SDK and AVD Manager
 （2）点击左侧面板的Virtual Devices，再右侧点击New
 （3）填入Name，选择Target的API，SD Card大小任意，Skin随便选，Hardware目前保持默认值 
 （4）点击Create AVD即可完成创建AVD。如图2-2、2-3所示


图2-2 android模拟器

图2-3 程序启动后

第3章 数据库设计
 3.1 概念结构设计
 实体-联系图，即E-R图，提供了表示实体型、属性和联系的方法，用来描述现实世界的概念模型，在数据库系统概论中属于概念设计阶段。E-R图的设计与建立，可以帮助开发者更加合理的去制定数据库内容，使得在开发过程中对数据库的各种操作(添加，查询，修改或删除)变得简单易行。
 学生考勤系统的实体联系图的实体主要包括major（课程表），student（学生表），login（登陆表），课程考勤表（动态生成）。考勤和学生之间是一对一的关系；教师与考勤之间是一对多的关系；课程与教师之间是一对多的关系；学生和课程是一对多的关系；选课和考勤是多对多的关系。实体联系图如图3-1所示。


图3-1 ER图 3.2 物理结构设计
 根据以上的实体属性集可以建立相应的数据库表结构，主要用到的数据库表结构如下所示。
 1．课程表(major)
 包括_id、name、teachername、phone、email、address。 ………………（见表3-1）…………………………………… 表3-1 学生表 字段名 描述 类型 是否允许为空 是否主键 _id 序号 integer 否 是 name 课程名称 Text 否 否 teachername 授课老师 Text 否 否 phone 老师电话 Text 否 否 email 老师邮箱 Text 否 否 address 老师办公室 Text 否 否 2．学生表(student) 包括_id、kid、cod、name、myclass、flag。 ………………（见表3-2）…………………………………… 表3-2 教师表 字段名 描述 类型 是否允许为空 是否主键 _id 序号 int 否 是 kid 卡号 Text 否 否 code 学号 Text 否 否 name 姓名 Text 否 否 myclass 班级 Text 否 否 flag 同步标记 int 否 否 3．登陆表(login) 包括_id 、logtime 、 major 、 flag ………………（见表3-3）…………………………………… 表3-3 课程表 字段名 描述 类型 是否允许为空 是否主键 _id 序号 int 否 是 logtime 登陆时间 Text 否 否 major 课程名称 Text 否 否 flag 同步标记 int 否 否
1.课程考勤表(动态生成) 包括_id、kid、code、name、myclass、times、 absence、losecard、 L0723（动态生成时间）。 ………………（见表3-4）…………………………………… 表3-4 选课表 字段名 描述 类型 是否允许为空 是否主键 _id 序号 int 否 否 kid 卡号 Text 否 否 code 学号 Text 否 否 name 姓名 Text 否 否 myclass 班级 Text 否 否 times 总次数 int 否 否 absence 事假 int 否 否 losecard 未带卡 int 否 否 L0723 签到日期 Text 否 否

以上将数据库概念结构设计的E-R图转换成SQLITE与所支持的数据模型相符合的结构，只需使用数据定义语言将数据库逻辑设计和物理设计的结果严格描述出来，再经过调试产生目标模式就可以组织数据入库，进行数据库实施。


第4章 考勤系统客户端设计
 4.1功能概述
 基于校园卡的学生考勤系统主要实现功能：选择课程、考勤功能、学生信息录入、信息同步、课程管理、事假管理、出勤率等。运行开始显示选择课程界面。
 软件启动后，选择考勤课程即开始考勤。系统功能模块图4-1


图4-1 功能模块示意

4.2 系统流程图 如图4-2所示。

图4-2 系统流程示意 4.3 类和布局文件介绍 ………………（见表4-1）…………………………………… 表4-1 类和布局文件 类名 布局 备注 MainActivity.java activity_main.xml 程序启动动画 about.java about.xml 关于软件界面 camera.java camera.xml 拍照模式界面 downloadingActivity.java download.xml 信息同步界面 manageabsent.java absent.xml 事假管理界面 managecourse.java course.xml 课程管理界面 managedownload.java same.xml 学生信息下载 manageontime.java ontime.xml 出勤率界面 photo.java photo.xml 图像 Register.java registermain.xml 主界面 setabs.java setabs.xml 事假设置 setregister.java setregister.xml 选择课程主界面 showdetail.java showdetail.xml 出勤明细 ToDoDB.java 无 数据库操作类 uploadingActivity.java uploading.xml 信息上传 uploadmajor.java uploadstudent.xml 显示正在上传 uploadstudent.java uploadstudent.xml 显示正在上传 Exit.java exit_dialog.xml 退出软件提示框

4.4 功能模块介绍 1．软件启动动画 （1）启动软件动画。如图4-3所示。

图4-3 系统运行开始示意

2．主界面
 包括签到、信息录入、信息同步、管理导航按钮和一个签到课程选择。如图4-5所示。

图4-5 主界面
 选课：点击选课按钮后，会在课程后下拉框中加载已有课程，选择签到的课程时，会在主界面显示该课程的信息，如：该课程共多少人，已到多少人，任课老师，老师电话，老师邮箱以及老师的办公地点 
 开始：点击开始按钮时，会先判断是何种模式，共四种模式：1.无语音无摄像 2.无语音有摄像 3.有语音无摄像
 4.有语音有摄像。根据不同模式，加载不同的签到界面，有摄像的一种界面，无摄像的另一种界面 
 3.简单模式
 主要有学生图像、姓名、班级、学号、签到标记。如图4-6所示。


图4-6 无摄像考勤界面
 4.拍照模式
 提供刷卡界面并显示图片预览界面，拍照后显示学生图像、姓名、班级、学号、签到标记。如图4-7所示。


图4-7 摄像考勤界面
 5.学生信息录入
 学生信息录入前提是在主界面选择好签到课程。进入该界面时，指针聚焦卡号后文本编辑框，读卡器读入卡后，自动查询student表，如果有该生信息，复制该生信息到所选课程，并显示添加成功等提示。如果student表中无此学生信息，提示输入学号后按查找按钮试试，如果查找到该生信息，复制信息到所选课程并提示添加成功，如果未查找到该生信息，就需要手动输入学生信息后按添加按钮。如图4-8所示。


图4-8 学生信息录入


6.信息同步
 信息同步主要有信息上传和信息下载。信息上传包括student表信息与课程签到信息，添加的学生信息上传到服务器student表中，利于数据维护，只需一次录入，后续即可实现快速添加。信息下载此处只下载已选课程的所有学生信息 。如图4-9所示。


图4-9 学生信息录入
 7.信息上传
 信息上传包括学生信息和课程信息，点击立即同步，先检测网络设置，如果未打开网络，提示未打开网络，跳转网络设置界面。已设置好后，立即同步数据，可后台同步。课程信息同步按照签到日期同步。如图4-10所示。


图4-10 信息上传


7.管理
 管理界面包括课程管理、事假管理、出勤率、数据库同步、摄像头设置、语音设置以及设置的当前状态，设置的状态保存在本地。下次运行软件时保持上次设置的状态。如图4-11所示。


图4-11 管理界面


8.课程管理
 课程管理界面主要包括课程信息的显示、添加、修改以及删除。点击显示课程后加载课程名称，选择课程即可显示该课程的信息，下方添加按钮变成删除按钮。如图4-12 所示。


图4-12 课程管理
 9.事假管理
 事假管理界面只加载签到当天未签到的同学，点击该同学，弹出提示框，设置为事假还是未带卡。如图4-13 所示。


图4-13 事假管理
 10.出勤率
 出勤率界面显示已选课程的签到情况，点击任一学生，跳转到该学生的详细签到信息
 如图4-14 所示。


图4-14 学生信息录入


11.出勤明细
 出勤明细显示学生是详细签到信息，包括签到的次数、出勤次数、事假次数、缺卡次数、缺勤次数、出勤率以及出勤照片明细。点击出勤照片明细跳转到照片浏览界面。如图4-15 所示。


图4-15 学生信息录入


第五章 总结
 本次嵌入式系统程序设计课程实习设计让我学到了程序的开发模式，开发框架、界面设计等。让我对数据库和android有了进一步的学习。
 大学里经过一个学年的安卓学习。在这几个月的开发过程中，我首先自学了一些安卓的基础知识，然后模仿了网上的安卓程序编写边思考。让我对学习一门新的开发语言有了一个新的认识。那就是要多联系。对安卓有一些基本了解之后我就开始着手开发自己的考勤系统APP了。
 在开发过程中，我学到了设计一款软件的基本思路：需求分析、概要设计，画流程图、界面设计、编码和测试。这样初步做成一款可以解决实际问题的软件。再此我对我学习到的一些知识做下总结。
 （1） android的环境搭建和基本语法知识
 （2） SQlite数据库的设计和实现
 （3） SqlServer数据库同步
 （4） 界面设计
 由于时间有限，系统还不是很完善，许多地方还需要进一步地修改。恳请老师能够多提宝贵意见。


致 谢
 本次嵌入式系统程序设计课程实习设计即将结束了，在短短几个月中，我遇到了很多问题，查找了很多资料，得到了很多人的帮助。在此我对他们表示衷心地感谢。 首先，我要感谢我的导师。是他在课题思路设计上给了我宝贵的建议。并在我遇到问题时能够耐心、细致地为我解答。从他那里，不仅让我学到了很多专业的知识。更让我学到了解决问题的方法思路。感谢他为我的设计文档写作做了很多指导，使我的设计文档充实、完善。在此，我要向我的导师表示深深地谢意。 同时，我还要感谢我大学里所有教过我的老师们，是你们的悉心教导和严谨扎实的工作态度，让我们学到了很多本领。你们的谆谆教导我会永远铭记！ 通过此次嵌入式系统程序设计课程实习设计，让我知道了什么是学以致用，灵活变通。我学习了如何去分析问题、解决问题的能力。进一步提高了我的实际问题的解决能力。 大学生活即将接近尾声，感谢学校给我提供了一个这么优质的学习环境和学习资源。让我认识了很多优秀的老师和同学。从他们那里，我学到了很多做人、做事的很多道理。 最后再次感谢一路走来的同学和老师，和你们相遇我感到十分荣幸！


参考文献：
 [1] 戚伟,李蓉.在ASP环境下实现SQL数据库模糊查询[J].科技信息,2008,24
 [2] 高常波.C＃程序设计快速入门[J]中文信息：程序春秋,2002,6
 [3] 欧阳峥峥,林茂.基于TCP／IP协议通信软件的分析与实现[J].武汉工业学院学报,2005,2
 [4] 王恩普,林宏.Windows NT下通过串口实现TCP／IP协议通信[J].微机发展,2003, 2
 [5] 郭曙光.基于C#.Net的门禁系统的设计开发[J].闽西职业技术学院学报,2008,3
 [6] 俞志龙，陈昱勋，郑名杰. Google Android SDK开发范例大全.人民邮电出版社,2009
 [7] 杨丰盛 Android应用开发揭秘. 机械工业出版社.2010.1 
 [8] Powell. R. (美)Weeks. R.著. 袁鹏飞译. C#和.NET架构. 北京：人民邮电出版社，2002.4 
 [9] 萨师煊，王珊.数据库系统概论（第三版）.北京：高等教育出版社，2000.2
 [10] Jason Price著，邱仲潘，等译.C#数据库编程从入门到精通.北京：电子工业出版社，2003.9
 [11] 罗斌，等编著. Visual C#.NET精彩编程实例集锦. 北京：中国水力水电出版社，2005.
 [12] 张公忠主编. 现代网络技术教程.第2版. 北京：电子工业出版社，2004.1
 [13] 王昊亮，李刚，等编著.Visual C#程序设计教程.北京：清华大学出版社,2003.9


附 录
 附录A:主要源程序代码
 基于校园卡的学生考勤系统的源程序
 //数据库操作代码----------- package com.example.sspuregister;

import android.content.ContentValues; import android.content.Context; import android.database.Cursor; import android.database.sqlite.SQLiteDatabase; import android.database.sqlite.SQLiteOpenHelper;

public class ToDoDB extends SQLiteOpenHelper {
private final static String DATABASE_NAME = "sspuregister_db";
private final static int DATABASE_VERSION = 1;
private final static String TABLE_NAME = "student_table";
public final static String FIELD_id = "_id";
public final static String FIELD_TEXT = "kid";


public ToDoDB(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

public void onCreate(SQLiteDatabase db) {

    String sqlmajor = "create table major (_id integer primary key autoincrement, "
            + "name text, teachername text, phone text, email text, address text);";

    String sqllogin = "create table login (_id integer primary key autoincrement, "
            + "logtime text, major text, flag int);";

    String sqlstu = "create table student (_id integer primary key autoincrement, "
            + "kid text, code text, name text, myclass text,flag int default 0);";

    String sqlmode = "create table mode (_id integer primary key autoincrement, "
            + "name text,stat text);";
    db.execSQL(sqlmajor);
    db.execSQL(sqllogin);
    db.execSQL(sqlstu);
    db.execSQL(sqlmode);

    ContentValues cv = new ContentValues();
    cv.put("name", "phone");
    cv.put("stat", "0");
    db.insert("mode", null, cv);

    cv.put("name", "sound");
    cv.put("stat", "0");
    db.insert("mode", null, cv);

}

public void alterTable(String name,String col) {
    SQLiteDatabase db = this.getReadableDatabase();
    String sql = "ALTER TABLE "+name+" ADD COLUMN "+col+" STRING default '0'";
    db.execSQL(sql);
}

public void createTable(String name) {
    SQLiteDatabase db = this.getReadableDatabase();
    String sql = "create table "+name+" (_id integer primary key autoincrement, "
            + "kid text, code text, name text, myclass text,times int default 1,absence int default 0,losecard int default 0);";
    db.execSQL(sql);
}

public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
    db.execSQL(sql);
    onCreate(db);
}

public Cursor select(String table) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db
            .query(table, null, null, null, null, null, null);
    return cursor;
}

public Cursor selectstudent(String table,String kid) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db
            .query(table, null, "kid=?", new String[] { String.valueOf(kid) }, null, null, null);
    return cursor;
}

public Cursor selectlogtime() {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db
            .query("login", null, "flag=?", new String[] { String.valueOf("0") }, null, null, null);
    return cursor;
}

public Cursor selectlogin(String major) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db
            .query("login", null, "major=?", new String[] { String.valueOf(major) } , null, null, null);
    return cursor;
}


public Cursor selectinitstu(String table,String col) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db
            .query(table, null, col+"=?", new String[] { String.valueOf("1") }, null, null, null);
    return cursor;
}

public Cursor selectabststu(String table,String col) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db
            .query(table, null, col+"=?", new String[] { String.valueOf("0") }, null, null, null);
    return cursor;
}

public Cursor selectuploadstu() {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db
            .query("student", null, "flag=?", new String[] { String.valueOf("0") }, null, null, null);
    return cursor;
}

public Cursor selectadyoff(String table,String col,String kid) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db
            .query(table, null, col+"=?and kid=?", new String[] { String.valueOf("事假"),String.valueOf(kid) }, null, null, null);
    return cursor;
}

public long insertstudent(String table,String kid,String code,String name,String myclass) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put("kid", kid);
    cv.put("code", code);
    cv.put("name", name);
    cv.put("myclass", myclass);
    long row = db.insert(table, null, cv);
    return row;
}

public long insertlogin(String logtime,String major) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put("logtime", logtime);
    cv.put("major", major);
    cv.put("flag", 0);
    long row = db.insert("login", null, cv);
    return row;
}

public Cursor checklogin(String logtime,String name) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db
            .query("login", null, "logtime=?and major=?", new String[] { String.valueOf(logtime),String.valueOf(name) }, null, null, null);
    return cursor;
}


public Cursor selectcourse() {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db
            .query("major", null, null, null, null, null, null);
    return cursor;
}

public Cursor checkcourse(String name) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db
            .query("major", null, "name=?", new String[] { String.valueOf(name) }, null, null, null);
    return cursor;
}


public long insertmajor(String name,String teachername,String phone,String email,String address) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put("name", name);
    cv.put("teachername", teachername);
    cv.put("phone", phone);
    cv.put("email", email);
    cv.put("address", address);
    long row = db.insert("major", null, cv);
    return row;
}


public void delete(String name) {
    SQLiteDatabase db = this.getWritableDatabase();
    String where =  "name = ?";
    String[] whereValue = { name };
    db.delete("major", where, whereValue);
}

public void deletelog(String name) {
    SQLiteDatabase db = this.getWritableDatabase();
    String where =  "major = ?";
    String[] whereValue = { name };
    db.delete("login", where, whereValue);
}

public void update(String table,String col, String kid) {
    SQLiteDatabase db = this.getWritableDatabase();
    String where = "kid= ?";
    String[] whereValue = { kid };
    ContentValues cv = new ContentValues();
    cv.put(col, "1");
    db.update(table, cv, where, whereValue);
}

public void updateuploadstu(String kid) {
    SQLiteDatabase db = this.getWritableDatabase();
    String where = "kid= ?";
    String[] whereValue = { kid };
    ContentValues cv = new ContentValues();
    cv.put("flag", "1");
    db.update("student", cv, where, whereValue);
}

public void updateabs(String table,String col,String absence, String kid) {
    SQLiteDatabase db = this.getWritableDatabase();
    String where = "kid= ?";
    String[] whereValue = { kid };
    ContentValues cv = new ContentValues();
    cv.put("absence", absence);
    cv.put(col, "事假");
    db.update(table, cv, where, whereValue);
}

public void updatetimes(String table,String times, String kid) {
    SQLiteDatabase db = this.getWritableDatabase();
    String where = "kid= ?";
    String[] whereValue = { kid };
    ContentValues cv = new ContentValues();
    cv.put("times", times);
    db.update(table, cv, where, whereValue);
}

public void updatetimesabs(String table,String times,String losecard, String kid) {
    SQLiteDatabase db = this.getWritableDatabase();
    String where = "kid= ?";
    String[] whereValue = { kid };
    ContentValues cv = new ContentValues();
    cv.put("times", times);
    cv.put("losecard", losecard);
    db.update(table, cv, where, whereValue);
}

public void updatemajor(String name,String teachername, String phone,String email,String address) {
    SQLiteDatabase db = this.getWritableDatabase();
    String where = "name= ?";
    String[] whereValue = { name };
    ContentValues cv = new ContentValues();
    cv.put("teachername", teachername);
    cv.put("phone", phone);
    cv.put("email", email);
    cv.put("address", address);
    db.update("major", cv, where, whereValue);
}

public void deletetable(String name) {
    SQLiteDatabase db = this.getReadableDatabase();
    String sql = "drop table "+name+" ";
    db.execSQL(sql);
}

public Cursor selectmode(String name) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db
            .query("mode", null,"name=?", new String[] { String.valueOf(name) }, null, null, null);
    return cursor;
}


public void updatemode(String name,String stat) {
    SQLiteDatabase db = this.getWritableDatabase();
    String where = "name= ?";
    String[] whereValue = { name };
    ContentValues cv = new ContentValues();
    cv.put("stat", stat);
    db.update("mode", cv, where, whereValue);
}

public void logintime(String logtime,String major) {
    SQLiteDatabase db = this.getWritableDatabase();
    String where = "logtime= ? and major =? ";
    String[] whereValue = { logtime,major };
    ContentValues cv = new ContentValues();
    cv.put("flag", "1");
    db.update("login", cv, where, whereValue);
}


}

附录B:软件（系统）使用说明书 学生考勤系统开发（Android版） 1．系统使用概述 本系统是基于校园卡学生考勤APP,是运用android平台的进行开发完成的。Android开发工具(ADT)，作为Eclipse工具插件，让其支持Android快速入门和便捷开发，可通过Eclipse启动菜单（启动Eclipse后，选择Help->Install New Software）安装。Android Software Development Kit(SDK) 为开发者提供相关封装API接口库文件、文档资源及一些工具包整合。当然了如果使用Eclipse作为开发工具，那么只需要安装SDK也可以，ECLIPSE和ADT也一并省略掉。从SDK安装目录下执行SDK Manager.exe或在Eclipse中执行Window->Android SDK and AVD manager可以运行虚拟机。 1．1系统功能 信息录入：利用动态库和SQLite助手类来操作数据库，完成数据的初始录入。 登陆考勤：根据教师的相关信息，只考勤和登陆信息相同的教师考勤班级和课程。 信息查询：查询相应课次和考勤日期的考勤情况。 学生信息修改：新增学生信息和修改学生卡号。 报表统计：选择考勤时间段和考勤教师和课程。生成统计报表。 1．2系统结构 本系统主要分为手机客户端和PC服务端。 手机客户端结构为： 添加课程，选择课程，添加学习信息，考勤，查看考勤结果 2．系统安装 和安装普通app一样，可以借助工具安装，也可以直接拷贝到内存卡中，点击安装即可。该app由android 4.2开发，建议安装在4.3及以上的android操作系统中。 2．1系统要求  手机客户端： Android系统要求：Android 2.3及其以上。 手机配置：内存 256M及其以上。 主频 600HZ及其以上。 2．运行说明 把SspuRegister.apk复制的到内存卡中，然后点击安装即可 3．疑难解答 在使用android开发平台时遇到疑难问题,可以通过该开发的帮助功能,进行查询得到相关帮助。 5．服务与支持 http://developer.android.com/sdk/index.html?hl=sk
