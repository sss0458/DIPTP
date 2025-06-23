克隆仓库文件到本地：在仓库界面点击绿色的code按钮，选择http方式并复制地址，

打开命令行使用cd指令切换到你想要存放的目录，然后执行克隆命令

git clone [复制的仓库地址]

例如

git clone https://github.com/sss0458/DIPTP.git

然后就可以在项目文件夹中编写你的代码了。


推荐各人使用个人的分支中先进行你的开发工作，并频繁提交到新分支，main分支用来存储我们合并后的最新代码

其中创建新分支的命令为

git checkout -b feature/login-module # 创建并切换到新分支

就创建了一个登陆模块的功能分支

我们的命名约定为feature/xxx (新功能), bugfix/xxx (bug 修复), refactor/xxx (代码重构)

在新分支上开发提交代码

git add [位于项目文件夹中的文件]

git commit -m "feat: 完成登录表单设计"

git push origin feature/login-module

在工作开始之前，需要拉取最新代码来避免冲突

git checkout main

git pull origin main

git checkout feature/login-module

git merge main # 将 main 分支的更改合并到你的特性分支


经过代码审查之后，可以将你的特性分支合并到main分支

同样的，为了避免其他成员的修改，也需要在main分支的视角下拉取最新代码

git checkout main

git pull origin main

然后将特性分支和main分支进行合并

git merge feature/your-new-feature
