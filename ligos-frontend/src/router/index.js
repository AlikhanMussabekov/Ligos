import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Sections from '@/components/Sections'
import Courts from '@/components/Courts'
import Teams from '@/components/Teams'
import SectionDetails from '@/components/SectionDetails'
import CourtDetails from '@/components/CourtDetails'
import PageNotFound from '@/components/PageNotFound'
import Settings from '@/components/Settings'
import MyCourts from '@/components/MyCourts'

Vue.use(Router);

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
      path: '/sections/:id',
      name: 'sectionDetails',
      component: SectionDetails
    },
    {
      path: '/court/:id',
      name: 'courtDetails',
      component: CourtDetails
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
    },
    {
      path: '/settings',
      name: 'Settings',
      component: Settings
    },
    {
      path: '/mycourts',
      name: 'MyCourts',
      component: MyCourts
    },
    {
      alias: '*',
      path: '/404',
      name: 'notFound',
      component: PageNotFound
    }
  ]
})
