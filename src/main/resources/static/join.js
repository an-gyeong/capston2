 NAME = document.querySelector('.name_'),
 ID = document.querySelector('.text-id'),
 PW = document.querySelector('.text-pw'),
 PWCheck = document.querySelector('.PWCheck'),
 EMAIL = document.querySelector('.email');

 SEE = document.getElementById('see'),
 LOID = document.getElementById('LOID'),
 LOPW = document.getElementById('LOPW'),
 HW = document.getElementById('ghldnjs');

function checkfield() { //정보입력 하지 않았을 때
	
    if(NAME.value =="" || ID.value == "" || PW.value =="" || PWCheck.value ==""){
	    if(NAME.value =="") { 
	        alert("이름을 입력하세요");
	        NAME.focus();
	    }
	    else if(EMAIL.value == ""){
	        alert("이메일을 입력하세요"); 
	        EMAIL.focus();
	    }
	    else if(ID.value == ""){
	        alert("아이디을 입력하세요"); 
	        ID.focus();
	    }
	
	    else if(PW.value =="") {
	        alert("비밀번호를 입력하세요");
	        PW.focus();
	    }
	    else if(PWCheck.value =="") {
	        alert("비밀번호 확인하십시오");
	        PWCheck.focus();
	    }
	    else if(PW.value != PWCheck.value){
	        alert("비밀번호 불일치");
	        PWCheck.focus();
	    }
    }
    else{
        alert("회원가입 완료!");
        
        //form 데이터 넘겨줌
		var memberForm = document.memberForm;
		console.log(memberForm)
		memberForm.submit();
         //서버 연결 후 메인 주소로 변경
    }
}


function same(){ //비번 확인
    const samck = document.getElementById('same');

    if(PW.value != '' && PWCheck.value != ''){
        if(PW.value == PWCheck.value){
            samck.innerHTML = "비밀번호 일치"
            samck.style.color = "blue";
        }
        else{
            samck.innerHTML = "비밀번호 불일치"
            samck.style.color = "red";
        }
    }
}

function show(){ //비밀번호 표시
    if(PW.type == "password"){
        PW.type = "text";
        SEE.setAttribute("value", "HIDE");
    }
    else{
        PW.type = "password";
        SEE.setAttribute("value", "SHOW");
    }
}

function loginh(){ //새로고침 시 화면 유지
    if(LOID.value == ""){
        alert("아이디 입력");
        LOID.focus();
        return false;
    }
    else if(LOPW.value == ""){
        alert("비밀번호 입력");
        LOPW.focus();
        return false;
    }
 
    HW.style.display = "none"
    login_on.style.display = "none"; 
    document.getElementById("areaLogin").style.display = "none";

    var objLoginInfo = document.getElementById('loginInfo');
    objLoginInfo.innerHTML = LOID.value + "님 환영합니다"; //자기 점수 알려주는거 추가해야함
           
    document.getElementById("areaLogout").style.display = "";

}

function fnLogout(){
    LOID.value = "";
    LOPW.value = "";

    areaLogin.style.display = "";
    document.getElementById("areaLogout").style.display = "none";
    login_on.style.display = "block"; 
    HW.style.display = "block";
}
