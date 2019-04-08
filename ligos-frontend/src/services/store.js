import Vue from 'vue'
import Vuex from 'vuex'
import DataService from './DataService'

Vue.use(Vuex);

export const store = new Vuex.Store({
	state:{
		token: localStorage.getItem('token'),
		logged: localStorage.getItem('token') !== null,
		user: localStorage.getItem('user')
	},
	getters: {
		GET_LOGGED: state => {
			return state.logged
		},
		GET_USER: state => {
			return state.user
		},
		GET_TOKEN: state => {
			return state.token
		}
	},
	mutations: {
		SET_LOGGED(state){
			state.logged = true
		},
		SET_LOGGED_OUT(state){
			state.logged = false
		},
		SET_USER(state, user){
			state.user = user
		},
		SET_TOKEN(state, token){
			state.token = token
		}
	},
	actions: {
		SET_LOGGED({ commit, dispatch }){
			DataService.whoAmI()
				.then(response => {
					//this.('user', JSON.stringify(response.data));
					dispatch('SET_USER', JSON.stringify(response.data));
					console.log(response)
				})
				.catch(e => {
					console.log(e)
				});
			commit('SET_LOGGED');
		},
		SET_LOGGED_OUT({ commit }){
			delete localStorage.user;
			delete localStorage.token;
			commit('SET_LOGGED_OUT');
		},
		SET_USER({ commit }, user){
			localStorage.setItem('user', user);
			commit('SET_USER', user)
		},
		SET_TOKEN({commit}, token){
			localStorage.setItem('token', token)
			commit('SET_TOKEN', token)
		}
	}
});