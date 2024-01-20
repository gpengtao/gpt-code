
## Jquery查找元素的方法汇总
https://blog.csdn.net/shuncheng_hu/article/details/121952609

## console导入jQuery
var importJs=document.createElement('script')  //在页面新建一个script标签
importJs.setAttribute("type","text/javascript")  //给script标签增加type属性
importJs.setAttribute("src", 'https://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js') //给script标签增加src属性， url地址为cdn公共库里的
document.getElementsByTagName("head")[0].appendChild(importJs) //把importJs标签添加在页面