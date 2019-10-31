import axios from 'axios'

//back 서버를 켠 노트북의 ip로 넣을것
const BASE_URL = "http://localhost:9090"

export default {
  async signUp(signupForm) {
    const response = {};

    await axios.post(BASE_URL + '/member', signupForm)
    .then(response => {
		this.result = response.data;
		if(this.result.success==='false'){
      alert("이미 등록되어있는 이메일 입니다.")
      response = {
        isSignIn: false
			}
		} else {
			alert("정상적으로 회원가입 되었습니다.")
			response = {
        isSignIn: true
			}
		}
    })
	
    return response
  },

  async login(loginForm) {
    let result = {};
    await axios.post(BASE_URL + '/login', loginForm)
    .then(response => {
		if (response.data.success == "true") {
      result = {
        isLoggedIn: true,
        user: response.data.mid
			}
		}
    })
  
    return result
  }

}