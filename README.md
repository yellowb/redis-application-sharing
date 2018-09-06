# redis-application-sharing
Redis高级数据结构与应用场景

## 目录
- [Redis安装步骤](#redis-安装步骤)
  - [Docker for Win10安装](#docker-for-win10-安装)
- [源码使用注意事项](#源码使用注意事项)

## redis-安装步骤
### docker-for-win10-安装
PS：以下教程都只针对Win10，如果是Linux用户，那么根本不需要用Docker。

由于Redis原生只支持Linux，虽然微软也弄了Windows版的Redis，但是版本比较旧，所以综合来看，Windows下安装Redis最快捷的方式就是通过Docker了。在安装之前，请确保：

1. 你的CPU支持虚拟化功能，并且在BIOS中把它打开；
2. 你安装了Windows 10 Pro（Win 10 Home版和Win 7不支持Hyper-V，Docker For Windows 需要Hyper-V的支持），并在设置中打开了Hyper-V功能，如下图所示：

![](https://raw.githubusercontent.com/yellowb/redis-application-sharing/imgs/imgs/windows-hyper-v.png)

如果你的电脑不支持以上2点，请自行寻找其它办法安装Docker，或者安装微软的Redis Windows版。

**Step 1：**
参考[Docker官网](https://docs.docker.com/docker-for-windows/install/ "Docker官网")安装Docker For Windows。安装成功后，检查Docker状态是否是Running：

![](https://raw.githubusercontent.com/yellowb/redis-application-sharing/imgs/imgs/docker-running.png)

**Step 2：**
由于众所周知的原因，国内网络访问官方的Docker镜像仓库很慢，为了加速拉取镜像的过程，需要配置国内的mirror仓库。请参考[这里](https://yeasy.gitbooks.io/docker_practice/content/install/mirror.html "这里")进行配置。如果不想看文档，你可以直接复制`http://519067d7.m.daocloud.io`到Docker Setting -> Daemon中：

![](https://raw.githubusercontent.com/yellowb/redis-application-sharing/imgs/imgs/docker-settings.png)


## 源码使用注意事项
