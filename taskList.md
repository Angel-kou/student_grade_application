##构建主菜单（2mins）
- 输入：
       无
- 输出：
       String(主菜单信息)

##处理用户请求（4mins）
- 输入： 
        number：int(用户的选择)
- 输出：
        String(number为1时，输出添加学生提示信息；number为2时，输出打印成绩单提示信息；输入合法时作出响应)

##构建添加学生提示信息（2mins）
- 输入：
       用户输入
- 输出：
       String(格式不符合要求给出相应提示信息，并引导用户继续输入)

##构建打印成绩单提示信息（2mins）
- 输入：
       用户输入
- 输出：
       String(格式不符合要求给出相应提示信息，并引导用户继续输入)

##处理用户响应（2mins）
- 输入：
       number：int
- 输出： 
       响应方式，包括添加学生、打印报表、显示下一步动作
       

##添加学生（3mins）
- 输入：
       info： String[] ;  
       list: List<Student>(所有学生列表)

- 输出：
       return :list
       list :{
	          {
		           name：String,
		           serialNumber：String,
		           subjects：{
		             {
		              subName:String,
		              score:int,
		             }
		           },
		           totalScore：int,
	           }
             }

##打印报表（2mins）
- 输入：
       serialNumbers：String ;  
       所有学生列表list :List<Student>
       list :{
	          {
		           name：String,
		           serialNumber：String,
		           subjects：{
		             {
		              subName:String,
		              score:int,
		             }
		           },
		           totalScore：int,
	           }
             }

- 输出：
        String;
##显示下一步动作（2mins）
- 输入：
       无
- 输出：
       String(主菜单信息）
       提醒用户输入

##获取学生成绩单（5mins）
- 输入：
       serialNumbers：String ; 
       所有学生列表list :List<Student>
       list :{
	          {
		           name：String,
		           serialNumber：String,
		           subjects：{
		             {
		              subName:String,
		              score:int,
		             }
		           },
		           totalScore：int,
	           }
             }

- 输出：
       String;（需要打印的学生成绩信息） 

##计算全班总分平均数（3mins）
- 输入：
       所有学生列表list :List<Student>
       list :{
	          {
		           name：String,
		           serialNumber：String,
		           subjects：{
		             {
		              subName:String,
		              score:int,
		             }
		           },
		           totalScore：int,
	           }
             }

- 输出：
       String; 
##计算全班总分中位数（3mins）
- 输入：
       所有学生列表list :List<Student>
       list :{
	          {
		           name：String,
		           serialNumber：String,
		           subjects：{
		             {
		              subName:String,
		              score:int,
		             }
		           },
		           totalScore：int,
	           }
             }

- 输出：
       String; 









