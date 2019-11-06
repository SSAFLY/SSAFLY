import axios from 'axios'

//back 서버를 켠 노트북의 ip로 넣을것
const BASE_URL = "http://15.165.18.29:8090"

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
          user: response.data.mid,
          grade: response.data.grade
        }
      }
    })
    return result
  },
  async addProblem(problemForm) {
    let result = {};
    await axios.post(BASE_URL + '/problem', problemForm)
    .then(response => {
      if (response.data.success == "true") {
        result = {
          success: true
        }
      }
    })
    return result
  },

  async sendAnswer(content){
    let result = {};
    console.log(content)
    await axios({
      method: 'POST',
      url: BASE_URL + '/answer/' + sessionStorage.getItem("user"),
      data: content,
      headers:{'Content-Type': 'application/json; charset=utf-8'}
    })
    .then(response => {
      if (response.data.success == "true") {
        result = {
          success: true
        }
      }
    })
  
    return result
  },

  async getProblem(id) {
    let response = [];
    response = await axios.get(BASE_URL + '/problem/solving/' + id)
  
    console.log(response.data)
    return response.data;
  },
  async getAllProblem() {
    let response = [];
    response = await axios.get(BASE_URL + '/problem')
    console.log(response.data)
    return response.data;
  },
  async updateProblem(contents) {
    let result = {};
    await axios.put(BASE_URL + '/problem/' + contents.key, contents.content)
    .then(response => {
      if (response.data.success == "true") {
        result = {
          success: true
        }
      }
    })
    return result;
  },
  async deleteProblem(key) {
    let result = {};
    await axios.delete(BASE_URL + '/problem/' + key)
    .then(response => {
      if (response.data.success == "true") {
        result = {
          success: true
        }
      }
    })
    return result;
  },
  async getAllWorkbookByID(id) {
    let response = [];
    console.log(id)
    response = await axios.get(BASE_URL + '/answer/' + id)
    console.log(response.data)
    return response.data;
  },
  async updateWorkbook(id,contents) {
    let response = [];
    response = await axios.put(BASE_URL + '/answer/' +id+'/'+ contents.key, contents)
    console.log(response.data)
    return response.data;
  },
  async deleteWorkbook(id,key) {
    let response = [];
    response = await axios.delete(BASE_URL + '/answer/' +id + '/' + key)
    console.log(key)
    console.log(response.data)
    return response.data;
  }
}