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
	organizationLogin(loginForm){
		return apiClient.post('/organizations/signin', null, {
			useCredentials: true,
			params: {
				'email': loginForm.email,
				'password': loginForm.password
			}
		})
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
	orgInfo(){
		return apiClient.get('/organizations/me', {
			headers: {
				'Authorization': 'Bearer ' + store.getters.GET_TOKEN
			},
			useCredentials: true
		})
	},
	getSections(count){
		return apiClient.get('/section/all/' + count)
	},
	getMySections(){
		return apiClient.get('/section/mySections',{
			headers: {
				'Authorization': 'Bearer ' + store.getters.GET_TOKEN
			},
			useCredentials: true
		})
	},
	getMyCourts(){
		return apiClient.get('/court/myCourts',{
			headers: {
				'Authorization': 'Bearer ' + store.getters.GET_TOKEN
			},
			useCredentials: true
		})
	},
	createSection(form){
		var bodyFormData = new FormData();
		bodyFormData.set('name', form.name);
		bodyFormData.set('description', form.description);
		bodyFormData.set('photo', form.photo);

		return apiClient.post('/section',bodyFormData,
			{
				headers: {
					'Authorization': 'Bearer ' + store.getters.GET_TOKEN,
					'Content-Type': 'multipart/form-data'
				},
				useCredentials: true
			}
		)
	},
	createCourt(form){
		var bodyFormData = new FormData();
		bodyFormData.set('name', form.name);
		bodyFormData.set('description', form.description);
		bodyFormData.set('photo', form.photo);

		return apiClient.post('/court',bodyFormData,
			{
				headers: {
					'Authorization': 'Bearer ' + store.getters.GET_TOKEN,
					'Content-Type': 'multipart/form-data'
				},
				useCredentials: true
			}
		)
	},
	getSection(id){
		return apiClient.get('/section/' + id)
	},
	getSectionDetails(id){
		return apiClient.get('/section/' + id + '/details')
	},
	getSectionReviews(id){
		return apiClient.get('/section/' + id + '/reviews')
	},
	registerToSection(sectionId, detailsId){
		return apiClient.post('/section/' + sectionId + '/details/' + detailsId + '/register', null,{
			headers: {
				'Authorization': 'Bearer ' + store.getters.GET_TOKEN
			},
			useCredentials: true
		})
	},
	addSectionReview(sectionId, review, rating){
		return apiClient.post('/section/' + sectionId + ' ' + '/review',
			{
				review: review,
				rating: rating
			},
			{
			headers: {
				'Authorization': 'Bearer ' + store.getters.GET_TOKEN
			},
			useCredentials: true
		})
	},
	addCourtReview(courtId, review, rating){
		return apiClient.post('/court/' + courtId + ' ' + '/review',
			{
				review: review,
				rating: rating
			},
			{
				headers: {
					'Authorization': 'Bearer ' + store.getters.GET_TOKEN
				},
				useCredentials: true
			})
	},
	getCourts(count){
		return apiClient.get('/court/all/' + count)
	},
	getCourt(id){
		return apiClient.get(/court/ + id)
	},
	getCourtReviews(id){
		return apiClient.get('/court/' + id + '/reviews')
	},
	getCourtPayments(id){
		return apiClient.get('/court/' + id + '/payments')
	},
	bookCourt(courtId,id){
		return apiClient.post('/court/' + courtId + ' ' + '/book',
			{
				ids:[id]
			},
			{
				headers: {
					'Authorization': 'Bearer ' + store.getters.GET_TOKEN
				},
				useCredentials: true
			})
	},
	getTeams(){
		return apiClient.get('/team/all')
	}
}
