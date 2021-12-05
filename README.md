# CPretreatmentSystem_C语言的预处理程序

## 项目目的
只是为了完成本年度的编译原理课程设计的课设 `(*ﾟ∇ﾟ)`

## 题目要求
&ensp;&ensp;设计一个C语言的预处理程序。将给出C语言的所有宏常量进行计算，并生成另外一个文件，将宏常量的展开和计算结果全部显示出来。<br>
&ensp;&ensp;将定义的宏在源程序中全部替换

## 程序处理过程 1.0

#### 文件与程序进行链接<br>

毕竟不允许将程序用scanf的形式输入，也不允许用printf的形式输出。<br>
而且，也不用考虑对于时间的浪费，所以可以使用一个模块，暴露两个接口:<br>

##### `boolean` 源文件输入(源文件路径)

成功读入返回`ture`, 否则返回`false`

##### 结果文件输出(结果文件输出路径)