# redis-application-sharing
Redis高级数据结构与应用场景

## 目录
- [Redis安装步骤](#redis-安装步骤)
  - [Docker for Win10安装](#docker-for-win10-安装)
  - [Redis for Docker安装](#redis-for-docker-安装)
- [示例代码使用注意事项](#示例代码使用注意事项)

## redis-安装步骤
### docker-for-win10-安装

由于Redis原生只支持Linux，虽然微软也弄了Windows版的Redis，但是版本比较旧，所以综合来看，Windows下安装Redis最快捷的方式就是通过Docker了。在安装之前，请确保：

PS：以下教程都只针对Win10 Pro，如果是Linux用户，那么根本不需要用Docker。如果你的电脑不支持以下2点，请自行寻找其它办法安装Docker，或者安装微软的Redis Windows版。(https://github.com/MicrosoftArchive/redis)

1. 你的CPU支持虚拟化功能，并且在BIOS中把它打开；
2. 你安装了Windows 10 Pro（Win 10 Home版和Win 7不支持Hyper-V，Docker For Windows 需要Hyper-V的支持），并在设置中打开了Hyper-V功能，如下图所示：

![](https://raw.githubusercontent.com/yellowb/redis-application-sharing/imgs/imgs/windows-hyper-v.png)

**Step 1：安装Docker**
参考[Docker官网](https://docs.docker.com/docker-for-windows/install/ "Docker官网")安装Docker For Windows。安装成功后，检查Docker状态是否是Running：

![](https://raw.githubusercontent.com/yellowb/redis-application-sharing/imgs/imgs/docker-running.png)

**Step 2：配置Docker仓库镜像地址**
由于众所周知的原因，国内网络访问官方的Docker镜像仓库很慢，为了加速拉取镜像的过程，需要配置国内的mirror仓库。请参考[这里](https://yeasy.gitbooks.io/docker_practice/content/install/mirror.html "这里")进行配置。如果不想看文档，你可以直接复制`http://519067d7.m.daocloud.io`到Docker Setting -> Daemon中，**记得把Experimental features前的钩子去掉**，否则可能会重启Windows后Docker容器就启动失败：

![](https://raw.githubusercontent.com/yellowb/redis-application-sharing/imgs/imgs/docker-settings.png)

### redis-for-docker-安装

**Step 1：拉取Redis镜像**

在命令行下执行`docker pull redis:4.0.11`，会把Redis 4.0.11的Docker镜像下载到本地，当然你也可以下载其他版本，请上仓库的网站自行搜索：

![](https://raw.githubusercontent.com/yellowb/redis-application-sharing/imgs/imgs/docker-image-pull.png)

**Step 2：查看Redis镜像id**

执行`docker image ls`查看已经下载成功的镜像，`IMAGE ID`这列即是镜像的ID，等下运行会用到。

![](https://raw.githubusercontent.com/yellowb/redis-application-sharing/imgs/imgs/docker-image-ls.png)

**Step 3：用Docker镜像启动Docker容器**

执行`docker run -p 6379:6379 4e`，用刚刚下载的Redis镜像启动一个容器。其中`6379`是Redis的默认端口，表示把容器中的`6379`端口绑定到本机的`6379`端口；而`4e`即Redis镜像的ID的前2位前缀，执行Docker命令时并不要求输入完整的ID，因为ID太长了不方便，所以一般只需要输入一个可区分不同镜像/容器的ID前缀就可以了。

![](https://raw.githubusercontent.com/yellowb/redis-application-sharing/imgs/imgs/docker-image-run.png)

**Step 4：查看正在运行的Redis容器id**

执行`docker container ls`或`docker ps`，会罗列出正在运行的容器，入下图所示CONTAINER ID为cef87d002d9c的容器就是刚刚通过Redis镜像启动的容器：

![](https://raw.githubusercontent.com/yellowb/redis-application-sharing/imgs/imgs/docker-container-ls.png)

**至此，Redis容器已经运行起来了，端口是6379，你可以用任何至此Redis的客户端或库去连接它。**

如果想停止容器或想重新启动，可以用`docker stop <CONTAINER ID>`/`docker start <CONTAINER ID>`

**Step 5：登入到Redis容器中并运行其自带的Redis客户端**

Redis镜像中自带了一个默认的Redis客户端：Redis-Cli，通过它可以执行任何Redis支持的命令，也可以验证Redis是否正常工作。执行`docker exec -it ce redis-cli`，进入刚刚启动起来的容器里并运行redis-cli客户端，其中`ce`是容器ID。

![](https://raw.githubusercontent.com/yellowb/redis-application-sharing/imgs/imgs/docker-container-exec-rediscli.png)

## 示例代码使用注意事项
示例代码是一个Java工程，依赖管理用的是**gradle**，请先参照[gradle官网](https://gradle.org/install/ "gradle官网")下载并设置好环境变量。

可以用**IntelliJ IDEA**直接导入本项目，点击**File**菜单下的**Open**，选中源码中的`build.gradle`文件即可。

