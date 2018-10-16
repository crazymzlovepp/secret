//检查传过来的值是否是null/""/undefined
checkIsNotBlank=function(value){
    if(value == null || value == ""|| value == "null" || value == undefined || value == "undefined"){
       return false;
    }else{
        return true;
    }
}