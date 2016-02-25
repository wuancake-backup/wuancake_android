1. 将github上的工程克隆到本地。
git clone https://github.com/wuanlife/Jianbing_Android.git
2. 查看工作目录的修改状态。
git status
3. 添加到工作缓冲区。
git add . 
4. 添加到工作区。
git commit -m "添加git命令文件"
5. 添加到github上
git push origin master
输入账号密码。
6. 
git pull origin master

煎饼计划-github账号
https://github.com/wuanlife/Jianbing_Android



///////////////////////////////////////////////////////////////////////
你可以按如下命令来生成sshkey

ssh-keygen -t rsa -C "xxxxx@xxxxx.com"# Creates a new ssh key using the provided email
# Generating public/private rsa key pair...
查看你的public key，并把他添加到 Git @ OSC http://git.oschina.net/keys

cat ~/.ssh/id_rsa.pub
# ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC6eNtGpNGwstc....
添加后，在终端（Terminal）中输入

ssh -T git@git.oschina.net
若返回

Welcome to Git@OSC, yourname! 
则证明添加成功。
/////////////////////////////////////////////////////////////////////

