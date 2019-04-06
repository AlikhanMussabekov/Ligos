import axios from 'axios'

const apiClient = axios.create({
	baseURL: 'http://localhost:8080',
	withCredentials: true,
	headers:{
		'Content-type' : 'application/json',
		'Authorization' : 'Bearer ' + localStorage.token
	}
})

export default {
	login(login, password){

	}
}