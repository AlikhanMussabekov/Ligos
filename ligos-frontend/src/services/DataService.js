import vue from 'vue'
import axios from 'axios'
import {store} from './store'

const apiClient = axios.create({
	baseURL: 'http://localhost:8080',
	withCredentials: true,
	headers:{
		'Content-type' : 'application/json'
	}
});

export default {
	login(email, password) {
		return apiClient.post('/user/signin', null,
			{
				useCredentials: true,
				params: {
					'email': email,
					'password': password
				}
			}
		)
	},
	register(name,email, password){
		return apiClient.post('/user/signup',{
			name: name,
			email: email,
			password: password,
			authType: 'jwt',
			roles:[
				'ROLE_USER'
			]
		})
	},
	whoAmI(){
		return apiClient.get('/user/user/me', {
			headers: {
				'Authorization': 'Bearer ' + store.getters.GET_TOKEN
			},
			useCredentials: true
		})
	},
	getSections(count){
		return apiClient.get('/section/all/' + count)
	},
	getSection(id){
		return apiClient.get('/section/' + id)
	},
	getSectionDetails(id){
		return apiClient.get('/section/' + id + '/details')
	},
	getCourts(count){
		return apiClient.get('/court/all/' + count)
	},
	getCourt(id){
		return apiClient.get(/court/ + id)
	},
	getCourtPayments(id){
		return apiClient.get('/court/' + id + '/payments')
	},
	getTeams(){
		return apiClient.get('/team/all')
	}
}
