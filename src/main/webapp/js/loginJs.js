	let bool = true;
	let ckid = "";
	
	let chk = function (allId){
		allId = allId.slice(1, -1).split(',').map(i => i.trim());
		let id = document.getElementById('id');	
		bool = false
		
		for( var i = 0; i < allId.length ; i++){
			if (id.value==allId[i]){
				bool = true;
				break;
			}
		}
		
		if(bool){
			alert("이미 존재하는 아이디입니다.");
		}else{			
			alert("사용 가능한 아이디입니다.");
			ckid = id.value
		}
		return bool
	}
	
	document.addEventListener('DOMContentLoaded', function () {
        let form = document.getElementById('signForm');

        form.addEventListener('submit', function (event) {
            // 폼 제출 시 실행되는 함수
            let pw = document.getElementById('password').value;
            let pw2 = document.getElementById('checkPassword').value;

            // 비밀번호 확인
            if (bool || ckid !== id.value){
            	alert('아이디 중복 체크를 해주세요.')
                event.preventDefault();
                return false;
            }
            if (pw !== pw2) {
                alert('비밀번호가 일치하지 않습니다.');
                event.preventDefault(); // 폼 제출 방지
                return false;
            }
            form.submit();
        });
    });

window.onload = function(){
	const signUpButton = document.getElementById('signUp');
	const signInButton = document.getElementById('signIn');
	const container = document.getElementById('container');

	signUpButton.addEventListener('click',
	 () => container.classList.add('right-panel-active'));
	
	signInButton.addEventListener('click',
	 () => container.classList.remove('right-panel-active'));

	let birth = document.getElementById('birthday');
	let today = new Date().toISOString().substring(0, 10);
	birth.max = today;
	
}