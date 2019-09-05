/**
 * 打字游戏步骤:
 1.准备一个游戏界面,提供开始游戏按钮
 禁用按钮,在页面加载完毕,即时启用"开始游戏"按钮
 2.对开始按钮,添加点击事件
 2.1) 定时随机生成字符,并显示到页面中
 2.1.1> 创建<lable>元素,并设置随机字符
 <label style="position:absolute;top:30px;left:10px;background-color:red;width:20px;text-align:center;font-weight:bold;">I</label>
 2.1.2> 把<lable>元素添加到body元素中
 2.2) 让页面中的字符周期性向下移动
 2.3) 对键盘输入内容,监听,对比输入内容,和游戏界面字母内容,一致则消除

 */

window.onload=function ()
{
    var submit=document.getElementById("sub");
    submit.disabled=false;
    //增加一个点击事件
    submit.onclick=submite;
};

var labelArray=new Array();
function submite() {
    this.disabled=true;
    //2.1) 定时随机生成字符,并显示到页面中
    setInterval(function () {
        //创建label元素
        var label=createLabel();
        //显示label元素
        document.body.appendChild(label);
        //存储label元素
        labelArray.push(label);
    },1000);


    //设置label位置，使其从上到下移动
    setInterval(function () {
        //设置浏览器的高度
        var windowTop=document.documentElement.clientHeight-30;
        for (var i=0;i<labelArray.length;i++){
            //获取label元素
            var label=labelArray[i];
            //修改该元素距离顶端的位置
            var old=parseInt(label.style.top);
            if (old<windowTop){
                var hight=old+10;
                label.style.top=hight+"px";
            } else {
                //从界面移除
                label.parentNode.removeChild(label);
                //从数组中移除
                labelArray.splice(i,1);
            }
        }
    },200);

    //最后一步
    //给当前文档设置键盘按下的事件
    document.onkeydown=function (ev) {
        //获取当前被按下的ASICC码
        var keyCode=ev.keyCode;
        var charCode=String.fromCharCode(keyCode);
        for (var i=0;i<labelArray.length;i++){
            var label=labelArray[i];
            if (charCode==label.innerHTML){
                label.parentNode.removeChild(label);
                labelArray.splice(i,1);
                break;
            }
        }
    }

}

//创建label元素的函数
function createLabel() {
    //随机数设置
    var code=parseInt(Math.random()*26)+65;
    var charCode=String.fromCharCode(code);
    //创建label，并设置属性
    var label=document.createElement("label");
    label.innerHTML=charCode;
    //<label style="position:absolute;top:30px;left:10px;background-color: aquamarine;width:20px;text-align: center;font-weight: bold;">
    label.style.position="absolute";
    label.style.top="30px";
    //随机生成左右位置
    var window=document.documentElement.clientWidth-30;
    var width=Math.random()*window;
    label.style.left=width+"10px";
    label.style.backgroundColor="aquamarine";
    label.style.width="20px";
    label.style.textAlign="center";
    label.style.fontWeight="bold";

    return label;
}