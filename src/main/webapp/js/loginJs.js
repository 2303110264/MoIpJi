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
	birth.value = today;
	birth.max = today;
}