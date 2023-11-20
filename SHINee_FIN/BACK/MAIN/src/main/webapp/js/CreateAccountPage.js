document.getElementById("profileUploadButton").addEventListener("click", function(){
    var fileInput = document.getElementById("profileInput");
    var uploadImage = document.getElementById("uploadProfile");
    var imageContainer = document.getElementById("profileContainer");

    if(fileInput.files.length > 0){
        var file = fileInput.files[0];
        var reader = new FileReader();

        reader.onload = function(e){
            uploadImage.src = e.target.result;
            imageContainer.style.display = "block";
        };

        reader.readAsDataURL(file);
    } else{
        alert("프로필 사진을 넣어야 합니다.");
    };
});

function closeButton(){

    if(confirm("회원가입을 취소하시겠습니까?.")){
        alert("회원가입이 취소되었습니다.")
        var pageMove = "https://music.apple.com/kr/browse";
        location.href=pageMove; 
    };
};

function idCheck(){
	
	var userid = document.getElementById("id").value;
	const idPattarn =  /^[A-za-z0-9]{4,12}$/;
	
	if(idPattarn.test(userid)){
		$('#ruleid').text('조건 일치').css('color', 'green');
	}else{
        $('#ruleid').text("아이디는 4 ~ 12 자의 숫자,영어 로만 구성되어야합니다").css('color', 'red');
     };
     
     document.userInfo.idCheckBox.value = "idUnCheck"
     
};



/*
function idDbCheck(){
	const a = docu
	a.value
	window.name = "parentForm";
	window.open("Id_Check.jsp?=",
	"checkForm", "width=500, height=300, resizable = no, scrollbars = no");
	
	}
	*/


function nameCheck(){

    var username = document.getElementById("name").value; 
    const namePattarn = /^[가-힣]{1,15}$/;

    if(namePattarn.test(username)){
		$('#rulename').text('조건 일치').css('color', 'green');
	}else{
        $('#rulename').text("이름은 15자 이하의 한글로만 구성되어야 합니다.").css('color', 'red');
     };
};


function pwCheck(){
	
	var password = document.getElementById("pw").value;
    const passwordPattarn = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,32}$/;

    if(passwordPattarn.test(password)){
		$('#rulepm').text('조건 일치').css('color', 'green');
	}else{
        $('#rulepm').text("비밀번호는 6 ~ 32자의 영문, 대문자, 소문자, 특수문자, 숫자를 포함해야합니다.").css('color', 'red');
     };
	
    if($('#pw').val() == $('#checkpw').val()){
        $('#checkpm').text('비밀번호 일치').css('color', 'green');
    }else{
        $('#checkpm').text('비밀번호가 불일치').css('color', 'red');
    };
};

function nicknameCheck(){

    var userNickname = document.getElementById("nickname").value;
    const nicknamePattarn =  /^[가-힣A-za-z0-9]{1,20}$/;
    
    if(nicknamePattarn.test(userNickname)){
		$('#rulenickname').text('조건 일치').css('color', 'green');
	}else{
        $('#rulenickname').text("닉네임은 20자 이하의 영어,숫자,한글로만 구성되어야 합니다.").css('color', 'red');
     };
    
};

function userEmailCheck(){
    
    var userEmail = document.getElementById("emailid").value;
    const emailPattarn =  /^[A-Za-z-0-9\-\.]+@[A-Ja-z-0-9\-\.]+\.[A-Ja-z-0-9]+$/;

    if(emailPattarn.test(userEmail)){
		$('#ruleemail').text('조건 일치').css('color', 'green');
	}else{
        $('#ruleemail').text("이메일은 80자 이하의 @과.를 포함한 영어, 숫자로만 구성되어야 합니다.").css('color', 'red');
     };
};

function userPhoneCheck(){

    var userPhone = document.getElementById("phoneNumber").value;
    const phonePattarn = /^(010|011)[0-9]{8}$/;

    if(phonePattarn.test(userPhone)){
		$('#rulephoneNumber').text('조건 일치').css('color', 'green');
	}else{
        $('#rulephoneNumber').text("올바른 번호가 아닙니다.").css('color', 'red');
     };
}






function CreateUser(){
	
    var userid = document.getElementById("id").value;
    var username = document.getElementById("name").value; 
    var password = document.getElementById("pw").value;
    var passwordCheck = document.getElementById("checkpw").value;
    var userNickname = document.getElementById("nickname").value;
    var userEmail = document.getElementById("emailid").value;
    var userPhone = document.getElementById("phoneNumber").value;
    //var idCheckBox = document.getElementById("idCheckBox").value;


    
    const idPattarn =  /^[A-za-z0-9]{4,12}$/;
    const namePattarn = /^[가-힣]{1,15}$/;
    const passwordPattarn = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,32}$/;
    const nicknamePattarn =  /^[가-힣A-za-z0-9]{1,20}$/;
    const emailPattarn =  /^[A-Za-z-0-9\-\.]+@[A-Ja-z-0-9\-\.]+\.[A-Ja-z-0-9]+$/;
    const phonePattarn = /^(010|011)[0-9]{8}$/;	

    if(!userid.match(idPattarn)){
        alert("아이디는 4 ~ 12 자의 숫자,영어 로만 구성되어야합니다");
        return false;
   /* else if(idCheckBox !="idOnCheck"){
        alert("아이디 중복체크를 하셔야합니다.")
        return false;
        }*/
       
    }else if(!username.match(namePattarn)){
        alert("이름은 15자 이하의 한글로만 구성되어야 합니다.");
        return false;
    }else if(!password.match(passwordPattarn)){
        alert("비밀번호는 6 ~ 32자의 영문, 대문자, 소문자, 특수문자, 숫자를 포함해야합니다.");
        return false;
    }else if(password !== passwordCheck){
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }else if(!userNickname.match(nicknamePattarn)){
        alert("닉네임은 20자 이하의 영어,숫자,한글로만 구성되어야 합니다.");
        return false;
    }else if(!userEmail.match(emailPattarn)){
        alert("이메일은 80자 이하의 @과.를 포함한 영어, 숫자로만 구성되어야 합니다.")
        return false;
    }else if(!userPhone.match(phonePattarn)){
        alert("올바른 번호가 아닙니다.");
        return false;
    }
    alert("SHINee Musci에 오신걸 환영합니다!")
     
	
	
	/*
	var idCheckResult = $('#idCheckBox').val();
    
    // 아이디 중복 확인이 되었는지 확인
    if (idCheckResult === 'idUnCheck') {
        alert('아이디 중복 확인을 해주세요.');
        return;
    }
    */

  
    
	
	
    //alert("회원가입이 정상적으로 완료되었습니다!.");
    //var pageMove = "https://music.apple.com/kr/browse";
    //location.href=pageMove;     // ( 현재 페이지에서 이동 )
    
    // window.open(사이트링크) ( 새창 열기 )
    
     
     }