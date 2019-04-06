import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Sections from '@/components/Sections'
import Courts from '@/components/Courts'
import Teams from '@/components/Teams'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/sections',
      name: 'Sections',
      component: Sections
    },
    {
      path: '/courts',
      name: 'Courts',
      component: Courts
    },
    {
      path: '/teams',
      name: 'Teams',
      component: Teams
    }
  ]
})
