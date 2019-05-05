function checkPsw(){
	var middle=$("#middle").text();
	var strength=$("#strength").text();
	
	if(middle=='' && strength==''){
		return false;
	}else{
		return true;
	}
}
function AuthPasswd() {
	var string=$("#newpassword").val();
	var modes = 0;
    //正则表达式验证符合要求的
    if (string.length >= 6) { 
    	if (/\d/.test(string)) {modes++}; //数字
	    if (/[a-z]/.test(string)) {modes++}; //小写
	    if (/[A-Z]/.test(string)) {modes++}; //大写  
	    if (/\W/.test(string)) {modes++}; //特殊字符
    }
    
    //强度级别
    if(modes == 3) {
        $('#weak').css({backgroundColor:'#dd0000'});
        $('#middle').css({backgroundColor:'#ffcc33'});
        $('#strength').css({backgroundColor:'#009900'});
        $('#weak').html('');
        $('#middle').html('');
        $('#strength').html('强');
    }else if(modes == 2){
        $('#weak').css({backgroundColor:'#dd0000'});
        $('#middle').css({backgroundColor:'#ffcc33'});
        $('#strength').css({backgroundColor:''});
        $('#weak').html('');
        $('#middle').html('中');
        $('#strength').html('');
    }else if(modes ==1) {
        $('#weak').css({backgroundColor:'#dd0000'});
        $('#middle').css({backgroundColor:''});
        $('#strength').css({backgroundColor:''});
        $('#weak').html('弱');
        $('#middle').html('');
        $('#strength').html('');
    }else{
        $('#weak').css({backgroundColor:''});
        $('#middle').css({backgroundColor:''});
        $('#strength').css({backgroundColor:''});
        $('#weak').html('太短');
        $('#middle').html('');
        $('#strength').html('');
    }
}