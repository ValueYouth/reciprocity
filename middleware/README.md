### 中间件定义
* 中间件是一种独立的系统软件或服务程序，分布式应用软件借助这种软件在不同的技术之间共享资源，中间件位于客户机服务器的操作系统之上，管理计算资源和网络通信。
* 中间件（middleware）是基础软件的一大类，属于可复用软件的范畴。中间件处于操作系统软件与用户的应用软件的中间。中间件在操作系统、网络和数据库之上，应用软件的下层。作用：为处于自己上层的应用软件提供运行与开发的环境，帮助用户灵活、高效地开发和集成复杂的应用软件。

### 产生背景
* 在中间件产生以前，应用软件直接使用操作系统、网络协议和数据库等开发，这些都是计算机最底层的东西，越底层越复杂，开发者不得不面临许多很棘手的问题，如操作系统的多样性，繁杂的网络程序设计、管理，复杂多变的网络环境，数据分散处理带来的不一致性问题、性能和效率、安全，等等。这些与用户的业务没有直接关系，但又必须解决，耗费了大量有限的时间和精力
* 有人提出能不能将应用软件所要面临的共性问题进行提炼、抽象，在操作系统之上再形成一个可复用的部分，供成千上万的应用软件重复使用。这一技术思想最终构成了中间件这类的软件。
* 中间件屏蔽了底层操作系统的复杂性，使程序开发人员面对一个简单而统一的开发环境，减少程序设计的复杂性，将注意力集中在自己的业务上，不必再为程序在不同系统软件上的移植而重复工作，从而大大减少了技术上的负担。

### 分类

| 类别  |  作用 |
|---|---|
| 消息中间件(ActiveMessenger)  | 适用于任何需要进行网络通信的系统，负责建立网络通信的通道，进行数据或文件发送。消息中间件的一个重要作用是可以实现跨平台操作，为不同操作系统上的应用软件集成提供服务。  |
|  交易中间件                  | 适用于联机交易处理系统，主要功能是管理分布于不同计算机上的数据的一致性，保障系统处理能力的效率与均衡负载。交易中间件所遵循的主要标准是x/open dtp模型。  |
|  对象中间件                  | 基于corba标准的构件框架，相当于软总线，能使不同厂家的软件交互访问，为软件用户及开发者提供一种即插即用的互操作性，就像现在使用集成块和扩展板装配计算机一样  |
| 应用服务器                   | 用来构造internet/intranet应用和其它分布式构件应用，是企业实施电子商务的基础设施。应用服务器一般是基于j2ee工业标准的。  |
| 安全中间件                   | 以公钥基础设施（pki）为核心的、建立在一系列相关国际安全标准之上的一个开放式应用开发平台，向上为应用系统提供开发接口，向下提供统一的密码算法接口及各种ic卡、安全芯片等设备的驱动接口。  |
|应用集成服务器                |把工作流和应用开发技术如消息及分布式构件结合在一起，使处理能方便自动地和构件、script |
