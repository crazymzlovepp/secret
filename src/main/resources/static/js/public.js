//检查传过来的值是否是null/""/undefined
var checkIsNotBlank=function(value){
    if(value == null || value == ""|| value == "null" || value == undefined || value == "undefined"){
       return false;
    }else{
        return true;
    }
}
//tips相关操作
var colors = ['red', 'green', 'blue', 'yellow'], index = 0;
var getColor = function () {
    (index >= colors.length) && (index = 0);
    return colors[index++];
};

var strings = ['Short', 'You just switched the internet off', 'Please do not click too hard - next time we\'ll notify google.', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.'];
var getString = function () {
    return strings[Math.floor(Math.random()*strings.length)];
};

var titles = ['Congrats', 'Success', 'Thank you', false, false, false];
var getTitle = function () {
    return titles[Math.floor(Math.random()*strings.length)];
};